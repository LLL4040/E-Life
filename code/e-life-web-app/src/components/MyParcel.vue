<template>
  <div>
    <div align="center">
      <el-input v-model="search" size="medium" style="width: 400px" suffix-icon="el-icon-search" placeholder="输入0或1筛选包裹状态-(0表示未取/1表示已取)"/>
    </div>
    <div style="padding-top: 20px;">
      <el-card style="padding-left: 20px">
        <el-table :data="messages.filter(data => !search || data.status === search)" style="width: 100%">
          <el-table-column prop="time" label="时间" align="center"></el-table-column>
          <el-table-column prop="managerName" label="代签人" align="center"></el-table-column>
          <el-table-column label="状态" align="center">
            <template slot-scope="scope">
              <el-button v-if="scope.row.status === 1" type="success" plain size="small">已取</el-button>
              <el-button v-if="scope.row.status === 0" type="primary" plain size="small">待取</el-button>
            </template>
          </el-table-column>
          <el-table-column label="点击确认领取" align="center">
            <template slot-scope="scope">
              <el-button v-if="scope.row.status === 0" size="medium" type="danger" plain icon="el-icon-check" circle @click="handleDelete(scope.$index, scope.row)"></el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MyParcel',
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
      messages: [{
        id: '',
        time: '',
        managerName: '',
        status: null
      }]
    }
  },
  mounted () {
    this.loadData()
    this.loadP()
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
    loadP () {
      let bodyFormData = new FormData()
      bodyFormData.set('username', this.userInfo.username)
      let url = '/package-server/api/Package/findNew'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        this.messages = response.data
      })
    }
  }
}
</script>

<style scoped>

</style>
