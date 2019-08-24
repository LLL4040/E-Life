<template>
  <div>
    <div align="left">
      <el-button style="margin-top: -20px" size="medium" type="primary" plain icon="el-icon-refresh" circle @click="refresh()"></el-button>
    </div>
    <div align="center">
      <el-input v-model="search" size="medium" style="width: 350px" suffix-icon="el-icon-search" placeholder="输入用户名关键字筛选"/>
    </div>
    <div style="padding-top: 20px;">
      <el-card class="box-card">
        <el-table :data="requestData.filter(data => typeof data.time !== 'undefined' && (!search || data.username.toLowerCase().includes(search.toLowerCase())))" style="width: 100%">
          <el-table-column prop="time" label="时间"></el-table-column>
          <el-table-column prop="username" label="用户名"></el-table-column>
          <el-table-column prop="userphone" label="联系方式"></el-table-column>
          <el-table-column prop="content" label="内容"></el-table-column>
          <el-table-column label="操作" width="150px" align="center">
            <template slot-scope="scope">
              <el-button size="mini" type="primary" plain @click="handleIt1(scope.row)">处理</el-button>
            </template>
          </el-table-column>
        </el-table>
        <div class="block" align="center" >
          <el-pagination @current-change="handleCurrentChange" :current-page.sync="pageNum" :page-count="total" :pager-count="5" layout="prev, pager, next, jumper"></el-pagination>
        </div>
      </el-card>
    </div>
    <el-dialog title="处理报修" :visible.sync="dialogFormVisible">
      <el-form ref="handleR" :rules="rules" :model="handleR" class="demo-ruleForm" >
        <el-form-item label="修理人员姓名" prop="repairman">
          <el-input v-model="handleR.repairman"></el-input>
        </el-form-item>
        <el-form-item label="修理人员手机号" prop="phone">
          <el-input v-model="handleR.phone"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleIt()">提 交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'AdminRepairW',
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
      requestData: [],
      handleR: {
        repairman: '',
        phone: ''
      },
      pageNum: 1,
      pageSize: 10,
      total: 1,
      requestId: '',
      rules: {
        repairman:
          [{ required: true, message: '请输入姓名', trigger: 'blur' }],
        phone:
          [{ required: true, message: '请输入手机号码', trigger: 'blur' },
            { validator: function (rule, value, callback) {
              if (/^1[34578]\d{9}$/.test(value) === false) {
                callback(new Error('请输入正确的手机号'))
              } else {
                callback()
              }
            },
            trigger: 'blur' }]
      }
    }
  },
  mounted () {
    this.refresh()
  },
  methods: {
    refresh () {
      this.loadData()
      this.loadRequest()
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
    loadRequest () {
      let bodyFormData = new FormData()
      bodyFormData.set('communityId', this.userInfo.communityId)
      bodyFormData.set('pageNumber', this.pageNum)
      bodyFormData.set('pageSize', this.pageSize)
      let url = '/lifeservice-server/api/maintain/managerFindUnMaintain'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.length > 0 && response.data[0].login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          this.requestData = response.data
          console.log(this.requestData)
          this.total = response.data[0].pageNum
        }
      })
    },
    handleCurrentChange (val) {
      this.pageNum = val
      this.loadRequest()
      this.$forceUpdate()
    },
    handleIt1 (row) {
      this.requestId = row.id
      this.dialogFormVisible = true
    },
    handleIt () {
      this.dialogFormVisible = false
      let bodyFormData = new FormData()
      bodyFormData.set('id', this.requestId)
      bodyFormData.set('status', 1)
      bodyFormData.set('maintainname', this.handleR.repairman)
      bodyFormData.set('phone', this.handleR.phone)
      bodyFormData.set('managername', this.userInfo.username)
      let url = '/lifeservice-server/api/maintain/manageMaintain'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          if (response.data.manageMaintain === '1') {
            this.loadRequest()
            this.$forceUpdate()
          } else {
            this.$alert('处理失败！')
          }
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
