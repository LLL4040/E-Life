<template>
  <el-container style="border: 0 solid #eee">
    <el-header style="padding-left: 0">
      <section class="text-white tm-font-big tm-parallax">
        <nav class="navbar navbar-expand-md tm-navbar scroll" id="tmNav">
          <div class="container">
            <div class="tm-next">
              <img src="../../public/img/logo.gif" width="60px">
              <a class="navbar-brand" @click="toIndex()">E-LIFE</a>
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
            <el-tooltip class="item" effect="light" content="点击退出登录" placement="right">
              <el-button id="logout" type="success" plain size="mini" icon="el-icon-info" @click="logout()">商家</el-button>
            </el-tooltip>
            <div style="clear:both"></div>
            <el-tooltip class="item" effect="light" content="点击查看或修改个人信息" placement="right">
              <el-button type="primary" plain size="mini" icon="el-icon-edit" @click="toPage(1)">{{ userInfo.username }}</el-button>
            </el-tooltip>
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
          <el-menu-item index="4" @click="toPage(4)">
            <i class="el-icon-shopping-cart-2"></i>
            <span slot="title" style="font-size: 16px">优惠商品</span>
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
import page9 from './MyFriend.vue'
import page10 from './MyFriendAdd.vue'
import page11 from './MyFriendApply.vue'
import page4 from './MeBargain.vue'

export default {
  name: 'Merchant',
  data () {
    return {
      tabView: 'page2',
      openList: ['1'],
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
      },
      dialogFormVisible: false,
      newFriend: 0
    }
  },
  methods: {
    toIndex () {
      let bodyFormData = new FormData()
      let url = '/user-server/api/user/logout'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.logout === 1) {
          sessionStorage.clear()
          this.$router.push({ name: 'home' })
        } else {
          this.$alert('前往首页失败！请关闭页面重试')
        }
      })
    },
    logout () {
      let bodyFormData = new FormData()
      let url = '/user-server/api/user/logout'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.logout === 1) {
          sessionStorage.clear()
          this.$router.push({ name: 'Login' })
        } else {
          this.$alert('退出登录失败！请关闭页面重试')
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
        })
      })
    },
    toPage (index) {
      this.tabView = `page${index}`
    },
    toPage2 (id1, id2) {
      this.openList[0] = id1
      this.tabView = `page${id2}`
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
        if (response.data.login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          this.newFriend = response.data.number
        }
      })
    },
    exit () {
      /* eslint-disable */
      window.is_confirm = false
      // 关闭窗口时弹出确认提示
      $(window).bind('beforeunload', function () {
        // 只有在标识变量is_confirm不为false时，才弹出确认提示
        if (window.is_confirm !== false) {
          window.document.getElementById('logout').click()
          return '您可能有数据没有保存'
        }
      })
      // mouseleave mouseover事件也可以注册在body、外层容器等元素上
        .bind('mouseover mouseleave', function (event) {
          window.is_confirm = (event.type === 'mouseleave')
        })
    }
  },
  components: {
    page1,
    page2,
    page3,
    page9,
    page10,
    page11,
    page4
  },
  mounted () {
    this.loadData()
    this.loadF()
    this.exit()
  }
}
</script>

<style scoped>

</style>
