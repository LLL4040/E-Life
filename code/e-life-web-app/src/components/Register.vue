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
              <el-select v-model="form.communityId" filterable placeholder="输入小区名称关键字搜索">
              <el-option
                v-for="item in communities"
                :key="item.id"
                :label="item.label"
                :value="item.id">
                <span style="float: left">{{ item.label }}</span>
                <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>
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
          <el-form v-if="x === 1 && this.form.id !== '商家'" ref="form" :model="form" label-width="60px" style="padding-left: 20%; width: 77%">
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
              <el-button type="success" @click="preStep()">上一步</el-button>
              <el-button type="primary" @click="nextStep()">下一步</el-button>
            </el-form-item>
          </el-form>
          <el-form v-if="x === 1 && this.form.id === '商家'" ref="form" :inline="true" :model="form" label-width="70px">
            <el-form-item label="用户名">
              <el-input v-model="form.username" style="width: 200px"></el-input>
            </el-form-item>
            <el-form-item label="密码">
              <el-input v-model="form.password" show-password style="width: 200px"></el-input>
            </el-form-item>
            <el-form-item label="邮箱">
              <el-input v-model="form.email" style="width: 200px"></el-input>
            </el-form-item>
            <el-form-item label="商店名称">
              <el-input v-model="form.shop" style="width: 200px"></el-input>
            </el-form-item>
            <el-form-item label="商店电话">
              <el-input v-model="form.phone" style="width: 200px"></el-input>
            </el-form-item>
            <el-form-item label="商店类型">
              <el-select v-model="form.type" placeholder="请选择" style="width: 200px">
                <el-option v-for="item in types" :key="item.value" :label="item.label" :value="item.value"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="商店描述">
              <el-input v-model="form.description" type="textarea" :rows="2" style="width: 200px"></el-input>
            </el-form-item>
            <el-form-item label="商店地址" style="padding-right: 130px">
              <el-button style="float: right; padding: 10px 0; font-size: 16px;" type="text" @click="dialogFormVisible = true">选择地址</el-button>
            </el-form-item>
            <div style="clear:both"></div>
            <el-form-item>
              <el-button type="success" @click="preStep()">上一步</el-button>
              <el-button type="primary" @click="nextStep()">下一步</el-button>
            </el-form-item>
          </el-form>
          <el-form v-if="x === 2" ref="form" :model="form" label-width="60px" style="padding-left: 20%; width: 70%; padding-top: 5%">
            <el-form-item label="手机号">
              <el-input v-model="form.number" style="float:left; width: 64%"></el-input>
              <el-button type="primary" @click="getPass()" style="float:left">获取验证码</el-button>
            </el-form-item>
            <el-form-item label="验证码" >
              <el-input v-model="form.pass"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="success" @click="preStep()">上一步</el-button>
              <el-button type="primary" @click="submit()">提交</el-button>
            </el-form-item>
          </el-form>
          <el-form v-if="x === 3 && form.id === '管理员'" label-width="60px">
            <el-form-item>
              <p>提交成功！等待审核，请等待邮箱提醒。</p>
            </el-form-item>
          </el-form>
          <el-form v-if="x === 3 && form.id !== '管理员'" label-width="60px">
            <el-form-item>
              <br>
              <router-link router-link :to="{name:'Login'}" style="font-size: 17px; color: #3ba0dd">提交成功！点此跳转。</router-link>
            </el-form-item>
          </el-form>
        </el-card>
      </div>
    </div>
    <el-dialog title="商店地址选择" :visible.sync="dialogFormVisible">
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
      </div>
    </el-dialog>
  </section>
</template>

<script>
export default {
  name: 'Register',
  data () {
    return {
      x: 0,
      dialogFormVisible: false,
      form: {
        communityId: '',
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
        id: '1',
        value: '上海',
        label: 'xx小区'
      }],
      types: [{
        value: '周边餐饮',
        label: '周边餐饮'
      }, {
        value: '超市购物',
        label: '超市购物'
      }, {
        value: '休闲娱乐',
        label: '休闲娱乐'
      }, {
        value: '生活服务',
        label: '生活服务'
      }],
      result: ''
    }
  },
  mounted () {
    this.loadCommunity()
  },
  methods: {
    loadCommunity () {
      this.$axios
        .get('/user-server/api/user/communities')
        .then(response => {
          for (let com in response.data) {
            let item = {
              id: com['id'],
              value: com['information'],
              label: com['name']
            }
            this.communities.push(item)
          }
        })
    },
    preStep () {
      this.x--
    },
    nextStep () {
      console.log(this.form)
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
      this.x++
    },
    submit () {
      if (this.form.number === '' || this.form.pass === '') {
        this.$alert('表单必须填写完整才能提交！')
        return 0
      }
      // let url = ''
      // this.$axios
      //   .post(url, this.form)
      //   .then(response => {
      //     this.$alert(response.data)
      //   })
      this.x++
    },
    getPass () {
      console.log('获取验证码！')
    }
  }
}
</script>

<style scoped>

</style>
