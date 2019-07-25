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
          </div>
          <el-table :data="bill.filter(data => typeof data.time !== 'undefined' && (data.status === 1 && (!search || data.username.toLowerCase().includes(search.toLowerCase()))))" style="width: 100%">
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
          </div>
          <el-table :data="bill.filter(data => typeof data.time !== 'undefined' && (data.status === 2 && (!search || data.username.toLowerCase().includes(search.toLowerCase()))))" style="width: 100%">
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
  </div>
</template>

<script>
export default {
  name: 'AdminBillY',
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
      bill: [],
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
      let url = '/pay-server/api/Pay/findPayHistory'
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
      this.$forceUpdate()
    }
  }
}
</script>

<style scoped>

</style>
