<template>
  <div>
    <div style="padding-top: 20px;">
      <el-card style="padding-left: 20px">
        <div slot="header" class="clearfix" style="padding-right: 20px;">
          <span style="font-size: 16px;">已发送邮包通知列表</span>
          <el-button style="float: right;" size="medium" type="primary" icon="el-icon-plus" circle @click="dialogFormVisible = true"></el-button>
        </div>
        <el-table :data="messages" style="width: 100%">
          <el-table-column prop="id" label="编号" align="center"></el-table-column>
          <el-table-column prop="time" label="时间" align="center"></el-table-column>
          <el-table-column prop="managerName" label="代签人" align="center"></el-table-column>
          <el-table-column prop="status" label="状态" align="center">
            <template slot-scope="scope">
              <el-button v-if="scope.row.status === 0" type="primary" plain size="small">待取</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
      <div class="block" align="center" >
        <el-pagination @current-change="handleCurrentChange" :current-page.sync="pageNum" :page-count="pageSize" :pager-count="7" layout="prev, pager, next, jumper"></el-pagination>
      </div>
    </div>
    <el-dialog title="发送通知" :visible.sync="dialogFormVisible">
      <el-form :model="sendM">
        <el-form-item label="签收时间">
          <el-date-picker v-model="sendM.time" type="datetime" placeholder="选择日期时间" value-format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
        </el-form-item>
        <el-form-item label="接收人用户名">
          <el-input maxLength="20" v-model="sendM.user"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="sendMessage()">发 送</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'AdminParcel',
  data () {
    return {
      userInfo: {
        community: '',
        communityId: 0,
        username: '',
        email: '',
        phone: ''
      },
      search: -1,
      dialogFormVisible: false,
      messages: [],
      sendM: {
        time: '',
        manager: '',
        user: ''
      },
      pageNum: 1,
      pageSize: 1
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
      bodyFormData.set('communityId', this.userInfo.communityId)
      bodyFormData.set('page', this.pageNum)
      let url = '/package-server/api/Package/findHistoryManager'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.length > 0 && response.data[0].login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          this.messages = response.data
          console.log(this.messages)
          this.pageSize = response.data[response.data.length - 1].pageNum
          this.messages.pop()
        }
      })
    },
    handleCurrentChange (val) {
      this.pageNum = val
      this.loadP()
      this.$forceUpdate()
    },
    sendMessage () {
      this.dialogFormVisible = false
      let bodyFormData = new FormData()
      bodyFormData.set('communityId', this.userInfo.communityId)
      bodyFormData.set('managerName', this.userInfo.username)
      bodyFormData.set('time', this.sendM.time)
      bodyFormData.set('username', this.sendM.user)
      let url = '/package-server/api/Package/savePackage'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data) {
          this.loadP()
          this.$forceUpdate()
        } else {
          this.$alert('发送失败！请重新登录再试')
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
