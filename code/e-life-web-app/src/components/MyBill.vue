<template>
  <div>
    <el-row :gutter="10" style="padding-top: 20px">
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span style="font-size: 16px;">停车费账单</span>
          </div>
          <el-table :data="bill.filter(data => (data.status === -1 || data.status === 1 || data.status === -11))" style="width: 100%">
            <el-table-column label="时间" prop="time" align="center"></el-table-column>
            <el-table-column label="金额" prop="bill" align="center"></el-table-column>
            <el-table-column label="状态" prop="status" align="center">
              <template slot-scope="scope">
                <el-button v-if="scope.row.status === 1" type="success" plain size="small">已缴费</el-button>
                <el-button v-if="scope.row.status === -11" type="warning" plain size="small" @click="toPay(scope.row)">支付中</el-button>
                <el-button v-if="scope.row.status === -1" type="danger" plain size="small" @click="submit(scope.row)">未缴费</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span style="font-size: 16px;">物业费账单</span>
          </div>
          <el-table :data="bill.filter(data => (data.status === -2 || data.status === 2 || data.status === -12))" style="width: 100%">
            <el-table-column label="时间" prop="time" align="center"></el-table-column>
            <el-table-column label="金额" prop="bill" align="center"></el-table-column>
            <el-table-column label="状态" prop="status" align="center">
              <template slot-scope="scope">
                <el-button v-if="scope.row.status === 2" type="success" plain size="small">已缴费</el-button>
                <el-button v-if="scope.row.status === -12" type="warning" plain size="small" @click="toPay(scope.row)">支付中</el-button>
                <el-button v-if="scope.row.status === -2" type="danger" plain size="small" @click="submit(scope.row)">未缴费</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>
    <div class="block" align="center" >
      <el-pagination @current-change="handleCurrentChange" :current-page.sync="pageNum" :page-count="pageSize" :pager-count="7" layout="prev, pager, next, jumper"></el-pagination>
    </div>
    <el-dialog title="订单详情" :visible.sync="dialogFormVisible">
      <el-form :model="order">
        <el-form-item label="订单号">
          <el-input v-model="order.id" type="text" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="时间">
          <el-input v-model="order.time" type="text" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="金额">
          <el-input v-model="order.bill" type="text" :disabled="true"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button id="pay" type="primary" icon="el-icon-share" @click="handlePay()">支 付</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'MyBill',
  data () {
    return {
      bill: [],
      userInfo: {
        community: '',
        communityId: 0,
        username: '',
        email: '',
        phone: ''
      },
      dialogFormVisible: false,
      order: {},
      pageNum: 1,
      pageSize: 1
    }
  },
  mounted () {
    this.loadData()
    this.loadBill()
  },
  methods: {
    loadData () {
      this.userInfo.username = sessionStorage.getItem('username')
      if (this.userInfo.username === '' || this.userInfo.username === null) {
        this.$router.push({ name: 'Login' })
      }
      this.userInfo.phone = sessionStorage.getItem('phone')
      this.userInfo.communityId = sessionStorage.getItem('communityId')
      this.userInfo.email = sessionStorage.getItem('email')
      let bodyFormData = new FormData()
      bodyFormData.set('id', this.userInfo.communityId)
      let url = '/user-server/api/user/getCommunityById'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        this.userInfo.community = response.data.community
        sessionStorage.setItem('community', this.userInfo.community)
      })
    },
    loadBill () {
      let bodyFormData = new FormData()
      bodyFormData.set('username', this.userInfo.username)
      bodyFormData.set('page', this.pageNum)
      let url = '/pay-server/api/Pay/findHistory'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.length > 0 && response.data[0].login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          this.bill = response.data
          console.log(response.data)
          this.pageSize = response.data[response.data.length - 1].pageNum
          this.bill.pop()
        }
      })
    },
    handleCurrentChange (val) {
      this.pageNum = val
      this.loadBill()
    },
    submit (item) {
      let bodyFormData = new FormData()
      bodyFormData.set('pid', item.id)
      bodyFormData.set('username', this.userInfo.username)
      bodyFormData.set('bill', item.bill)
      let url = '/pay-server/api/Pay/saveOrders'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        this.loadBill()
        this.toPay(item)
      })
    },
    toPay (row) {
      let bodyFormData = new FormData()
      bodyFormData.set('pid', row.id)
      bodyFormData.set('username', this.userInfo.username)
      let url = '/pay-server/api/Pay/getOrders'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        this.order = response.data[0]
        this.dialogFormVisible = true
      })
    },
    handlePay () {
      let bodyFormData = new FormData()
      bodyFormData.set('id', this.order.id)
      bodyFormData.set('bill', this.order.bill)
      bodyFormData.set('time', this.order.time)
      let url = '/pay-server/api/Pay/ali'
      const newTab = window.open()
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        const div = document.createElement('div')
        newTab.console.log(response.data)
        div.innerHTML = response.data // html code
        newTab.document.body.appendChild(div)
        newTab.document.forms[0].submit()
      })
    }
  }
}
</script>

<style scoped>

</style>
