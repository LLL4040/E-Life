<template>
  <div>
    <div align="left">
      <el-button style="margin-top: -20px" size="medium" type="primary" plain icon="el-icon-refresh" circle @click="refresh()"></el-button>
    </div>
    <div align="center">
      <el-input v-model="search" size="medium" style="width: 300px" suffix-icon="el-icon-search" placeholder="输入用户名关键字搜索"/>
    </div>
    <div style="padding-top: 20px">
      <el-card style="padding-left: 20px; padding-right: 20px;">
        <div slot="header" class="clearfix">
          <span style="font-size: 16px;">待审核</span>
        </div>
        <el-table :data="managerList" style="width: 100%">
          <el-table-column label="用户名" prop="username" align="center"></el-table-column>
          <el-table-column label="手机号" prop="phone" align="center"></el-table-column>
          <el-table-column label="邮箱" prop="email" align="center"></el-table-column>
          <el-table-column label="状态" prop="status" align="center">
            <template slot-scope="scope">
              <el-button v-if="scope.row.status === 1" type="success" plain size="small">已通过</el-button>
              <el-button v-if="scope.row.status === 0" type="primary" plain size="small">未处理</el-button>
              <el-button v-if="scope.row.status === -1" type="danger" plain size="small">未通过</el-button>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="150px" align="center">
            <template slot-scope="scope">
              <el-button v-if="scope.row.status === 0" size="mini" type="primary" plain @click="handleA(scope.row)">同意</el-button>
              <el-button v-if="scope.row.status === 0" size="mini" type="danger" plain @click="handleR(scope.row)">拒绝</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ManageAdmin',
  data () {
    return {
      userInfo: {
        community: '',
        communityId: 0,
        username: '',
        email: '',
        phone: '',
        final: ''
      },
      managerList: []
    }
  },
  mounted () {
    this.refresh()
  },
  methods: {
    refresh () {
      this.loadData()
      this.loadManager()
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
      this.userInfo.final = sessionStorage.getItem('final')
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
    loadManager () {
      let bodyFormData = new FormData()
      bodyFormData.set('id', this.userInfo.communityId)
      let url = '/user-server/api/user/'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.length > 0 && response.data[0].login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          this.managerList = response.data
        }
      })
    },
    handleA (row) {
      let bodyFormData = new FormData()
      bodyFormData.set('username', row.username)
      bodyFormData.set('role', 1)
      let url = '/user-server/api/user/changeManagerStatus'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          if (response.data.exists === 0) {
            this.$alert('该用户不存在！')
          } else if (response.data.changeRole === 1) {
            this.$alert('同意成功！')
          } else {
            this.$alert('操作失败！请重新登录后再试')
          }
        }
      })
    },
    handleR (row) {
      let bodyFormData = new FormData()
      bodyFormData.set('username', row.username)
      bodyFormData.set('role', -1)
      let url = '/user-server/api/user/changeManagerStatus'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          if (response.data.exists === 0) {
            this.$alert('该用户不存在！')
          } else if (response.data.changeRole === 1) {
            this.$alert('拒绝成功！')
          } else {
            this.$alert('操作失败！请重新登录后再试')
          }
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
