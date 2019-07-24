<template>
  <div>
    <div align="center">
      <el-input v-model="search" size="medium" style="width: 300px" placeholder="输入用户名关键字搜索"/>
      <el-button icon="el-icon-search" size="medium" type="primary" @click="loadUser()"></el-button>
    </div>
    <div style="padding-top: 20px;">
      <el-card style="padding-left: 20px">
        <el-table :data="userData.filter(data => !search || data.username.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
          <el-table-column prop="username" label="姓名" align="center"></el-table-column>
          <el-table-column prop="community" label="小区" align="center"></el-table-column>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button size="mini" type="danger" icon="el-icon-plus" circle @click="handleAdd1(scope.row)"></el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </div>
    <el-dialog title="添加好友" :visible.sync="dialogFormVisible">
      <el-form :model="myApply">
        <el-form-item label="留言">
          <el-input v-model="myApply.content" autocomplete="off" type="textarea" :autosize="{ minRows: 1, maxRows: 3}"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleAdd2()">提 交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'MyFriendAdd',
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
      dialogFormVisible: false,
      myApply: {
        myName: '',
        hisName: '',
        content: ''
      },
      userData: []
    }
  },
  mounted () {
    this.loadData()
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
    loadUser () {
      let bodyFormData = new FormData()
      bodyFormData.set('username', this.search)
      let url = '/user-server/api/friend/friendSearchList'
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
    handleAdd1 (row) {
      this.myApply.hisName = row.username
      this.dialogFormVisible = true
    },
    handleAdd2 () {
      this.dialogFormVisible = false
      let bodyFormData = new FormData()
      bodyFormData.set('username', this.userInfo.username)
      bodyFormData.set('friend', this.myApply.hisName)
      bodyFormData.set('content', this.myApply.content)
      let url = '/user-server/api/friend/sendFriendRequest'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          if (response.data.send === 1) {
            this.$alert('添加成功！')
          } else {
            this.$alert('添加失败！')
          }
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
