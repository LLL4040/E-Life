<template>
  <div>
    <div style="padding-top: 20px;">
      <el-card style="padding-left: 20px">
        <el-table :data="messages" style="width: 100%">
          <el-table-column prop="noticeTime" label="时间" align="center"></el-table-column>
          <el-table-column prop="managerName" label="发送人" align="center"></el-table-column>
          <el-table-column prop="noticeContent" label="内容" align="center"></el-table-column>
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
  name: 'MyMessage',
  data () {
    return {
      userInfo: {
        community: '',
        communityId: 0,
        username: '',
        email: '',
        phone: ''
      },
      messages: [{
        noticeId: '',
        noticeTime: '',
        managerName: '',
        noticeContent: ''
      }]
    }
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
    loadMessage () {
      let bodyFormData = new FormData()
      bodyFormData.set('username', this.userInfo.username)
      let url = '/news-server/api/notice/findMyNotice'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        this.messages = response.data
      })
    },
    handleDelete (row) {
      let bodyFormData = new FormData()
      bodyFormData.set('username', this.userInfo.username)
      bodyFormData.set('noticeId', row.noticeId)
      let url = '/news-server/api/notice/deleteMyOneNotice'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.deleteMyOneNotice === '1') {
          this.loadMessage()
        } else {
          this.$alert('删除失败！')
        }
      })
    }
  },
  mounted () {
    this.loadData()
    this.loadMessage()
  }
}
</script>

<style scoped>

</style>
