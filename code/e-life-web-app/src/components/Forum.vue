<template>
  <div>
    <div align="center">
      <el-input v-model="search" size="medium" style="width: 300px" suffix-icon="el-icon-search" placeholder="输入关键字搜索"/>
    </div>
    <el-row :gutter="10" style="padding-top: 20px">
      <el-col :span="18">
        <el-card class="box-card">
          <div v-for="o in postData.length-1" :key="o" class="text item">
            {{'帖子题目: ' + postData[o].title + '  '}}
            {{
            '帖子时间 '+postData[o].postTime
            }}
            <br/>
            {{
               '发帖人 '+postData[o].posterName}}
            <br/>
            {{
               '帖子内容 '+postData[o].postContent}}
            <br/>
            <el-divider></el-divider>
          </div>
        </el-card>
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
      dialogFormVisible: false,
      myPost: {
        username: '',
        title: '',
        content: ''
      },
      postData: []
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
    }
  }
}
</script>

<style scoped>

</style>
