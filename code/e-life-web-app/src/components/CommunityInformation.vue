<template>
  <div>
    <div align="center" style="width: 800px; padding-left: 200px">
      <el-carousel indicator-position="outside" :interval="4000" height="300px">
        <el-carousel-item  v-if="notice.length === 0">
          <el-image style="width: 500px; height: 300px" :src="require('../../public/img/alert.jpg')"></el-image>
          <p style="position: relative; bottom: 150px; left: 10px">目前没有紧急通知哦</p>
        </el-carousel-item>
        <el-carousel-item  v-if="notice.length > 0">
          <el-image style="width: 500px; height: 300px" :src="require('../../public/img/alert.jpg')"></el-image>
          <p style="position: relative; bottom: 150px; left: 10px">{{ notice }}</p>
        </el-carousel-item>
        <el-carousel-item v-if="news.length > 0">
          <el-image style="width: 500px; height: 300px" :src="require('../../public/img/news.jpg')"></el-image>
          <p style="position: relative; bottom: 150px; left: 10px">{{ news[0].title }}</p>
        </el-carousel-item>
        <el-carousel-item v-if="activity.length > 0">
          <el-image style="width: 500px; height: 300px" :src="require('../../public/img/activity.jpg')"></el-image>
          <p style="position: relative; bottom: 150px; left: 10px">{{ activity[0].title }}</p>
        </el-carousel-item>
      </el-carousel>
    </div>
    <el-row :gutter="10">
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>最新资讯</span>
            <el-button style="float: right; padding: 3px 0" type="text" @click="loadActivityMore()">more >></el-button>
          </div>
          <el-table :data="news" style="width: 100%">
            <el-table-column type="expand">
              <template slot-scope="props">
                <el-form label-position="left" inline class="demo-table-expand">
                  <el-form-item label="封面">
                    <img :src="props.row.photo" style="width: 80px; height: 80px">
                  </el-form-item>
                  <el-form-item label="详情">
                    <span>{{ props.row.content }}</span>
                  </el-form-item>
                </el-form>
              </template>
            </el-table-column>
            <el-table-column prop="time" label="时间" align="center"></el-table-column>
            <el-table-column prop="title" label="标题" align="center"></el-table-column>
          </el-table>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>活动安排</span>
            <el-button style="float: right; padding: 3px 0" type="text" @click="loadNewsMore()">more >></el-button>
          </div>
          <el-table :data="activity" style="width: 100%">
            <el-table-column type="expand">
              <template slot-scope="props">
                <el-form label-position="left" inline class="demo-table-expand">
                  <el-form-item label="封面">
                    <img :src="props.row.photo" style="width: 80px; height: 80px">
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
                <el-button v-if="scope.row.status === 0" size="mini" type="primary" plain @click="handleA(scope.row)">申请加入</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>
    <el-dialog title="申请加入活动" :visible.sync="dialogFormVisible">
      <el-form :model="apply">
        <el-form-item label="留言">
          <el-input v-model="apply.content" autocomplete="off" type="textarea" :autosize="{ minRows: 2, maxRows: 3}"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleApply()">提 交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'CommunityInformation',
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
      notice: '',
      news: [],
      activity: [],
      apply: {
        id: '',
        content: ''
      }
    }
  },
  mounted () {
    this.loadData()
    this.loadUrgent()
    this.loadNews()
    this.loadActivity()
  },
  methods: {
    handleA (row) {
      this.apply.id = row.id
      this.dialogFormVisible = true
    },
    handleApply () {
      this.dialogFormVisible = false
      let bodyFormData = new FormData()
      bodyFormData.set('aid', this.apply.id)
      bodyFormData.set('content', this.apply.content)
      bodyFormData.set('username', this.userInfo.username)
      let url = '/news-server/api/Activity/saveParticipator'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data) {
          this.$alert('加入成功！')
        } else {
          this.$alert('加入失败！')
        }
      })
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
    loadUrgent () {
      let bodyFormData = new FormData()
      bodyFormData.set('communityId', this.userInfo.communityId)
      let url = '/news-server/api/Urgent/getNewUrgent'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        this.notice = response.data.content
        console.log(response.data)
      })
    },
    loadNews () {
      let bodyFormData = new FormData()
      bodyFormData.set('communityId', this.userInfo.communityId)
      let url = '/news-server/api/News/findNews'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        this.news = response.data
        console.log(response.data)
      })
    },
    loadNewsMore () {
      let bodyFormData = new FormData()
      bodyFormData.set('communityId', this.userInfo.communityId)
      let url = '/news-server/api/News/findHistory'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        this.news = response.data
        console.log(response.data)
      })
    },
    loadActivity () {
      let bodyFormData = new FormData()
      bodyFormData.set('communityId', this.userInfo.communityId)
      let url = '/news-server/api/Activity/findNewActivity'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        this.activity = response.data
        console.log(response.data)
      })
    },
    loadActivityMore () {
      let bodyFormData = new FormData()
      bodyFormData.set('communityId', this.userInfo.communityId)
      let url = '/news-server/api/Activity/findAllActivity'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        this.activity = response.data
        console.log(response.data)
      })
    }
  }
}
</script>

<style scoped>
</style>
