<template>
  <div>
    <div align="left">
      <el-button style="margin-top: -20px" size="medium" type="primary" plain icon="el-icon-refresh" circle @click="refresh()"></el-button>
    </div>
    <div style="padding-top: 20px">
      <el-card style="padding-left: 20px; padding-right: 20px;">
        <div slot="header" class="clearfix">
          <span style="font-size: 16px;">已录入户型信息</span>
          <el-button style="float: right;" size="medium" type="primary" icon="el-icon-plus" circle @click="dialogFormVisible = true"></el-button>
        </div>
        <el-table :data="roomspace" style="width: 100%">
          <el-table-column prop="type" label="户型" align="center"></el-table-column>
          <el-table-column prop="space" label="户型面积" align="center"></el-table-column>
          <el-table-column prop="maxNum" label="户型楼栋数" align="center"></el-table-column>
          <el-table-column prop="maxFloor" label="每栋楼层数" align="center"></el-table-column>
          <el-table-column prop="maxRoom" label="每层房间数" align="center"></el-table-column>
<!--          <el-table-column label="操作" align="center">-->
<!--            <template slot-scope="scope">-->
<!--              <el-button size="medium" type="danger" icon="el-icon-delete" circle @click="handleDelete(scope.row)"></el-button>-->
<!--            </template>-->
<!--          </el-table-column>-->
        </el-table>
      </el-card>
    </div>
    <el-dialog title="新增户型" :visible.sync="dialogFormVisible">
      <el-form :model="newRoom" align="center" style="padding-left: 40px;">
        <el-form-item label="户型(只能为单个英文字母)">
          <el-input v-model="newRoom.type" style="width: 180px; margin-left: -88px;"></el-input>
        </el-form-item>
        <el-form-item label="该户型面积">
          <el-input-number v-model="newRoom.roomSpace" :precision="2" :step="0.1"></el-input-number>
        </el-form-item>
        <el-form-item label="户型楼栋数">
          <el-input-number v-model="newRoom.maxNum" :precision="0" :step="1"></el-input-number>
        </el-form-item>
        <el-form-item label="每栋楼层数">
          <el-input-number v-model="newRoom.maxFloor" :precision="0" :step="1"></el-input-number>
        </el-form-item>
        <el-form-item label="每层房间数">
          <el-input-number v-model="newRoom.maxRoom" :precision="0" :step="1"></el-input-number>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addRoom()">发 送</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'AdminRoomspace',
  data () {
    return {
      userInfo: {
        community: '',
        communityId: 0,
        username: '',
        email: '',
        phone: ''
      },
      dialogFormVisible: false,
      roomspace: [],
      newRoom: {
        type: '',
        maxNum: 0,
        maxFloor: 0,
        maxRoom: 0,
        roomSpace: 0
      }
    }
  },
  mounted () {
    this.refresh()
  },
  methods: {
    refresh () {
      this.loadData()
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
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        this.userInfo.community = response.data.community
        sessionStorage.setItem('community', this.userInfo.community)
      })
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
    addRoom () {
      this.dialogFormVisible = false
      let bodyFormData = new FormData()
      bodyFormData.set('type', this.newRoom.type)
      bodyFormData.set('maxNum', this.newRoom.maxNum)
      bodyFormData.set('maxFloor', this.newRoom.maxFloor)
      bodyFormData.set('maxRoom', this.newRoom.maxRoom)
      bodyFormData.set('roomSpace', this.newRoom.roomSpace)
      bodyFormData.set('communityId', this.userInfo.communityId)
      let url = '/pay-server/api/Pay/saveRoom'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data) {
          this.$message({
            type: 'success',
            message: '添加成功!'
          })
          this.loadRoomspace()
          this.$forceUpdate()
        } else {
          this.$alert('添加户型失败！')
        }
      })
    }
    // handleDelete (row) {
    //   let bodyFormData = new FormData()
    //   bodyFormData.set('noticeId', row.id)
    //   let url = '/news-server/api/notice/deleteOneNotice'
    //   this.$axios({
    //     method: 'post',
    //     url: url,
    //     data: bodyFormData,
    //     config: { headers: { 'Content-type': 'multipart/form-data' } } }
    //   ).then(response => {
    //     if (response.data.login === 0) {
    //       this.$router.push({ name: 'Login' })
    //     } else {
    //       if (response.data.deleteOneNotice === '1') {
    //         this.loadMessage()
    //         this.$forceUpdate()
    //       } else {
    //         this.$alert('删除通知失败！')
    //       }
    //     }
    //   })
    // }
  }
}
</script>

<style scoped>

</style>
