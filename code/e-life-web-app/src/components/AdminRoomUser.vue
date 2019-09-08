<template>
  <div>
    <div align="left">
      <el-button style="margin-top: -20px" size="medium" type="primary" plain icon="el-icon-refresh" circle @click="refresh()"></el-button>
    </div>
    <div align="center" style="width: 500px; margin-left: 250px;">
      <el-input placeholder="请输入需要查找的房间号，如：010101" v-model="room" class="input-with-select">
        <el-select v-model="type" slot="prepend" filterable placeholder="输入户型关键字搜索" style="width: 180px;">
          <el-option
            v-for="item in roomspace"
            :key="item.type"
            :label="item.type"
            :value="item.type">
            <span style="float: left">{{ item.type }}</span>
          </el-option>
        </el-select>
        <el-button slot="append" icon="el-icon-search" @click="loadUserDetail()"></el-button>
      </el-input>
    </div>
    <div style="padding-top: 20px">
      <el-card style="padding-left: 20px; padding-right: 20px;">
        <el-table :data="userDetail" style="width: 100%">
          <el-table-column prop="username" label="居民用户名" align="center"></el-table-column>
          <el-table-column prop="type" label="户型" align="center"></el-table-column>
          <el-table-column prop="room" label="房间号" align="center"></el-table-column>
          <el-table-column prop="parking" label="停车方案" align="center"></el-table-column>
<!--          <el-table-column prop="pmoney" label="停车费" align="center"></el-table-column>-->
<!--          <el-table-column prop="mmoney" label="管理费" align="center"></el-table-column>-->
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button size="medium" type="primary" icon="el-icon-edit" circle @click="handleEdit(scope.row)"></el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </div>
    <el-dialog title="编辑信息" :visible.sync="dialogFormVisible">
      <el-form :model="user">
        <el-form-item label="居民用户名">
          <el-select v-model="user.username" filterable placeholder="输入用户名关键字搜索">
            <el-option
              v-for="item in userList"
              :key="item.username"
              :label="item.username"
              :value="item.username">
              <span style="float: left">{{ item.username }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="户型">
          <el-input v-model="user.type" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="房间号">
          <el-input v-model="user.room" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="停车方案(AB后面的数字只能是0-9)">
          <el-input v-model="user.parking"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="handleModify1()">绑定用户</el-button>
        <el-button type="primary" @click="handleModify2()">绑定停车方案</el-button>
        <el-button type="primary" @click="handleModify()">一键绑定</el-button>
        <el-button @click="dialogFormVisible = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'AdminRoomUser',
  data () {
    return {
      userInfo: {
        community: '',
        communityId: 0,
        username: '',
        email: '',
        phone: ''
      },
      type: '',
      room: '',
      dialogFormVisible: false,
      userDetail: [],
      user: {
        username: '',
        communityId: 0,
        type: '',
        room: '',
        parking: '',
        pmoney: 0,
        mmoney: 0
      },
      userList: [],
      roomspace: []
    }
  },
  mounted () {
    this.refresh()
  },
  methods: {
    refresh () {
      this.loadData()
      this.loadUsers()
      this.loadRoomspace()
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
        config: { headers: { 'Content-type': 'multipart/form-data' } }
      }
      ).then(response => {
        this.userInfo.community = response.data.community
        sessionStorage.setItem('community', this.userInfo.community)
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
          this.userList = response.data
        }
      })
    },
    loadUserDetail () {
      if (this.type === '' || this.room === '') {
        this.$alert('请完整填写信息！')
      } else {
        let bodyFormData = new FormData()
        bodyFormData.set('communityId', this.userInfo.communityId)
        bodyFormData.set('type', this.type)
        bodyFormData.set('room', this.room)
        let url = '/pay-server/api/Pay/getRoomByRoomNumber'
        this.$axios({
          method: 'post',
          url: url,
          data: bodyFormData,
          config: { headers: { 'Content-type': 'multipart/form-data' } }
        }
        ).then(response => {
          this.userDetail = []
          this.userDetail.push(response.data)
          this.$forceUpdate()
        })
      }
    },
    loadRoomspace () {
      let bodyFormData = new FormData()
      bodyFormData.set('communityId', this.userInfo.communityId)
      let url = '/pay-server/api/Pay/getRoomSpace'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.length > 0 && response.data[0].login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          this.roomspace = response.data
        }
      })
    },
    handleEdit (row) {
      this.user = row
      this.dialogFormVisible = true
    },
    handleModify1 () {
      this.dialogFormVisible = false
      let bodyFormData = new FormData()
      bodyFormData.set('username', this.user.username)
      bodyFormData.set('type', this.user.type)
      bodyFormData.set('room', this.user.room)
      bodyFormData.set('communityId', this.userInfo.communityId)
      let url = '/pay-server/api/Pay/saveUsername'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } }
      }
      ).then(response => {
        if (response.data) {
          this.loadUserDetail()
          this.$forceUpdate()
        } else {
          this.$alert('绑定用户失败！')
        }
      })
    },
    handleModify2 () {
      this.dialogFormVisible = false
      let bodyFormData = new FormData()
      bodyFormData.set('username', this.user.username)
      bodyFormData.set('type', this.user.type)
      bodyFormData.set('parking', this.user.parking)
      bodyFormData.set('communityId', this.userInfo.communityId)
      let url = '/pay-server/api/Pay/changeParking'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } }
      }
      ).then(response => {
        if (response.data) {
          this.loadUserDetail()
          this.$forceUpdate()
        } else {
          this.$alert('绑定停车方案失败！')
        }
      })
    },
    handleModify () {
      this.dialogFormVisible = false
      let bodyFormData = new FormData()
      bodyFormData.set('username', this.user.username)
      bodyFormData.set('type', this.user.type)
      bodyFormData.set('room', this.user.room)
      bodyFormData.set('communityId', this.userInfo.communityId)
      let url = '/pay-server/api/Pay/saveUsername'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } }
      }
      ).then(response => {
        if (response.data) {
          this.handleModify2()
        } else {
          this.$alert('绑定用户失败！')
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
