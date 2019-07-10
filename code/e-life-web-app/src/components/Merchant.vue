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
            <i class="el-icon-shopping-bag-1"></i>
            <span slot="title" style="font-size: 16px">查看团购</span>
          </el-menu-item>
          <el-menu-item index="2">
            <i class="el-icon-alarm-clock"></i>
            <span slot="title" style="font-size: 16px">查看需求</span>
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
export default {
  name: 'Merchant',
  data () {
    return {
      tabView: 'page1'
    }
  },
  methods: {
    toPage (index) {
      this.tabView = `page${index}`
    }
  },
  components: {
    page1
  },
  mounted () {
    let start = window.location.href.lastIndexOf('/')
    this.activeIndex = window.location.href.slice(start + 1)
  }
}
</script>

<style scoped>

</style>
