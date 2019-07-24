<template>
  <div>
    <div align="center">
      <el-input v-model="search" size="medium" style="width: 350px" suffix-icon="el-icon-search" placeholder="输入用户名关键字查找用户账单"/>
    </div>
    <el-row :gutter="10" style="padding-top: 20px">
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span style="font-size: 16px;">停车费账单</span>
            <el-button style="float: right;" size="medium" type="primary" icon="el-icon-plus" circle @click="dialogFormVisible1 = true"></el-button>
          </div>
          <el-table :data="bill.filter(data => ((data.status === -1 || data.status === -11) && (!search || data.username.toLowerCase().includes(search.toLowerCase()))))" style="width: 100%">
            <el-table-column label="时间" prop="time" align="center"></el-table-column>
            <el-table-column label="金额" prop="bill" align="center"></el-table-column>
            <el-table-column label="用户名" prop="username" align="center"></el-table-column>
          </el-table>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span style="font-size: 16px;">物业费账单</span>
            <el-button style="float: right;" size="medium" type="primary" icon="el-icon-plus" circle @click="dialogFormVisible2 = true"></el-button>
          </div>
          <el-table :data="bill.filter(data => ((data.status === -2 || data.status === -12) && (!search || data.username.toLowerCase().includes(search.toLowerCase()))))" style="width: 100%">
            <el-table-column label="时间" prop="time" align="center"></el-table-column>
            <el-table-column label="金额" prop="bill" align="center"></el-table-column>
            <el-table-column label="用户名" prop="username" align="center"></el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>
    <div class="block" align="center" >
      <el-pagination @current-change="handleCurrentChange" :current-page.sync="pageNum" :page-count="pageSize" :pager-count="7" layout="prev, pager, next, jumper"></el-pagination>
    </div>
    <el-dialog title="添加停车费账单" :visible.sync="dialogFormVisible1">
      <el-form :model="newT">
        <el-form-item label="截止时间">
          <el-date-picker v-model="newT.time" type="datetime" placeholder="选择日期时间" value-format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="newT.username"></el-input>
        </el-form-item>
        <el-form-item label="金额">
          <el-input v-model="newT.amount"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="releaseT()">发 布</el-button>
      </div>
    </el-dialog>
    <el-dialog title="添加物业费账单" :visible.sync="dialogFormVisible2">
      <el-form :model="newW">
        <el-form-item label="截止时间">
          <el-date-picker v-model="newW.time" type="datetime" placeholder="选择日期时间" value-format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="newW.username"></el-input>
        </el-form-item>
        <el-form-item label="金额">
          <el-input v-model="newW.amount"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible2 = false">取 消</el-button>
        <el-button type="primary" @click="releaseW()">发 布</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'AdminBillW',
  data () {
    return {
      userInfo: {
        community: '',
        communityId: 0,
        username: '',
        email: '',
        phone: ''
      },
      search: '',
      dialogFormVisible1: false,
      dialogFormVisible2: false,
      bill: [],
      newT: {
        time: '',
        amount: '',
        username: ''
      },
      newW: {
        time: '',
        amount: '',
        username: ''
      },
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
      bodyFormData.set('communityId', this.userInfo.communityId)
      bodyFormData.set('page', this.pageNum)
      let url = '/pay-server/api/Pay/findUnPayHistory'
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
    releaseT () {
      this.dialogFormVisible1 = false
      let bodyFormData = new FormData()
      bodyFormData.set('communityId', this.userInfo.communityId)
      bodyFormData.set('managerName', this.userInfo.username)
      bodyFormData.set('username', this.newT.username)
      bodyFormData.set('time', this.newT.time)
      bodyFormData.set('bill', this.newT.amount)
      bodyFormData.set('status', -1)
      let url = '/pay-server/api/Pay/savePay'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data) {
          this.loadBill()
        } else {
          this.$alert('添加账单失败！请重新登录再试')
        }
      })
    },
    releaseW () {
      this.dialogFormVisible2 = false
      let bodyFormData = new FormData()
      bodyFormData.set('communityId', this.userInfo.communityId)
      bodyFormData.set('managerName', this.userInfo.username)
      bodyFormData.set('username', this.newW.username)
      bodyFormData.set('time', this.newW.time)
      bodyFormData.set('bill', this.newW.amount)
      bodyFormData.set('status', -2)
      let url = '/pay-server/api/Pay/savePay'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data) {
          this.loadBill()
        } else {
          this.$alert('添加账单失败！请重新登录再试')
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
