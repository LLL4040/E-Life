<template>
  <div>
    <div align="center">
      <el-input v-model="search" maxLength="25" size="medium" style="width: 300px" suffix-icon="el-icon-search" placeholder="输入用户名关键字搜索"/>
    </div>
    <div style="padding-top: 20px;">
      <el-card style="padding-left: 20px">
        <el-table :data="userData.filter(data => !search || data.username.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
          <el-table-column prop="friend" label="姓名" align="center"></el-table-column>
          <el-table-column prop="phone" label="手机号" align="center"></el-table-column>
          <el-table-column prop="email" label="邮箱" align="center"></el-table-column>
          <el-table-column prop="community" label="小区" align="center"></el-table-column>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button size="medium" type="danger" icon="el-icon-delete" circle @click="handleDelete(scope.row)"></el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MyFriend',
  data () {
    return {
      search: '',
      userInfo: {
        community: '',
        communityId: 0,
        username: '',
        email: '',
        phone: ''
      },
      userData: []
    }
  },
  mounted () {
    this.loadData()
    this.getFriend()
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
    getFriend () {
      let bodyFormData = new FormData()
      bodyFormData.set('username', this.userInfo.username)
      let url = '/user-server/api/friend/friendList'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.length > 0 && response.data[0].login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          this.userData = response.data
        }
      })
    },
    handleDelete (row) {
      let bodyFormData = new FormData()
      bodyFormData.set('username', this.userInfo.username)
      bodyFormData.set('friend', row.friend)
      let url = '/user-server/api/friend/deleteFriend'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          if (response.data.delete === 1) {
            this.$alert('删除成功！')
            this.getFriend()
          } else {
            this.$alert('删除失败！')
          }
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
