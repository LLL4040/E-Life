<template>
  <div>
<!--    <div align="center">-->
<!--      <el-input v-model="search" size="medium" style="width: 300px" suffix-icon="el-icon-search" placeholder="输入关键字搜索"/>-->
<!--    </div>-->
    <div align="left">
      <el-button style="margin-top: -20px" size="medium" type="primary" plain icon="el-icon-refresh" circle @click="refresh()"></el-button>
    </div>
    <el-row :gutter="10" style="padding-top: 20px" v-if="dialogFormVisibleMain">
      <el-col :span="18">
        <div>
          <div v-if="postData.length === 0">
            <el-card style="margin-bottom: 8px">
              <p>这里空空的，什么帖子都没有- -|||</p>
            </el-card>
          </div>
          <div v-if="postData.length !== 0">
            <div v-for="o in postData" :key="o" class="text item">
              <el-card style="margin-bottom: 8px">
                <el-button style="float: left; padding: 3px 0; font-size: 16px;" type="text" @click="toDetail(o)">{{o.title}}</el-button>
                <p style="float:right; font-size: 12px;">{{o.poster}}</p>
                <br>
                <p style="float: left; padding: 3px 0; font-size: 14px;">{{o.content.slice(0,20)}}</p>
                <p style="float: left; padding-bottom: 1px; font-size: 14px;">{{((o.content.length > 20) ? "...": "")}}</p>
                <br>
                <span v-for="(x, index) in o.photo" :key="index">
                  <button @click="show(o.path[index])" type="button" style="cursor: pointer; background-color: transparent; border: 0;">
                      <img :src="x" style="width: 100%; height: 100%">
                    </button>
<!--                <img :src="x" style="width: 30%"/>-->
                </span>
                <br>
                <p style="float:right; font-size: 12px;">{{o.time}}</p>
              </el-card>
            </div>
            <div class="block" align="center" >
              <el-pagination @current-change="handleCurrentChange1" :current-page.sync="pageNum1" :page-count="total1" :pager-count="5" layout="prev, pager, next, jumper"></el-pagination>
            </div>
          </div>
        </div>
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
    <el-row :gutter="10" style="padding-top: 20px" v-if="!dialogFormVisibleMain">
      <el-col :span="24">
        <el-col :span="20" style="float: left">
          <div v-for="o in commentData" :key="o" class="text item">
            <el-card style="margin-bottom: 0">
              <el-col :span="4" style="float: left" >
                <p style="float: bottom; font-size: 16px">{{o.commenterName}}</p>
              </el-col>
              <el-col :span="20" style="float: left">
                <p style="float:left; font-size: 16px;">{{o.postComment}}</p>
                <br>
                <span v-for="(x, index) in o.photo" :key="index">
                  <button @click="show(o.path[index])" type="button" style="cursor: pointer; background-color: transparent; border: 0;">
                      <img :src="x" style="width: 100%; height: 100%">
                    </button>
                  <!--                <img :src="x" style="width: 30%"/>-->
                </span>
                <br>
                <p style="float: right; font-size: 12px;">{{o.location + '楼，发布于' + o.commentsTime}}</p>
              </el-col>
            </el-card>
          </div>
          <div class="block" align="center" >
            <el-pagination @current-change="handleCurrentChange2" :current-page.sync="pageNum2" :page-count="total2" :pager-count="5" layout="prev, pager, next, jumper"></el-pagination>
          </div>
        </el-col>
        <el-col :span="4" >
          <el-card :span="4">
            <el-button icon="el-icon-back" type="success" size="mini" @click="goBack()" style="margin: 0 0 0 20px">返回</el-button>
            <el-button icon="el-icon-edit-outline" type="primary" size="mini" @click="dialogFormVisibleDetail = true" style="margin: 0 0 0 20px">评论</el-button>
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
        <el-form-item label="添加图片">
          <div class="upload">
            <div class="upload_warp_left" @click="fileClick()">
              <el-button type="primary" plain>添加图片</el-button>
            </div>
            <div class="upload_warp_text">
              已选中{{imgList.length}}张图片
            </div>
            <div class="upload_warp" style="border: 1px solid white;">
              <div class="upload_warp_img" v-show="imgList.length!=0" >
                <div class="upload_warp_img_div" v-for="(item,index) of imgList" :key="index">
                  <div class="upload_warp_img_div_top" >
                    <div class="upload_warp_img_div_text" >
                      {{item.file.name}}
                    </div>
                    <img src="../../public/img/del.png" class="upload_warp_img_div_del" @click="fileDel(index)">
                  </div>
                  <img :src="item.file.src">
                </div>
              </div>
            </div>
            <input type="file" id="files" ref="files" multiple v-on:change="handleFilesUpload($event)" style="display: none"/>
          </div>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addForum()">发 布</el-button>
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
    <el-dialog :visible.sync="dialogFormVisiblePhoto">
      <div>
        <img :src="photo" width="100%">
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'Forum',
  data () {
    return {
      formData: null,
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
      dialogFormVisiblePhoto: false,
      myPost: {
        username: '',
        title: '',
        content: ''
      },
      myComment: {
        content: ''
      },
      postData: [],
      commentData: [],
      imgList: [],
      pageNum1: 1,
      pageSize1: 10,
      total1: 1,
      pageNum2: 1,
      pageSize2: 10,
      total2: 1,
      currentP: {},
      photo: ''
    }
  },
  mounted () {
    this.refresh()
  },
  methods: {
    refresh () {
      this.loadData()
      this.loadPost()
      this.formData = new FormData()
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
    addForum () {
      this.dialogFormVisible = false
      this.formData.set('title', this.myPost.title)
      this.formData.set('postContent', this.myPost.content)
      this.formData.set('posterName', this.userInfo.username)
      this.formData.set('communityId', this.userInfo.communityId)
      let url = '/estateforum-server/api/post/addPost'
      this.$axios({
        method: 'post',
        url: url,
        data: this.formData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          if (response.data.post) {
            this.$alert('发布帖子成功！')
            this.loadPost()
            this.$forceUpdate()
          } else {
            this.$alert('发布帖子失败！')
          }
        }
      })
    },
    loadPost () {
      let bodyFormData = new FormData()
      bodyFormData.set('communityId', this.userInfo.communityId)
      bodyFormData.set('page', this.pageNum1)
      bodyFormData.set('size', this.pageSize1)
      this.$axios({
        method: 'post',
        url: '/estateforum-server/api/post/findPost',
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.length > 0 && response.data[0].login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          this.postData = []
          this.total1 = response.data[0].pageNum
          for (let i = 1; i < response.data.length; i++) {
            let objproject = {
              'photo': response.data[i].photo,
              'path': response.data[i].path,
              'title': response.data[i].title,
              'poster': response.data[i].posterName,
              'content': response.data[i].postContent,
              'time': response.data[i].postTime,
              'id': response.data[i].id
            }
            console.log(objproject.path)
            this.postData.push(objproject)
          }
        }
      })
    },
    handleCurrentChange1 (val) {
      this.pageNum1 = val
      this.loadPost()
    },
    toDetail (object) {
      this.currentP = object
      this.commentData = []
      if (this.pageNum2 === 1) {
        let data = {
          'commenterName': object.poster,
          'pid': object.id,
          'photo': object.photo,
          'path': object.path,
          'commentsTime': object.time,
          'postComment': object.content,
          'location': 1
        }
        this.commentData.push(data)
      }
      let bodyFormData = new FormData()
      bodyFormData.set('pid', object.id)
      bodyFormData.set('page', this.pageNum2)
      bodyFormData.set('size', this.pageSize2)
      this.$axios({
        method: 'post',
        url: '/estateforum-server/api/postComments/findComments',
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } }
      }).then(response => {
        if (response.data.length > 0 && response.data[0].login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          if (response.data[0].pageNum === 0) {
            this.total2 = 1
          } else {
            this.total2 = response.data[0].pageNum
          }
          for (let i = 1; i < response.data.length; i++) {
            this.commentData.push(response.data[i])
          }
          this.dialogFormVisibleMain = false
        }
      })
    },
    handleCurrentChange2 (val) {
      this.pageNum2 = val
      this.toDetail(this.currentP)
    },
    goBack () {
      this.pageNum2 = 1
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
          if (response.data.login === 0) {
            this.$router.push({ name: 'Login' })
          } else {
            if (response.data.addComments === '1') {
              this.$alert('评论成功')
              if (this.pageNum2 === 1) {
                let data = this.commentData[0]
                this.commentData = []
                this.commentData.push(data)
              } else {
                this.commentData = []
              }
              bodyFormData = new FormData()
              bodyFormData.set('pid', pid)
              bodyFormData.set('page', this.pageNum2)
              bodyFormData.set('size', this.pageSize2)
              this.$axios({
                method: 'post',
                url: '/estateforum-server/api/postComments/findComments',
                data: bodyFormData,
                config: { headers: { 'Content-type': 'multipart/form-data' } }
              }).then(response => {
                if (response.data[0].pageNum === 0) {
                  this.total2 = 1
                } else {
                  this.total2 = response.data[0].pageNum
                }
                for (let i = 1; i < response.data.length; i++) {
                  this.commentData.push(response.data[i])
                }
                this.$forceUpdate()
              })
            } else {
              this.$alert('评论失败！')
            }
          }
        })
      }
    },
    fileClick () { // 点击选择图片按钮
      document.getElementById('files').click()
    },
    handleFilesUpload (el) { // 一定要在这选择一次时，append一次
      this.filesArr = this.$refs.files.files
      for (let i = 0; i < this.filesArr.length; i++) {
        this.formData.append('photo', this.filesArr[i])
      }
      if (!el.target.files[0].size) return
      this.fileList(el.target.files)
    },
    fileList (files) {
      for (let i = 0; i < files.length; i++) {
        this.fileAdd(files[i])
      }
    },
    fileAdd (file) {
      this.size = this.size + file.size// 总大小
      let reader = new FileReader()
      reader.vue = this
      reader.readAsDataURL(file)
      reader.onload = function () {
        file.src = this.result
        this.vue.imgList.push({ file })
      }
    },
    fileDel (index) { // 删除已选择的图片
      this.size = this.size - this.imgList[index].file.size// 总大小
      this.imgList.splice(index, 1)
    },
    show (path) {
      console.log(path)
      let bodyFormData = new FormData()
      bodyFormData.set('path', path)
      let url = '/estateforum-server/api/post/photo'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          this.photo = response.data.photo
          this.dialogFormVisiblePhoto = true
        }
      })
    }
  }
}
</script>

<style>
  .upload_warp_img_div_del {
    position: absolute;
    top: 6px;
    width: 16px;
    right: 4px;
  }

  .upload_warp_img_div_top {
    position: absolute;
    top: 0;
    width: 100%;
    height: 30px;
    background-color: rgba(0, 0, 0, 0.4);
    line-height: 30px;
    text-align: left;
    color: #fff;
    font-size: 12px;
    text-indent: 4px;
  }

  .upload_warp_img_div_text {
    white-space: nowrap;
    width: 80%;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  .upload_warp_img_div img {
    max-width: 115px;
    max-height: 110px;
    vertical-align: middle;
  }

  .upload_warp_img_div {
    position: relative;
    height: 120px;
    width: 120px;
    border: 1px solid #ccc;
    margin: 0px 5px 5px 0px;
    float: left;
    line-height: 120px;
    display: table-cell;
    text-align: center;
    background-color: #eee;
    cursor: pointer;
  }

  .upload_warp_img {

    padding: 0 0 0 0;
    overflow: hidden
  }

  .upload_warp_text {
    text-align: left;
    margin-bottom: 5px;
    padding-top: 5px;
    text-indent: 14px;
    border-top: 1px solid #ccc;
    font-size: 14px;
  }

  .upload_warp_right {
    float: left;
    width: 57%;
    margin-left: 2%;
    height: 100%;
    border: 1px dashed #999;
    border-radius: 4px;
    line-height: 130px;
    color: #999;
  }

  .upload_warp_left button {
    margin: 5px 5px 0px 5px;
    cursor:pointer;
  }

  .upload_warp_left {
    float: left;
  }

  .upload_warp {
    margin: 5px;
  }

  .upload {
    border-left: 1px solid #ccc;
    border-right: 1px solid #ccc;
    background-color: #fff;

    box-shadow: 0px 1px 0px #ccc;
    border-radius: 4px;
  }
</style>
