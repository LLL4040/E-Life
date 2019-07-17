<template>
  <div>
    <el-row :gutter="10" style="padding-top: 20px">
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>我的订单</span>
          </div>
          <el-table :data="pay" style="width: 100%">
            <el-table-column label="订单号" prop="id" align="center"></el-table-column>
            <el-table-column label="金额" prop="bill" align="center"></el-table-column>
            <el-table-column label="订单时间" prop="time" align="center"></el-table-column>
            <el-table-column label="状态" prop="status" align="center">
              <template slot-scope="scope">
                <el-button  plain size="small" @click="submit(scope.row)">支付宝缴费</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'MYPay',
  data () {
    return {
      pay: [],
      userInfo: {
        community: '',
        communityId: 0,
        username: '',
        email: '',
        phone: ''
      }
    }
  },
  mounted () {
    this.loadData()
    this.loadPay()
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
    loadPay () {
      let bodyFormData = new FormData()
      bodyFormData.set('username', this.userInfo.username)
      let url = '/pay-server/api/Pay/getOrders'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        this.pay = response.data
        console.log(response.data)
      })
    },
    submit (item) {
      let bodyFormData = new FormData()
      bodyFormData.set('id', item.id)
      bodyFormData.set('bill', item.bill)
      bodyFormData.set('time', item.time)
      let url = '/pay-server/api/Pay/ali'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        const div = document.createElement('div')
        div.innerHTML = response.data // html code
        document.body.appendChild(div)
        document.forms[0].submit()
      })
    }
  }
}
</script>

<style scoped>

</style>
