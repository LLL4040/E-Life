<template>
  <div>
    <div align="left">
      <el-button style="margin-top: -20px" size="medium" type="primary" plain icon="el-icon-refresh" circle @click="refresh()"></el-button>
    </div>
    <div align="center">
      <el-button size="medium" type="success" plain @click="autoAddT()">一键生成停车费账单</el-button>
      <el-input v-model="search" size="medium" style="width: 350px" suffix-icon="el-icon-search" placeholder="输入用户名关键字查找用户账单"/>
      <el-button size="medium" type="success" plain @click="dialogFormVisible3 = true">一键生成物业费账单</el-button>
    </div>
    <el-row :gutter="10" style="padding-top: 20px">
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span style="font-size: 16px;">停车费账单</span>
            <el-button style="float: right;" size="medium" type="primary" icon="el-icon-plus" circle @click="dialogFormVisible1 = true"></el-button>
            <el-tooltip class="item" effect="light" content="点击修改收费标准" placement="top">
              <el-button size="medium" type="primary" @click="dialogFormVisible = true" style="float: right" plain>
                收费标准：小车{{ park.Amoney }}元/月&nbsp;&nbsp;电动车{{ park.Bmoney }}元/月
              </el-button>
            </el-tooltip>
          </div>
          <el-table :data="bill.filter(data => typeof data.time !== 'undefined' && ((data.status === -1 || data.status === -11) && (!search || data.username.toLowerCase().includes(search.toLowerCase()))))" style="width: 100%">
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
          <el-table :data="bill.filter(data => typeof data.time !== 'undefined' && ((data.status === -2 || data.status === -12) && (!search || data.username.toLowerCase().includes(search.toLowerCase()))))" style="width: 100%">
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
        <el-form-item label="接收人用户名">
          <el-select v-model="newT.username" filterable placeholder="输入用户名关键字搜索">
            <el-option
              v-for="item in userList"
              :key="item.username"
              :label="item.username"
              :value="item.username">
              <span style="float: left">{{ item.username }}</span>
              <!--              <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>-->
            </el-option>
          </el-select>
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
        <el-form-item label="接收人用户名">
          <el-select v-model="newW.username" filterable placeholder="输入用户名关键字搜索">
            <el-option
              v-for="item in userList"
              :key="item.username"
              :label="item.username"
              :value="item.username">
              <span style="float: left">{{ item.username }}</span>
              <!--              <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>-->
            </el-option>
          </el-select>
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
    <el-dialog title="编辑停车收费方案" :visible.sync="dialogFormVisible">
      <el-form :model="park">
        <el-form-item label="小车包月费">
          <el-input v-model="park.Amoney"></el-input>
        </el-form-item>
        <el-form-item label="电动车包月费">
          <el-input v-model="park.Bmoney"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="handleModifyP()">保 存</el-button>
        <el-button @click="dialogFormVisible = false">取 消</el-button>
      </div>
    </el-dialog>
    <el-dialog title="设置物业费标准" :visible.sync="dialogFormVisible3">
      <el-form :model="man">
        <el-form-item label="费用参数">
          <el-input-number v-model="man.money" :precision="2" :step="0.1"></el-input-number>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="autoAddW()">生成订单</el-button>
        <el-button @click="dialogFormVisible3 = false">取 消</el-button>
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
      dialogFormVisible: false,
      dialogFormVisible1: false,
      dialogFormVisible2: false,
      dialogFormVisible3: false,
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
      pageSize: 1,
      userList: [],
      park: {
        Amoney: 0,
        Bmoney: 0
      },
      man: {
        money: 0
      }
    }
  },
  mounted () {
    this.refresh()
  },
  methods: {
    refresh () {
      this.loadData()
      this.loadBill()
      this.loadUsers()
      this.loadPark()
      this.$forceUpdate()
    },
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
    loadUsers () {
      let bodyFormData = new FormData()
      bodyFormData.set('communityId', this.userInfo.communityId)
      let url = '/user-server/api/user/getUsers'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.length > 0 && response.data[0].login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          this.userList = response.data
        }
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
    loadPark () {
      let bodyFormData = new FormData()
      bodyFormData.set('communityId', this.userInfo.communityId)
      let url = '/pay-server/api/Pay/getParkPlan'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        for (let x = 0; x < response.data.length; x++) {
          if (response.data[x].type === 'A') {
            this.park.Amoney = response.data[x].montyPay
          } else {
            this.park.Bmoney = response.data[x].montyPay
          }
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
          this.$forceUpdate()
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
          this.$forceUpdate()
        } else {
          this.$alert('添加账单失败！请重新登录再试')
        }
      })
    },
    handleModifyP () {
      this.dialogFormVisible = false
      let bodyFormData = new FormData()
      bodyFormData.set('Amoney', this.park.Amoney)
      bodyFormData.set('Bmoney', this.park.Bmoney)
      bodyFormData.set('communityId', this.userInfo.communityId)
      let url = '/pay-server/api/Pay/saveParkPlan'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } }
      }
      ).then(response => {
        if (response.data) {
          this.loadPark()
        } else {
          this.$alert('修改收费标准失败！')
        }
      })
    },
    autoAddT () {
      let bodyFormData = new FormData()
      bodyFormData.set('communityId', this.userInfo.communityId)
      let url = '/pay-server/api/Pay/calParking'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } }
      }
      ).then(response => {
        if (response.data) {
          this.autoAddT2()
        } else {
          this.$alert('一键生成订单失败！')
        }
      })
    },
    autoAddT2 () {
      let bodyFormData = new FormData()
      bodyFormData.set('managerName', this.userInfo.username)
      bodyFormData.set('communityId', this.userInfo.communityId)
      let url = '/pay-server/api/Pay/AutoSaveParkPay'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } }
      }
      ).then(response => {
        if (response.data) {
          this.loadBill()
        } else {
          this.$alert('一键生成订单失败！')
        }
      })
    },
    autoAddW () {
      this.dialogFormVisible3 = false
      let bodyFormData = new FormData()
      bodyFormData.set('money', this.man.money)
      bodyFormData.set('communityId', this.userInfo.communityId)
      let url = '/pay-server/api/Pay/calManMoney'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } }
      }
      ).then(response => {
        if (response.data) {
          this.autoAddW2()
        } else {
          this.$alert('一键生成订单失败！')
        }
      })
    },
    autoAddW2 () {
      let bodyFormData = new FormData()
      bodyFormData.set('managerName', this.userInfo.username)
      bodyFormData.set('communityId', this.userInfo.communityId)
      let url = '/pay-server/api/Pay/AutoSaveManPay'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } }
      }
      ).then(response => {
        if (response.data) {
          this.loadBill()
        } else {
          this.$alert('一键生成订单失败！')
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
