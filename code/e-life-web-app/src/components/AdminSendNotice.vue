<template>
  <div>
    <div align="center">
      <el-input v-model="search" size="medium" style="width: 300px" suffix-icon="el-icon-search" placeholder="输入内容关键字搜索"/>
    </div>
    <el-row :gutter="10" style="padding-top: 20px">
      <el-col :span="12">
      <el-card style="padding-left: 5px">
        <div slot="header" class="clearfix">
          <span style="font-size: 16px;">紧急通知</span>
          <el-button style="float: right;" size="medium" type="primary" icon="el-icon-plus" circle @click="dialogFormVisible1 = true"></el-button>
        </div>
        <el-table :data="urgent.filter(data => !search || data.content.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="left" inline class="demo-table-expand">
                <el-form-item label="详情">
                  <span>{{ props.row.content }}</span>
                </el-form-item>
              </el-form>
            </template>
          </el-table-column>
          <el-table-column prop="time" label="时间" align="center"></el-table-column>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button size="medium" type="danger" icon="el-icon-delete" circle @click="handleDeleteU(scope.row)"></el-button>
            </template>
          </el-table-column>
        </el-table>
        <div class="block" align="center" >
          <el-pagination @current-change="handleCurrentChange1" :current-page.sync="pageNum1" :page-count="pageSize1" :pager-count="5" layout="prev, pager, next, jumper"></el-pagination>
        </div>
      </el-card>
      </el-col>
      <el-col :span="12">
        <el-card style="padding-left: 5px">
          <div slot="header" class="clearfix">
            <span style="font-size: 16px;">最新资讯</span>
            <el-button style="float: right;" size="medium" type="primary" icon="el-icon-plus" circle @click="dialogFormVisible2 = true"></el-button>
          </div>
          <el-table :data="news.filter(data => !search || data.content.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
            <el-table-column type="expand">
              <template slot-scope="props">
                <el-form label-position="left" inline class="demo-table-expand">
                  <el-form-item label="封面">
                    <button @click="show(props.row)" type="button" style="cursor: pointer; background-color: transparent; border: 0;">
                      <img :src="props.row.photo" style="width: 100%; height: 100%">
                    </button>
                  </el-form-item>
                  <el-form-item label="时间">
                    <span>{{ props.row.time }}</span>
                  </el-form-item>
                  <el-form-item label="详情">
                    <span>{{ props.row.content }}</span>
                  </el-form-item>
                </el-form>
              </template>
            </el-table-column>
            <el-table-column prop="title" label="标题" align="center"></el-table-column>
            <el-table-column label="操作" align="center">
              <template slot-scope="scope">
                <el-button size="medium" type="danger" icon="el-icon-delete" circle @click="handleDeleteN(scope.row)"></el-button>
              </template>
            </el-table-column>
          </el-table>
          <div class="block" align="center" >
            <el-pagination @current-change="handleCurrentChange2" :current-page.sync="pageNum2" :page-count="pageSize2" :pager-count="5" layout="prev, pager, next, jumper"></el-pagination>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-dialog title="发布紧急通知" :visible.sync="dialogFormVisible1">
      <el-form :model="newUrgent">
        <el-form-item label="通知内容">
          <el-input v-model="newUrgent.content" autocomplete="off" type="textarea" :autosize="{ minRows: 3, maxRows: 5}"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="releaseU()">发 布</el-button>
      </div>
    </el-dialog>
    <el-dialog title="发布最新资讯" :visible.sync="dialogFormVisible2">
      <el-form :model="newNews">
        <el-form-item label="资讯标题">
          <el-input v-model="newNews.title"></el-input>
        </el-form-item>
        <el-form-item label="封面">
          <button class="upload-button" id="upImg" type="button" @click="upF()"></button>
          <input type='file' accept="image/*" id="file" ref="file" style="display: none"/>
        </el-form-item>
        <el-form-item label="资讯内容">
          <el-input v-model="newNews.content" autocomplete="off" type="textarea" :autosize="{ minRows: 3, maxRows: 5}"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible2 = false">取 消</el-button>
        <el-button type="primary" @click="releaseN()">发 布</el-button>
      </div>
    </el-dialog>
    <el-dialog :visible.sync="dialogFormVisible3">
      <div>
        <img :src="photo" width="100%">
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'AdminSendNotice',
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
      dialogFormVisible1: false,
      dialogFormVisible2: false,
      dialogFormVisible3: false,
      urgent: [],
      news: [],
      newUrgent: {
        content: ''
      },
      newNews: {
        title: '',
        content: ''
      },
      pageNum1: 1,
      pageNum2: 1,
      pageSize1: 1,
      pageSize2: 1
    }
  },
  mounted () {
    this.loadData()
    this.loadUrgent()
    this.loadNews()
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
    loadUrgent () {
      let bodyFormData = new FormData()
      bodyFormData.set('communityId', this.userInfo.communityId)
      bodyFormData.set('page', this.pageNum1)
      let url = '/news-server/api/Urgent/findHistory'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        this.urgent = response.data
        this.pageSize1 = response.data[response.data.length - 1].pageNum
        console.log(response.data)
        this.urgent.pop()
      })
    },
    handleCurrentChange1 (val) {
      this.pageNum1 = val
      this.loadUrgent()
    },
    loadNews () {
      let bodyFormData = new FormData()
      bodyFormData.set('communityId', this.userInfo.communityId)
      bodyFormData.set('page', this.pageNum2)
      let url = '/news-server/api/News/findHistory'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        this.news = response.data
        this.pageSize2 = response.data[response.data.length - 1].pageNum
        console.log(response.data)
        this.news.pop()
      })
    },
    handleCurrentChange2 (val) {
      this.pageNum2 = val
      this.loadNews()
    },
    releaseU () {
      this.dialogFormVisible1 = false
      let bodyFormData = new FormData()
      bodyFormData.set('communityId', this.userInfo.communityId)
      bodyFormData.set('managerName', this.userInfo.username)
      bodyFormData.set('content', this.newUrgent.content)
      let url = '/news-server/api/Urgent/saveUrgent'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data) {
          this.loadUrgent()
        } else {
          this.$alert('发布紧急通知失败！')
        }
      })
    },
    releaseN () {
      this.dialogFormVisible2 = false
      let imagFile = this.$refs.file.files[0]
      let bodyFormData = new FormData()
      bodyFormData.set('communityId', this.userInfo.communityId)
      bodyFormData.set('managerName', this.userInfo.username)
      bodyFormData.set('content', this.newNews.content)
      bodyFormData.set('title', this.newNews.title)
      bodyFormData.append('photo', imagFile)
      let url = '/news-server/api/News/saveNews'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data) {
          this.loadNews()
        } else {
          this.$alert('发布资讯失败！')
        }
      })
    },
    handleDeleteU (row) {
      let bodyFormData = new FormData()
      bodyFormData.set('id', row.id)
      let url = '/news-server/api/Urgent/deleteUrgent'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data) {
          this.loadNews()
        } else {
          this.$alert('删除紧急通知失败！')
        }
      })
    },
    handleDeleteN (row) {
      let bodyFormData = new FormData()
      bodyFormData.set('id', row.id)
      let url = '/news-server/api/News/deleteOne'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data) {
          this.loadNews()
        } else {
          this.$alert('删除资讯失败！')
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
    show (row) {
      let bodyFormData = new FormData()
      bodyFormData.set('path', row.path)
      let url = '/news-server/api/News/photo'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        this.photo = response.data.photo
        this.dialogFormVisible3 = true
      })
    }
  }
}
</script>

<style>
  .upload-button {
    width: 120px;
    height: 120px;
    line-height: 120px;
    border: 1px solid #99a9bf;
    display: inline-block;
    background-color: #f6f6f6;
    background-image:url('../../public/img/upload.jpg');
    background-repeat:no-repeat;
    background-size:50% 50%;
    background-position: 30px 30px;
  }
  .upload-button2 {
    width: 120px;
    height: 120px;
    line-height: 120px;
    border: 1px solid #99a9bf;
    display: inline-block;
    background-color: #f6f6f6;
    background-image:url('../../public/img/true.jpg');
    background-repeat:no-repeat;
    background-size:50% 50%;
    background-position: 30px 30px;
  }
</style>
