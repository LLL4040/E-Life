<template>
  <div>
    <div align="center">
      <el-input v-model="search" size="medium" style="width: 350px" suffix-icon="el-icon-search" placeholder="输入0或1筛选状态-(1:已缴费、0:未缴费)"/>
    </div>
    <el-row :gutter="10" style="padding-top: 20px">
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>停车费账单</span>
          </div>
          <el-table :data="parkBill.filter(data => !search || data.status === search)" style="width: 100%">
            <el-table-column label="时间" prop="time" align="center"></el-table-column>
            <el-table-column label="金额" prop="amount" align="center"></el-table-column>
            <el-table-column label="状态" prop="status" align="center">
              <template slot-scope="scope">
                <el-button v-if="scope.row.status === '1'" type="success" plain size="small">已缴费</el-button>
                <el-button v-if="scope.row.status === '0'" type="danger" plain size="small">未缴费</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>物业费账单</span>
          </div>
          <el-table :data="propertyBill.filter(data => !search || data.status === search)" style="width: 100%">
            <el-table-column label="时间" prop="time" align="center"></el-table-column>
            <el-table-column label="金额" prop="amount" align="center"></el-table-column>
            <el-table-column label="状态" prop="status" align="center">
              <template slot-scope="scope">
                <el-button v-if="scope.row.status === '1'" type="success" plain size="small">已缴费</el-button>
                <el-button v-if="scope.row.status === '0'" type="danger" plain size="small">未缴费</el-button>
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
  name: 'MyBill',
  data () {
    return {
      search: '',
      parkBill: [{
        time: '1',
        amount: '1',
        status: '1'
      }],
      propertyBill: [{
        time: '1',
        amount: '1',
        status: '0'
      }],
      bill: [],
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
      bodyFormData.set('username', this.userInfo.communityId)
      let url = '/pay-server/api/Pay/findHistory'
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
