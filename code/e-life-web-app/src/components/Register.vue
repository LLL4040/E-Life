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
              <router-link router-link :to="{name:'home'}" class="nav-link tm-nav-link">联系我们</router-link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <div class="text-center tm-hero-text-container">
      <div class="tm-hero-text-container-inner" style="padding-left: 22%; padding-top: 13%">
        <el-card class="box-card" style="width: 80%">
          <el-steps :space="200" :active="x" finish-status="success" align-center>
            <el-step title="选择小区和身份"></el-step>
            <el-step title="填写账号信息"></el-step>
            <el-step title="真实身份认证"></el-step>
            <el-step title="注册结果"></el-step>
          </el-steps>
          <div slot="header" class="clearfix" style="margin-bottom: -20px">
            <i class="fas fa-2x fa-user-circle text-center tm-icon" style="float:left"></i>
            <h3 class="text-center tm-text-primary mb-4" style="float:left">&nbsp;&nbsp;用户注册</h3>
          </div>
          <el-form v-if="x === 0" ref="form" :model="form" label-width="60px" style="width: 70%; padding-left: 30%; padding-top: 5%">
            <el-form-item label="小区">
              <el-select v-model="form.community" filterable placeholder="可输入关键词进行搜索">
                <el-option
                  v-for="item in communities"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="身份">
              <el-select v-model="form.id" placeholder="请选择">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="nextStep()">下一步</el-button>
            </el-form-item>
          </el-form>
          <el-form v-if="x === 1 && this.form.id !== '商家'" ref="form" :model="form" label-width="60px">
            <el-form-item label="用户名">
              <el-input v-model="form.username"></el-input>
            </el-form-item>
            <el-form-item label="密码">
              <el-input v-model="form.password" show-password></el-input>
            </el-form-item>
            <el-form-item label="邮箱">
              <el-input v-model="form.email"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="nextStep()">下一步</el-button>
            </el-form-item>
          </el-form>
          <el-form v-if="x === 2" ref="form" :model="form" label-width="60px">
            <el-form-item label="手机号">
              <el-input v-model="form.number" style="float:left; width: 64%"></el-input>
              <el-button type="primary" @click="getPass()" style="float:left">获取验证码</el-button>
            </el-form-item>
            <el-form-item label="验证码" >
              <el-input v-model="form.pass"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submit()">提交</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: 'Register',
  data () {
    return {
      x: 0,
      form: {
        community: '',
        id: '',
        username: '',
        password: '',
        email: '',
        number: '',
        pass: '',
        shop: '',
        phone: '',
        address: '',
        type: '',
        description: ''
      },
      options: [{
        value: '居民',
        label: '居民'
      }, {
        value: '商家',
        label: '商家'
      }, {
        value: '管理员',
        label: '管理员'
      }],
      communities: [{
        value: 'xx小区',
        label: 'xx小区'
      }],
      result: ''
    }
  },
  methods: {
    nextStep () {
      if (this.x === 0) {
        if (this.form.community === '' || this.form.id === '') {
          this.$alert('表单必须填写完整才能进入下一步！')
          return 0
        }
      }
      if (this.x === 1) {
        if (this.form.username === '' || this.form.password === '' || this.form.email === '') {
          this.$alert('表单必须填写完整才能进入下一步！')
          return 0
        }
        if (this.form.id === '商家') {
          if (this.form.shop === '' || this.form.phone === '' || this.form.address === '' || this.form.type === '' || this.form.description === '') {
            this.$alert('表单必须填写完整才能进入下一步！')
            return 0
          }
        }
      }
      if (this.x === 2) {
        if (this.form.number === '' || this.form.pass === '') {
          this.$alert('表单必须填写完整才能提交！')
          return 0
        }
      }
      this.x++
    },
    submit () {
      let url = ''
      this.$axios
        .post(url, this.form)
        .then(response => {
          this.$alert(response.data)
        })
    },
    getPass () {
      console.log('获取验证码！')
    }
  }
}
</script>

<style scoped>

</style>
