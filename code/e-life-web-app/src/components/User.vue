<template>
  <el-container style="border: 0 solid #eee">
    <el-header style="padding-left: 0;">
      <section class="text-white tm-font-big tm-parallax">
        <nav class="navbar navbar-expand-md tm-navbar scroll" id="tmNav">
          <div class="container">
            <div class="tm-next">
              <img src="../../public/img/logo.gif" width="60px">
              <router-link router-link :to="{name:'home'}" class="navbar-brand">E-LIFE</router-link>
            </div>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              <i class="fas fa-bars navbar-toggler-icon"></i>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                  <a class="nav-link tm-nav-link" @click="toPage1(1)" style="font-size: 20px;">小区资讯</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link tm-nav-link" @click="toPage1(2)" style="font-size: 20px;">小区团购</a>
                </li>
                <li class="nav-item">
                  <el-dropdown @command="toPage1">
                    <span class="nav-link tm-nav-link" style="font-size: 20px;">
                      <i class="el-icon-arrow-down el-icon--right" ></i>小区服务
                    </span>
                    <el-dropdown-menu slot="dropdown">
                      <el-dropdown-item command="4" style="font-size: 14px;">超市送货</el-dropdown-item>
                      <el-dropdown-item command="5" style="font-size: 14px;">电脑维修</el-dropdown-item>
                      <el-dropdown-item command="6" style="font-size: 14px;">物业维修</el-dropdown-item>
                    </el-dropdown-menu>
                  </el-dropdown>
                </li>
                <li class="nav-item">
                  <a class="nav-link tm-nav-link" @click="toPage1(3)" style="font-size: 20px;">小区论坛</a>
                </li>
              </ul>
            </div>
          </div>
        </nav>
      </section>
    </el-header>
    <el-container style="padding-top: 20px;">
      <el-aside width="220px" style="background-color: rgb(238, 241, 246)">
        <el-menu :default-openeds="openList" style="height:100vh;width:220px;float:left;overflow-y:auto">
          <div align="center">
            <i class="fas fa-3x fa-user-circle text-center tm-icon"></i>
            <div style="clear:both"></div>
            <el-button class="text-center tm-text-primary mb-4" type="success" plain size="mini" icon="el-icon-info">居民</el-button>
            <el-button class="text-center tm-text-primary mb-4" type="primary" plain size="mini" icon="el-icon-edit" @click="dialogFormVisible = true">{{ userInfo.username }}</el-button>
            <div style="clear:both"></div>
          </div>
          <el-menu-item index="1" @click="toPage2(1, 7)">
            <i class="el-icon-chat-dot-round"></i>
            <span slot="title" style="font-size: 16px">我的通知
              <el-badge v-if="newMessage !== 0" class="mark" :value=newMessage :max="99" style="background-color: transparent" />
            </span>
          </el-menu-item>
          <el-submenu index="2">
            <template slot="title" style="font-size: 16px">
              <i class="el-icon-bank-card"></i>
              <span style="font-size: 16px">我的账单</span>
            </template>
            <el-menu-item index="2-1" @click="toPage2(2, 8)">查询</el-menu-item>
            <el-menu-item index="2-2">缴纳</el-menu-item>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-user"></i>
              <span style="font-size: 16px">我的好友
                <el-badge v-if="newFriend !== 0" class="mark" :value=newFriend :max="99" style="background-color: transparent" />
              </span>
            </template>
            <el-menu-item index="3-1" @click="toPage2(3, 9)">好友列表</el-menu-item>
            <el-menu-item index="3-2" @click="toPage2(3, 10)">添加好友</el-menu-item>
            <el-menu-item index="3-3" @click="toPage2(3, 11)">申请列表
              <el-badge v-if="newFriend !== 0" class="mark" :value=newFriend :max="99" style="background-color: transparent" />
            </el-menu-item>
          </el-submenu>
          <el-submenu index="4">
            <template slot="title">
              <i class="el-icon-message"></i>
              <span style="font-size: 16px">我的邮包
                <el-badge v-if="newParcel !== 0" class="mark" :value=newParcel :max="99" style="background-color: transparent" />
              </span>
            </template>
            <el-menu-item index="4-1" @click="toPage2(4, 12)">邮包通知
              <el-badge v-if="newParcel !== 0" class="mark" :value=newParcel :max="99" style="background-color: transparent" />
            </el-menu-item>
            <el-menu-item index="4-2" @click="toPage2(4, 13)">上门寄件</el-menu-item>
          </el-submenu>
          <el-menu-item index="5" @click="toPage2(5, 14)">
            <i class="el-icon-map-location"></i><span slot="title" style="font-size: 16px">我的周边</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-main>
        <keep-alive>
          <component :is="tabView"></component>
        </keep-alive>
      </el-main>
    </el-container>
    <el-dialog title="编辑个人信息" :visible.sync="dialogFormVisible">
      <el-form :model="userInfo">
        <el-form-item label="小区">
          <el-input v-model="userInfo.community" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="userInfo.username" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="userInfo.email"></el-input>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="userInfo.phone" :disabled="true"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleModify()">提 交</el-button>
      </div>
    </el-dialog>
  </el-container>
</template>

<script>
import page1 from './CommunityInformation.vue'
import page2 from './GroupBuy.vue'
import page3 from './Forum.vue'
import page4 from './MarketService.vue'
import page5 from './ComputerService.vue'
import page6 from './PropertyService.vue'
import page7 from './MyMessage.vue'
import page8 from './MyBill.vue'
import page9 from './MyFriend.vue'
import page10 from './MyFriendAdd.vue'
import page11 from './MyFriendApply.vue'
import page12 from './MyParcel.vue'
import page13 from './MySending.vue'
import page14 from './MySurround.vue'

export default {
  name: 'User',
  data () {
    return {
      tabView: 'page1',
      openList: ['1'],
      newMessage: 0,
      newFriend: 0,
      newParcel: 0,
      dialogFormVisible: false,
      userInfo: {
        community: '',
        communityId: 0,
        username: '',
        email: '',
        phone: ''
      }
    }
  },
  methods: {
    toPage1 (id) {
      this.openList[0] = '1'
      this.tabView = `page${id}`
    },
    toPage2 (id1, id2) {
      this.openList[0] = id1
      this.tabView = `page${id2}`
    },
    getNew () {
      this.$axios
        .get('')
        .then(response => {
          this.newMessage = response.data('message')
          this.newFriend = response.data('friend')
          this.newParcel = response.data('parcel')
        })
    },
    handleModify () {
      this.dialogFormVisible = false
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
        sessionStorage.setItem('communityAddress', response.data.address)
        sessionStorage.setItem('community', this.userInfo.community)
      })
    },
    loadM () {
      let bodyFormData = new FormData()
      bodyFormData.set('username', this.userInfo.username)
      let url = '/news-server/api/notice/findMyNotice'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        this.newMessage = response.data.length
      })
    },
    loadF () {
      let bodyFormData = new FormData()
      bodyFormData.set('username', this.userInfo.username)
      let url = '/user-server/api/friend/requestNumber'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        this.newFriend = response.data.number
      })
    },
    loadP () {
      let bodyFormData = new FormData()
      bodyFormData.set('username', this.userInfo.username)
      let url = '/package-server/api/Package/findNew'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        this.newParcel = response.data.length
      })
    }
  },
  components: {
    page1,
    page2,
    page3,
    page4,
    page5,
    page6,
    page7,
    page8,
    page9,
    page10,
    page11,
    page12,
    page13,
    page14
  },
  mounted () {
    this.loadData()
    this.loadM()
    this.loadF()
    this.loadP()
  }
}
</script>

<style scoped>

</style>
