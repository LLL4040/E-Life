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
          <span style="font-size: 16px;">已发送通知</span>
          <el-button style="float: right;" size="medium" type="primary" icon="el-icon-plus" circle @click="dialogFormVisible = true"></el-button>
        </div>
        <el-table :data="message.filter(data => typeof data.time !== 'undefined' && (!search || data.receiver.toLowerCase().includes(search.toLowerCase())))" style="width: 100%">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="left" inline class="demo-table-expand">
                <el-form-item label="通知内容">
                  <span>{{ props.row.content }}</span>
                </el-form-item>
              </el-form>
            </template>
          </el-table-column>
          <el-table-column prop="time" label="时间" align="center"></el-table-column>
          <el-table-column prop="managename" label="发送人" align="center"></el-table-column>
          <el-table-column prop="receiver" label="接收人" align="center"></el-table-column>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button size="medium" type="danger" icon="el-icon-delete" circle @click="handleDelete(scope.row)"></el-button>
            </template>
          </el-table-column>
        </el-table>
        <div class="block" align="center" >
          <el-pagination @current-change="handleCurrentChange" :current-page.sync="pageNum" :page-count="total" :pager-count="5" layout="prev, pager, next, jumper"></el-pagination>
        </div>
      </el-card>
    </div>
    <el-dialog title="发送通知" :visible.sync="dialogFormVisible">
      <el-form :model="newMessage">
        <el-form-item label="接收人用户名">
          <el-select v-model="newMessage.user" filterable placeholder="输入用户名关键字搜索">
            <el-option
              v-for="item in userList"
              :key="item.username"
              :label="item.username"
              :value="item.username">
              <span style="float: left">{{ item.username }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="通知内容">
          <el-input v-model="newMessage.content" maxLength="512" autocomplete="off" type="textarea" :autosize="{ minRows: 3, maxRows: 5}"></el-input>
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
      message: [],
      newMessage: {
        manager: '',
        user: '',
        content: ''
      },
      pageNum: 1,
      pageSize: 10,
      total: 1,
      userList: [
        {
          username: '所有用户'
        }
      ]
    }
  },
  mounted () {
    this.refresh()
  },
  methods: {
    refresh () {
      this.loadData()
      this.loadMessage()
      this.loadUsers()
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
      bodyFormData.set('pageNumber', this.pageNum)
      bodyFormData.set('pageSize', this.pageSize)
      let url = '/news-server/api/notice/ManagerFindNotice'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.length > 0 && response.data[0].login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          this.message = response.data
          this.total = response.data[0].pageNum
        }
      })
    },
    loadUsers () {
      let bodyFormData = new FormData()
      bodyFormData.set('communityId', this.userInfo.communityId)
      let url = '/user-server/api/user/getUsers'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.length > 0 && response.data[0].login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          this.userList = [{ username: '所有用户' }]
          this.userList = this.userList.concat(response.data)
        }
      })
    },
    handleCurrentChange (val) {
      this.pageNum = val
      this.loadMessage()
      this.$forceUpdate()
    },
    sendM () {
      this.dialogFormVisible = false
      let bodyFormData = new FormData()
      bodyFormData.set('content', this.newMessage.content)
      bodyFormData.set('managerName', this.userInfo.username)
      bodyFormData.set('communityId', this.userInfo.communityId)
      bodyFormData.set('username', this.newMessage.user)
      if (this.newMessage.user === '所有用户') {
        bodyFormData.set('isMass', 1)
      } else {
        bodyFormData.set('isMass', 0)
      }
      let url = '/news-server/api/notice/addNotice'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          if (response.data.addNotice === '1') {
            this.loadMessage()
            this.$forceUpdate()
          } else {
            this.$alert('发送通知失败！')
          }
        }
      })
    },
    handleDelete (row) {
      let bodyFormData = new FormData()
      bodyFormData.set('noticeId', row.id)
      let url = '/news-server/api/notice/deleteOneNotice'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          if (response.data.deleteOneNotice === '1') {
            this.loadMessage()
            this.$forceUpdate()
          } else {
            this.$alert('删除通知失败！')
          }
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
