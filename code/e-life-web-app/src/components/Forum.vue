<template>
  <div>
<!--    <div align="center">-->
<!--      <el-input v-model="search" size="medium" style="width: 300px" suffix-icon="el-icon-search" placeholder="输入关键字搜索"/>-->
<!--    </div>-->
    <el-row :gutter="10" style="padding-top: 20px" v-if="dialogFormVisibleMain">
      <el-col :span="18">
<!--        <el-card class="box-card" >-->
          <div v-for="o in postData" :key="o" class="text item">
<!--            {{ '帖子时间 '+o.postTime}}-->
<!--            <br/>-->
<!--            {{ '帖子题目: ' + o.title + '  ' }}-->
<!--            <br/>-->
<!--            {{ '发帖人 '+o.posterName }}-->
<!--            <br/>-->
<!--            {{ '帖子内容 '+o.postContent }}-->
<!--            <br/>-->
<!--            <el-divider></el-divider>-->
<!--            <el-link type="primary" href="/user">主要链接</el-link>-->
            <el-card style="margin-bottom: 8px">
              <el-button style="float: left; padding: 3px 0; font-size: 16px;" type="text" @click="toDetail(o)">{{o.title}}</el-button>
              <p style="float:right; font-size: 12px;">{{o.posterName}}</p>
              <br>
              <p style="float: left; padding: 3px 0; font-size: 15px;">{{o.postContent.slice(0,20)}}</p>
              <p style="float: left; padding-bottom: 1px; font-size: 17px;">{{((o.postContent.length > 20) ? "...": "")}}</p>
              <br>
              <p style="float:right; font-size: 12px;">{{o.postTime}}</p>
            </el-card>
          </div>
<!--        </el-card>-->
      </el-col>
      <el-col :span="6">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>话题标签</span>
            <el-button style="float: right; padding: 3px 0; font-size: 16px;" type="text" @click="dialogFormVisible = true">我要发帖</el-button>
          </div>
          <el-row :gutter="10">
            <el-col :span="7">
              <el-tag>标签一</el-tag>
            </el-col>
            <el-col :span="7">
            <el-tag type="success">标签二</el-tag>
            </el-col>
              <el-col :span="7">
            <el-tag type="info">标签三</el-tag>
              </el-col>
                <el-col :span="7">
            <el-tag type="warning">标签四</el-tag>
                </el-col>
                  <el-col :span="7">
            <el-tag type="danger">标签五</el-tag>
                  </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="24" style="padding-top: 20px" v-if="!dialogFormVisibleMain">
      <el-col :span="24">
        <el-col :span="20" style="float: left">
          <div v-for="o in commentData" :key="o" class="text item">
            <el-card style="margin-bottom: 0">
              <el-col :span="4" style="float: left" >
                <p style="float: bottom; font-size: 15px">{{o.commenterName}}</p>
              </el-col>
              <el-col :span="20" style="float: left">
                <p style="float:left; font-size: 15px;">{{o.postComment}}</p>
                <br>
                <p style="float: right; font-size: 12px;">{{o.location + '楼，发布于' + o.commentsTime}}</p>
              </el-col>
            </el-card>
          </div>
        </el-col>
        <el-col :span="4" style="float: right">
          <el-card :span="4">
            <el-button style="alignment: center" @click="goBack()">返回</el-button>
            <el-button style="alignment: center" @click="dialogFormVisibleDetail = true" type="primary">评论</el-button>
          </el-card>
        </el-col>
      </el-col>
    </el-row>
    <el-dialog title="发帖详情" :visible.sync="dialogFormVisible">
      <el-form :model="myPost">
        <el-form-item label="标题">
          <el-input v-model="myPost.title" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="内容">
          <el-input v-model="myPost.content" autocomplete="off" type="textarea" :autosize="{ minRows: 3, maxRows: 5}"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addforum">发 布</el-button>
      </div>
    </el-dialog>
    <el-dialog title="发帖详情" :visible.sync="dialogFormVisibleDetail">
      <el-form :model="myComment">
        <el-form-item label="评论内容">
          <el-input v-model="myComment.content" autocomplete="off" type="textarea" :autosize="{ minRows: 3, maxRows: 5}"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisibleDetail = false">取 消</el-button>
        <el-button type="primary" @click="addComment()">发 布</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'Forum',
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
      dialogFormVisibleMain: true,
      dialogFormVisible: false,
      dialogFormVisibleDetail: false,
      myPost: {
        username: '',
        title: '',
        content: ''
      },
      myComment: {
        content: ''
      },
      postData: [],
      commentPage: 1,
      commentSize: 5,
      commentData: []
    }
  },
  mounted () {
    this.loadData()
    this.findPost()
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
    addforum () {
      this.dialogFormVisible = false
      let bodyFormData = new FormData()
      bodyFormData.set('title', this.myPost.title)
      bodyFormData.set('postContent', this.myPost.content)
      bodyFormData.set('posterName', this.userInfo.username)
      bodyFormData.set('communityId', this.userInfo.communityId)
      let url = '/estateforum-server/api/post/addPost'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data) {
          this.$alert('加入成功！')
          this.findPost()
        } else {
          this.$alert('加入失败！')
        }
      })
    },
    findPost () {
      let bodyFormData = new FormData()
      bodyFormData.set('communityId', this.userInfo.communityId)
      bodyFormData.set('page', 1)
      bodyFormData.set('size', 1000)
      this.$axios({
        method: 'post',
        url: '/estateforum-server/api/post/findPost',
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        // for (var i = 0; i < response.data.length; i++) {
        //   var objproject = {
        //     'title': response.data[i].title, // 这个是赋值到一个数组对象里面去，开发的时候就是取到里面的值进行一个逻辑判断，要干嘛干嘛的。这个也加上他的下标
        //     'poster': response.data[i].posterName,
        //     'content': response.data[i].postContent,
        //     'time': response.data[i].postTime,
        //     'id': response.data[i].id
        //   }
        //   this.postData.push(objproject)
        // }
        console.log(response.data)
        this.postData = response.data
      })
    },
    toDetail (object) {
      this.commentData = []
      let data = {
        'commenterName': object.posterName,
        'pid': object.id,
        'commentsTime': object.postTime,
        'postComment': object.postContent,
        'location': 1
      }
      this.commentData.push(data)
      this.commentPage = 1
      let bodyFormData = new FormData()
      bodyFormData.set('pid', object.id)
      bodyFormData.set('page', 1)
      bodyFormData.set('size', this.commentSize)
      console.log(bodyFormData)
      this.$axios({
        method: 'post',
        url: '/estateforum-server/api/postComments/findComments',
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } }
      }).then(response => {
        console.log(response.data)
        for (let i = 0; i < response.data.length; i++) {
          this.commentData.push(response.data[i])
        }
        this.dialogFormVisibleMain = false
      })
    },
    goBack () {
      this.dialogFormVisibleMain = true
    },
    addComment () {
      if (this.myComment.content !== '') {
        this.dialogFormVisibleDetail = false
        let bodyFormData = new FormData()
        let pid = this.commentData[0].pid
        bodyFormData.set('pid', pid)
        bodyFormData.set('commenterName', this.userInfo.username)
        bodyFormData.set('postComment', this.myComment.content)
        let url = '/estateforum-server/api/postComments/addComments'
        this.$axios({
          method: 'post',
          url: url,
          data: bodyFormData,
          config: { headers: { 'Content-type': 'multipart/form-data' } } }
        ).then(response => {
          if (response.data.addComments === '1') {
            this.$alert('评论成功')
            if (this.commentPage === 1) {
              let data = this.commentData[0]
              this.commentData = []
              this.commentData.push(data)
            } else {
              this.commentData = []
            }
            bodyFormData = new FormData()
            bodyFormData.set('pid', pid)
            bodyFormData.set('page', this.commentPage)
            bodyFormData.set('size', this.commentSize)
            this.$alert(bodyFormData)
            this.$axios({
              method: 'post',
              url: '/estateforum-server/api/postComments/findComments',
              data: bodyFormData,
              config: { headers: { 'Content-type': 'multipart/form-data' } }
            }).then(response => {
              console.log(response.data)
              for (let i = 0; i < response.data.length; i++) {
                this.commentData.push(response.data[i])
              }
            })
          } else {
            this.$alert('评论失败！')
          }
        })
      } else {
        this.$alert('评论失败！')
      }
    }
  }
}
</script>

<style scoped>

</style>
