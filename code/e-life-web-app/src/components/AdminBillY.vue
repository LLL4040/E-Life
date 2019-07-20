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
          <el-table :data="bill.filter(data => (data.status === 1 && (!search || data.username.toLowerCase().includes(search.toLowerCase()))))" style="width: 100%">
            <el-table-column label="时间" prop="time" align="center"></el-table-column>
            <el-table-column label="金额" prop="amount" align="center"></el-table-column>
            <el-table-column label="用户名" prop="username" align="center"></el-table-column>
          </el-table>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span style="font-size: 16px;">物业费账单</span>
          </div>
          <el-table :data="bill.filter(data => (data.status === 2 && (!search || data.username.toLowerCase().includes(search.toLowerCase()))))" style="width: 100%">
            <el-table-column label="时间" prop="time" align="center"></el-table-column>
            <el-table-column label="金额" prop="amount" align="center"></el-table-column>
            <el-table-column label="用户名" prop="username" align="center"></el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>
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
      bill: []
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
      let url = '/pay-server/api/Pay/findPayHistory'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        this.bill = response.data
        console.log(response.data)
      })
    }
  }
}
</script>

<style scoped>

</style>
