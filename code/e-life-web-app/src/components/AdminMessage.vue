<template>
  <div>
    <div align="center">
      <el-input v-model="search" size="medium" style="width: 300px" suffix-icon="el-icon-search" placeholder="输入用户名关键字搜索"/>
    </div>
    <div style="padding-top: 20px">
      <el-card style="padding-left: 20px; padding-right: 20px;">
        <div slot="header" class="clearfix">
          <span>已发送通知</span>
          <el-button style="float: right;" size="medium" type="primary" icon="el-icon-plus" circle @click="dialogFormVisible = true"></el-button>
        </div>
        <el-table :data="message.filter(data => !search || data.user.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="left" inline class="demo-table-expand">
                <el-form-item label="通知内容">
                  <span>{{ props.row.noticeContent }}</span>
                </el-form-item>
              </el-form>
            </template>
          </el-table-column>
          <el-table-column prop="noticeTime" label="时间" align="center"></el-table-column>
          <el-table-column prop="managerName" label="发送人" align="center"></el-table-column>
          <el-table-column prop="username" label="接收人" align="center"></el-table-column>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button size="medium" type="danger" icon="el-icon-delete" circle @click="handleDelete(scope.$index, scope.row)"></el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </div>
    <el-dialog title="发送通知" :visible.sync="dialogFormVisible">
      <el-form :model="newMessage">
        <el-form-item label="接收人用户名">
          <el-input v-model="newMessage.user"></el-input>
        </el-form-item>
        <el-form-item label="通知内容">
          <el-input v-model="newMessage.content" autocomplete="off" type="textarea" :autosize="{ minRows: 3, maxRows: 5}"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="sendM()">发 送</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'AdminMessage',
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
      message: [{
        time: 'xxx',
        manager: '996',
        user: '233',
        content: '快交钱啊啊啊'
      }],
      newMessage: {
        manager: '',
        user: '',
        content: ''
      }
    }
  },
  mounted () {
    this.loadData()
    this.loadMessage()
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
      bodyFormData.set('managerName', this.userInfo.username)
      bodyFormData.set('pageNumber', 1)
      bodyFormData.set('pageSize', 1)
      let url = '/news-server/api/notice/ManagerFindNotice'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        this.message = response.data
        console.log(this.message)
      })
    },
    sendM () {
      this.dialogFormVisible = false
    },
    handleDelete () {
    }
  }
}
</script>

<style scoped>

</style>
