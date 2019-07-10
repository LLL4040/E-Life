<template>
  <el-container style="border: 0 solid #eee">
    <el-header style="padding-left: 0">
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
                  <a class="nav-link tm-nav-link" @click="toPage(1)">小区资讯</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link tm-nav-link" @click="toPage(2)">小区团购</a>
                </li>
                <li class="nav-item">
                  <el-dropdown @command="toPage">
                    <span class="nav-link tm-nav-link" style="font-size: 1.25rem">
                      <i class="el-icon-arrow-down el-icon--right"></i>小区服务
                    </span>
                    <el-dropdown-menu slot="dropdown">
                      <el-dropdown-item command="4">超市送货</el-dropdown-item>
                      <el-dropdown-item command="5">电脑维修</el-dropdown-item>
                      <el-dropdown-item command="6">物业维修</el-dropdown-item>
                    </el-dropdown-menu>
                  </el-dropdown>
                </li>
                <li class="nav-item">
                  <a class="nav-link tm-nav-link" @click="toPage(3)">小区论坛</a>
                </li>
              </ul>
            </div>
          </div>
        </nav>
      </section>
    </el-header>
    <el-container style="padding-top: 30px">
      <el-aside width="220px" style="background-color: rgb(238, 241, 246)">
        <el-menu :default-openeds="[]" style="height:100vh;width:220px;float:left;overflow-y:auto">
          <div align="center">
            <i class="fas fa-3x fa-user-circle text-center tm-icon"></i>
            <h6 class="text-center tm-text-primary mb-4">用户名</h6>
            <div style="clear:both"></div>
          </div>
          <el-menu-item index="1" @click="toPage(7)">
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
            <el-menu-item index="2-1" @click="toPage(8)">查询</el-menu-item>
            <el-menu-item index="2-2">缴纳</el-menu-item>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-message"></i>
              <span style="font-size: 16px">我的好友
                <el-badge v-if="newFriend !== 0" class="mark" :value=newFriend :max="99" style="background-color: transparent" />
              </span>
            </template>
            <el-menu-item index="3-1" @click="toPage(9)">好友列表</el-menu-item>
            <el-menu-item index="3-2" @click="toPage(10)">添加好友</el-menu-item>
            <el-menu-item index="3-3" @click="toPage(11)">申请列表
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
            <el-menu-item index="4-1">邮包通知
              <el-badge v-if="newParcel !== 0" class="mark" :value=newParcel :max="99" style="background-color: transparent" />
            </el-menu-item>
            <el-menu-item index="4-2">上门寄件</el-menu-item>
          </el-submenu>
          <el-menu-item index="5">
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

export default {
  name: 'User',
  data () {
    return {
      tabView: 'page1',
      newMessage: 10,
      newFriend: 10,
      newParcel: 10
    }
  },
  methods: {
    toPage (index) {
      this.tabView = `page${index}`
    },
    getNew () {
      this.$axios
        .get('')
        .then(response => {
          this.newMessage = response.data('message')
          this.newFriend = response.data('friend')
          this.newParcel = response.data('parcel')
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
    page11
  },
  mounted () {
    // this.getNew()
    let start = window.location.href.lastIndexOf('/')
    this.activeIndex = window.location.href.slice(start + 1)
  }
}
</script>

<style scoped>

</style>
