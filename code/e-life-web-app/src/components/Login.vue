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
              <el-input v-model="form1.password"></el-input>
            </el-form-item>
            <el-form-item label="身份">
              <el-radio v-model="form1.id" label="0">小区居民或商家</el-radio>
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
              <el-radio v-model="form2.id" label="0">居民或商家</el-radio>
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
              <el-radio v-model="form3.id" label="0">居民或商家</el-radio>
              <el-radio v-model="form3.id" label="1">管理员</el-radio>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="getBack()">登录</el-button>
              <el-button @click="change(0)">用户名密码登录</el-button>
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
      let url = ''
      this.$axios
        .post(url, this.form1)
        .then(response => {
          this.$alert(response.data)
        })
    },
    login2 () {
      let url = ''
      this.$axios
        .post(url, this.form2)
        .then(response => {
          this.$alert(response.data)
        })
    },
    getPass () {
      console.log('获取验证码！')
    },
    getBack () {
      let url = ''
      this.$axios
        .post(url, this.form3)
        .then(response => {
          this.$alert(response.data)
        })
    }
  }
}
</script>

<style scoped>

</style>
