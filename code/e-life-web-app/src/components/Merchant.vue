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
    <el-container style="padding-top: 20px">
      <el-aside width="220px" style="background-color: rgb(238, 241, 246)">
        <el-menu default-active="1" style="height:100vh;width:220px;float:left;overflow-y:auto">
          <div align="center">
            <i class="fas fa-3x fa-user-circle text-center tm-icon"></i>
            <div style="clear:both"></div>
            <el-button class="text-center tm-text-primary mb-4" type="primary" plain size="mini" icon="el-icon-edit" @click="toPage(1)">{{ userInfo.username }}</el-button>
            <div style="clear:both"></div>
          </div>
          <el-menu-item index="1" @click="toPage(2)">
            <i class="el-icon-shopping-bag-1"></i>
            <span slot="title" style="font-size: 16px">查看团购</span>
          </el-menu-item>
          <el-menu-item index="2" @click="toPage(3)">
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
import page1 from './MeInfo.vue'
import page2 from './MeGroup.vue'
import page3 from './MeDemand.vue'

export default {
  name: 'Merchant',
  data () {
    return {
      tabView: 'page2',
      userInfo: {
        community: '',
        communityId: 0,
        username: '',
        email: '',
        phone: ''
      },
      merchantInfo: {
        id: '',
        name: '',
        address: '',
        detail: '',
        type: '',
        merchantPhone: ''
      }
    }
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
        bodyFormData.set('username', this.userInfo.username)
        let url = '/user-server/api/merchant/getMerchantByUsername'
        this.$axios({
          method: 'post',
          url: url,
          data: bodyFormData,
          config: { headers: { 'Content-type': 'multipart/form-data' } } }
        ).then(response => {
          this.merchantInfo = response.data
          sessionStorage.setItem('id', this.merchantInfo.id)
          sessionStorage.setItem('name', this.merchantInfo.name)
          sessionStorage.setItem('address', this.merchantInfo.address)
          sessionStorage.setItem('detail', this.merchantInfo.detail)
          sessionStorage.setItem('type', this.merchantInfo.type)
          sessionStorage.setItem('merchantPhone', this.merchantInfo.merchantPhone)
          console.log(this.merchantInfo.id)
        })
      })
    },
    toPage (index) {
      this.tabView = `page${index}`
    }
  },
  components: {
    page1,
    page2,
    page3
  },
  mounted () {
    this.loadData()
  }
}
</script>

<style scoped>

</style>
