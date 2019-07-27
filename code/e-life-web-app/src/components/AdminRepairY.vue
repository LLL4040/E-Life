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
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="left" inline class="demo-table-expand">
                <el-form-item label="维修人员姓名">
                  <span>{{ props.row.maintainname }}</span>
                </el-form-item>
                <el-form-item label="维修人员联系方式">
                  <span>{{ props.row.phone }}</span>
                </el-form-item>
              </el-form>
            </template>
          </el-table-column>
          <el-table-column prop="time" label="时间"></el-table-column>
          <el-table-column prop="username" label="用户名"></el-table-column>
          <el-table-column prop="userphone" label="联系方式"></el-table-column>
          <el-table-column prop="content" label="内容"></el-table-column>
          <el-table-column label="状态" prop="status" align="center">
            <template slot-scope="scope">
              <el-button v-if="scope.row.status === 2" type="success" plain size="small">已解决</el-button>
              <el-button v-if="scope.row.status === 1" type="primary" plain size="small" @click="handleDone(scope.row)">处理中</el-button>
            </template>
          </el-table-column>
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
  </div>
</template>

<script>
export default {
  name: 'AdminRepairY',
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
      requestData: [],
      pageNum: 1,
      pageSize: 10,
      total: 1
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
        config: { headers: { 'Content-type': 'multipart/form-data' } }
      }
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
      let url = '/lifeservice-server/api/maintain/managerFindHaveMaintain'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } }
      }
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
    handleDone (row) {
      let bodyFormData = new FormData()
      bodyFormData.set('id', row.id)
      bodyFormData.set('status', 2)
      let url = '/lifeservice-server/api/maintain/editMaintain'
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
    },
    handleDelete (row) {
      let bodyFormData = new FormData()
      bodyFormData.set('id', row.id)
      let url = '/lifeservice-server/api/maintain/deleteMaintain'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data) {
          this.loadRequest()
          this.$forceUpdate()
        } else {
          this.$alert('删除失败！请重新登录再试')
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
