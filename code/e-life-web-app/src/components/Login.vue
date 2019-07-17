<template>
  <!-- Hero section -->
  <section id="hero" class="text-white tm-font-big tm-parallax">
    <!-- Navigation -->
    <nav class="navbar navbar-expand-md tm-navbar" id="tmNav">
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
              <router-link router-link :to="{name:'Login'}" class="nav-link tm-nav-link">登录</router-link>
            </li>
            <li class="nav-item">
              <router-link router-link :to="{name:'Register'}" class="nav-link tm-nav-link">注册</router-link>
            </li>
            <li class="nav-item">
              <a class="nav-link tm-nav-link" href="/#contact">联系我们</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <div class="text-center tm-hero-text-container">
      <div class="tm-hero-text-container-inner" style="padding-left: 32%; padding-top: 13%">
        <el-card class="box-card" style="width: 55%">
          <div slot="header" class="clearfix" style="margin-bottom: -20px">
            <i class="fas fa-2x fa-user-circle text-center tm-icon" style="float:left"></i>
            <h3 class="text-center tm-text-primary mb-4" style="float:left">&nbsp;&nbsp;用户登录</h3>
          </div>
          <el-form v-if="flag === 0" ref="form1" :model="form1" label-width="60px">
            <el-form-item label="用户名">
              <el-input v-model="form1.username"></el-input>
            </el-form-item>
            <el-form-item label="密码">
              <el-input v-model="form1.password" show-password></el-input>
            </el-form-item>
            <el-form-item label="身份">
              <el-radio v-model="form1.id" label="0">居民</el-radio>
              <el-radio v-model="form1.id" label="2">商家</el-radio>
              <el-radio v-model="form1.id" label="1">管理员</el-radio>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="login1()">登录</el-button>
              <el-button @click="change(1)">手机号快捷登录</el-button>
              <el-button @click="change(2)">找回密码</el-button>
            </el-form-item>
          </el-form>
          <el-form v-if="flag === 1" ref="form2" :model="form2" label-width="60px">
            <el-form-item label="手机号">
              <el-input v-model="form2.number" style="float:left; width: 64%"></el-input>
              <el-button type="primary" @click="getPass()" style="float:left">获取验证码</el-button>
            </el-form-item>
            <el-form-item label="验证码" >
              <el-input v-model="form2.pass"></el-input>
            </el-form-item>
            <el-form-item label="身份">
              <el-radio v-model="form2.id" label="0">居民</el-radio>
              <el-radio v-model="form2.id" label="2">商家</el-radio>
              <el-radio v-model="form2.id" label="1">管理员</el-radio>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="login2()">登录</el-button>
              <el-button @click="change(0)">用户名密码登录</el-button>
            </el-form-item>
          </el-form>
          <el-form v-if="flag === 2" ref="form3" :model="form3" label-width="60px">
            <el-form-item label="手机号">
              <el-input v-model="form3.number" style="float:left; width: 64%"></el-input>
              <el-button type="primary" @click="getPass()" style="float:left">获取验证码</el-button>
            </el-form-item>
            <el-form-item label="验证码" >
              <el-input v-model="form3.pass"></el-input>
            </el-form-item>
            <el-form-item label="新密码" >
              <el-input v-model="form3.newPassword"></el-input>
            </el-form-item>
            <el-form-item label="身份">
              <el-radio v-model="form3.id" label="0">居民</el-radio>
              <el-radio v-model="form3.id" label="2">商家</el-radio>
              <el-radio v-model="form3.id" label="1">管理员</el-radio>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="getBack()">找回密码</el-button>
              <el-button @click="change(0)">返回登录</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      flag: 0,
      form1: {
        id: '0',
        username: '',
        password: ''
      },
      form2: {
        id: '0',
        number: '',
        pass: ''
      },
      form3: {
        id: '0',
        number: '',
        pass: '',
        newPassword: ''
      }
    }
  },
  methods: {
    change (x) {
      this.flag = x
    },
    login1 () {
      if (this.form1.username === '' || this.form1.password === '') {
        this.$alert('请输入完整信息！')
        return 0
      }
      let url = '/user-server/api/user/login'
      let bodyFormData = new FormData()
      bodyFormData.set('username', this.form1.username)
      bodyFormData.set('password', this.form1.password)
      bodyFormData.set('id', this.form1.id)
      const self = this
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      )
        .then(response => {
          if (response.data.login === 1) {
            sessionStorage.setItem('username', response.data.username)
            sessionStorage.setItem('communityId', response.data.communityId)
            sessionStorage.setItem('phone', response.data.phone)
            sessionStorage.setItem('email', response.data.email)
            if (this.form1.id === '0') {
              self.$router.push({ name: 'User' })
            } else if (this.form1.id === '1') {
              self.$router.push({ name: 'Manager' })
            } else if (this.form1.id === '2') {
              self.$router.push({ name: 'Merchant' })
            }
          } else {
            this.$alert('登录失败！')
          }
        })
    },
    login2 () {
      if (this.form2.number === '' || this.form2.pass === '') {
        this.$alert('请输入完整信息！')
        return 0
      }
      let url = '/user-server/api/user/loginPhone'
      let bodyFormData = new FormData()
      bodyFormData.set('phone', this.form2.number)
      bodyFormData.set('code', this.form2.pass)
      bodyFormData.set('id', this.form2.id)
      const self = this
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      )
        .then(response => {
          if (response.data.login === 1) {
            sessionStorage.setItem('username', response.data.username)
            sessionStorage.setItem('communityId', response.data.communityId)
            sessionStorage.setItem('phone', response.data.phone)
            sessionStorage.setItem('email', response.data.email)
            if (this.form2.id === '0') {
              self.$router.push({ name: 'User' })
            } else if (this.form2.id === '1') {
              self.$router.push({ name: 'Manager' })
            } else if (this.form2.id === '2') {
              self.$router.push({ name: 'Merchant' })
            }
          } else {
            this.$alert('登录失败！')
          }
        })
    },
    getPass () {
      if (this.form2.number === '' && this.form3.number === '') {
        this.$alert('请输入手机号！')
      } else {
        let bodyFormData = new FormData()
        let form = (this.form2.number === '') ? this.form3 : this.form2
        this.$alert(form)
        bodyFormData.set('phone', form.number)
        bodyFormData.set('id', form.id)
        console.log(bodyFormData)
        this.$axios({
          method: 'post',
          url: '/user-server/api/user/phoneAvailable',
          data: bodyFormData,
          config: { headers: { 'Content-type': 'multipart/form-data' } } }
        ).then(response => {
          if (response.data.available === 0) {
            this.$axios({
              method: 'post',
              url: '/user-server/api/user/sendIdentify',
              data: bodyFormData,
              config: { headers: { 'Content-type': 'multipart/form-data' } } }
            ).then(response => {
              if (response.data === true) {
                this.$alert('验证码已发送！')
              } else {
                this.$alert('验证码发送失败！')
              }
            })
          } else {
            this.$alert(response.data)
            this.$alert('请输入正确的手机号！')
          }
        })
      }
      console.log('获取验证码！')
    },
    getBack () {
      if (this.form3.number === '' || this.form3.pass === '' || this.form3.newPassword === '') {
        this.$alert('请输入完整信息！')
        return 0
      }
      let url = '/user-server/api/user/findPassword'
      let bodyFormData = new FormData()
      bodyFormData.set('phone', this.form3.number)
      bodyFormData.set('code', this.form3.pass)
      bodyFormData.set('id', this.form3.id)
      bodyFormData.set('password', this.form3.newPassword)
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      )
        .then(response => {
          if (response.data.findPassword === 1) {
            this.$alert('找回密码成功！')
            this.$router.go(0)
          } else {
            this.$alert('找回密码失败！')
          }
        })
    }
  }
}
</script>

<style scoped>

</style>
