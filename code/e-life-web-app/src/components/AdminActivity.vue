<template>
  <div>
    <div align="center">
      <el-input v-model="search" size="medium" style="width: 300px" suffix-icon="el-icon-search" placeholder="输入活动名称关键字进行搜索"/>
    </div>
    <el-row :gutter="10" style="padding-top: 20px">
      <el-col :span="14">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span style="font-size: 16px;">活动列表</span>
            <el-button style="float: right;" size="medium" type="primary" icon="el-icon-plus" circle @click="dialogFormVisible = true"></el-button>
          </div>
          <el-table :data="activity.filter(data => !search || data.title.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
            <el-table-column type="expand">
              <template slot-scope="props">
                <el-form label-position="left" inline class="demo-table-expand">
                  <el-form-item label="封面">
                    <button @click="show(props.row)" type="button" style="cursor: pointer; background-color: transparent; border: 0;">
                      <img :src="props.row.photo" style="width: 100%; height: 100%">
                    </button>
                  </el-form-item>
                  <el-form-item label="开始时间">
                    <span>{{ props.row.startTime }}</span>
                  </el-form-item>
                  <el-form-item label="结束时间">
                    <span>{{ props.row.endTime }}</span>
                  </el-form-item>
                  <el-form-item label="详情">
                    <span>{{ props.row.content }}</span>
                  </el-form-item>
                </el-form>
              </template>
            </el-table-column>
            <el-table-column label="标题" prop="title" align="center"></el-table-column>
            <el-table-column label="状态" align="center">
              <template slot-scope="scope">
                <el-button v-if="scope.row.status === 1" type="danger" plain size="small">已结束</el-button>
                <el-button v-if="scope.row.status === 0" type="success" plain size="small">报名中</el-button>
              </template>
            </el-table-column>
            <el-table-column label="操作" align="center">
              <template slot-scope="scope">
                <el-button size="medium" type="primary" icon="el-icon-view" circle @click="loadApply(scope.row)"></el-button>
                <el-button size="medium" type="danger" icon="el-icon-delete" circle @click="handleDelete(scope.row)"></el-button>
              </template>
            </el-table-column>
          </el-table>
          <div class="block" align="center" >
            <el-pagination @current-change="handleCurrentChange1" :current-page.sync="pageNum1" :page-count="pageSize1" :pager-count="5" layout="prev, pager, next, jumper"></el-pagination>
          </div>
        </el-card>
      </el-col>
      <el-col :span="10">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span style="font-size: 16px;">申请列表</span>
          </div>
          <el-table :data="apply" style="width: 100%">
            <el-table-column type="expand">
              <template slot-scope="props">
                <el-form label-position="left" inline class="demo-table-expand">
                  <el-form-item label="留言">
                    <span>{{ props.row.content }}</span>
                  </el-form-item>
                </el-form>
              </template>
            </el-table-column>
            <el-table-column label="用户名" prop="username" align="center"></el-table-column>
            <el-table-column label="操作" width="150px" align="center">
              <template slot-scope="scope">
                <el-button v-if="scope.row.status === 0" size="mini" type="primary" plain @click="handleA(scope.row)">同意</el-button>
                <el-button v-if="scope.row.status === 0" size="mini" type="danger" plain @click="handleR(scope.row)">拒绝</el-button>
                <el-button v-if="scope.row.status === 1" size="mini" type="primary" plain>已同意</el-button>
                <el-button v-if="scope.row.status === -1" size="mini" type="danger" plain>已拒绝</el-button>
              </template>
            </el-table-column>
          </el-table>
          <div class="block" align="center" >
            <el-pagination @current-change="handleCurrentChange2" :current-page.sync="pageNum2" :page-count="pageSize2" :pager-count="5" layout="prev, pager, next, jumper"></el-pagination>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-dialog title="发起活动" :visible.sync="dialogFormVisible">
      <el-form :model="newA">
        <el-form-item label="活动标题">
          <el-input v-model="newA.title"></el-input>
        </el-form-item>
        <el-form-item label="起始时间">
          <el-date-picker v-model="time" type="datetimerange" :picker-options="pickerOptions"
                          range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" align="right" value-format="yyyy-MM-dd HH:mm:ss">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="封面">
        <button class="upload-button" id="upImg" type="button" @click="upF()"></button>
        <input type='file' accept="image/*" id="file" ref="file" style="display: none"/>
      </el-form-item>
        <el-form-item label="详情">
          <el-input v-model="newA.content" autocomplete="off" type="textarea" :autosize="{ minRows: 3, maxRows: 5}"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="releaseA()">发 布</el-button>
      </div>
    </el-dialog>
    <el-dialog :visible.sync="dialogFormVisible2">
      <div>
        <img :src="photo" width="100%">
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'AdminActivity',
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
      dialogFormVisible2: false,
      photo: '',
      activity: [],
      apply: [],
      applyId: '',
      time: [],
      newA: {
        title: '',
        content: ''
      },
      pageNum1: 1,
      pageNum2: 1,
      pageSize1: 1,
      pageSize2: 1,
      pickerOptions: {
        shortcuts: [{
          text: '未来一周',
          onClick (picker) {
            const start = new Date()
            const end = new Date()
            end.setTime(end.getTime() + 3600 * 1000 * 24 * 7)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '未来一个月',
          onClick (picker) {
            const start = new Date()
            const end = new Date()
            end.setTime(end.getTime() + 3600 * 1000 * 24 * 30)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '未来三个月',
          onClick (picker) {
            const start = new Date()
            const end = new Date()
            end.setTime(end.getTime() + 3600 * 1000 * 24 * 90)
            picker.$emit('pick', [start, end])
          }
        }]
      }
    }
  },
  mounted () {
    this.loadData()
    this.loadActivity()
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
    loadActivity () {
      let bodyFormData = new FormData()
      bodyFormData.set('communityId', this.userInfo.communityId)
      bodyFormData.set('page', this.pageNum1)
      let url = '/news-server/api/Activity/findAllActivity'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.length > 0 && response.data[0].login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          this.activity = response.data
          this.pageSize1 = response.data[response.data.length - 1].pageNum
          console.log(response.data)
          this.activity.pop()
        }
      })
    },
    handleCurrentChange1 (val) {
      this.pageNum1 = val
      this.loadActivity()
    },
    loadApply (row) {
      this.applyId = row
      let bodyFormData = new FormData()
      bodyFormData.set('aid', row.id)
      bodyFormData.set('page', this.pageNum2)
      let url = '/news-server/api/Activity/findAllParticipator'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.length > 0 && response.data[0].login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          this.apply = response.data
          this.pageSize2 = response.data[response.data.length - 1].pageNum
          console.log(response.data)
          this.apply.pop()
        }
      })
    },
    handleCurrentChange2 (val) {
      this.pageNum2 = val
      this.loadApply()
    },
    handleA (row) {
      let bodyFormData = new FormData()
      bodyFormData.set('pid', row.id)
      bodyFormData.set('status', 1)
      let url = '/news-server/api/Activity/disagreeParticipator'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data) {
          this.loadApply(this.applyId)
        } else {
          this.$alert('操作失败！请重新登录再试')
        }
      })
    },
    handleR (row) {
      let bodyFormData = new FormData()
      bodyFormData.set('pid', row.id)
      bodyFormData.set('status', -1)
      let url = '/news-server/api/Activity/disagreeParticipator'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data) {
          this.loadApply(this.applyId)
        } else {
          this.$alert('操作失败！请重新登录再试')
        }
      })
    },
    upF () {
      let element = document.getElementById('file')
      element.addEventListener('change', function (e) {
        document.getElementById('upImg').className = 'upload-button2'
      })
      element.click()
    },
    releaseA () {
      this.dialogFormVisible = false
      let imagFile = this.$refs.file.files[0]
      let bodyFormData = new FormData()
      bodyFormData.set('communityId', this.userInfo.communityId)
      bodyFormData.set('managerName', this.userInfo.username)
      bodyFormData.set('content', this.newA.content)
      bodyFormData.set('title', this.newA.title)
      bodyFormData.set('startTime', this.time[0])
      bodyFormData.set('endTime', this.time[1])
      bodyFormData.append('photo', imagFile)
      let url = '/news-server/api/Activity/saveActivity'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data) {
          this.loadActivity()
        } else {
          this.$alert('发布活动失败！请重新登录再试')
        }
      })
    },
    handleDelete (row) {
      let bodyFormData = new FormData()
      bodyFormData.set('aid', row.id)
      let url = '/news-server/api/Activity/deleteActivity'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data) {
          this.loadActivity()
        } else {
          this.$alert('删除活动失败！请重新登录再试')
        }
      })
    },
    show (row) {
      let bodyFormData = new FormData()
      bodyFormData.set('path', row.path)
      let url = '/news-server/api/Activity/photo'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.length > 0 && response.data[0].login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          this.photo = response.data.photo
          this.dialogFormVisible2 = true
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
