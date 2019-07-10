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
          <el-menu-item index="1">
            <i class="el-icon-chat-dot-round"></i><span slot="title" style="font-size: 16px">发布资讯</span>
          </el-menu-item>
          <el-menu-item index="2">
            <i class="el-icon-alarm-clock"></i><span slot="title" style="font-size: 16px">安排活动</span>
          </el-menu-item>
          <el-submenu index="3">
            <template slot="title" style="font-size: 16px">
              <i class="el-icon-setting"></i>
              <span style="font-size: 16px">管理用户</span>
            </template>
            <el-submenu index="3-1">
              <template slot="title">管理账单</template>
              <el-menu-item index="3-1-1">未缴费</el-menu-item>
              <el-menu-item index="3-1-2">已缴费</el-menu-item>
            </el-submenu>
            <el-menu-item index="3-2">通知用户</el-menu-item>
          </el-submenu>
          <el-submenu index="4">
            <template slot="title">
              <i class="el-icon-service"></i>
              <span style="font-size: 16px">处理报修
                <el-badge v-if="newRepaire !== 0" class="mark" :value=newRepaire :max="99" style="background-color: transparent" />
              </span>
            </template>
            <el-menu-item index="4-1">未处理
              <el-badge v-if="newRepaire !== 0" class="mark" :value=newRepaire :max="99" style="background-color: transparent" />
            </el-menu-item>
            <el-menu-item index="4-2">已处理</el-menu-item>
          </el-submenu>
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
export default {
  name: 'Manager',
  data () {
    return {
      tabView: 'page1',
      newRepaire: 10
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
          this.newRepaire = response.data
        })
    }
  },
  components: {
    page1,
    page2,
    page3,
    page4,
    page5,
    page6
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
