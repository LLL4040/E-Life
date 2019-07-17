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
          <el-form v-if="x === 1 && this.form.id !== '商家'" :model="form" :rules="rules" ref="form" label-width="60px"  class="demo-ruleForm" style="padding-left: 20%; width: 77%">
            <el-form-item label="用户名" prop="username" >
              <el-input v-model="form.username" style="float:left; width: 60%"></el-input>
              <el-button type="primary" @click="checkName()" style="float:left">验证是否可用</el-button>
            </el-form-item>
            <el-form-item label="密码" prop="password" >
              <el-input v-model="form.password" show-password></el-input>
            </el-form-item>
            <el-form-item label="邮箱" prop="email" >
              <el-input v-model="form.email"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="success" @click="preStep()">上一步</el-button>
              <el-button type="primary" @click="nextStepAndValid(form)">下一步</el-button>
            </el-form-item>
          </el-form>
          <el-form v-if="x === 1 && this.form.id === '商家'" ref="form" :inline="true" :model="form" label-width="70px">
            <el-form-item label="用户名">
              <el-input v-model="form.username" style="width: 200px"></el-input>
              <el-button type="primary" @click="checkName()" style="float:left">验证是否可用</el-button>
            </el-form-item>
            <el-form-item label="密码">
              <el-input v-model="form.password" show-password style="width: 200px"></el-input>
            </el-form-item>
            <el-form-item label="邮箱">
              <el-input v-model="form.email" style="width: 200px"></el-input>
            </el-form-item>
            <el-form-item label="商店名称">
              <el-input v-model="form.name" style="width: 200px"></el-input>
            </el-form-item>
            <el-form-item label="商店电话">
              <el-input v-model="form.merchantPhone" style="width: 200px"></el-input>
            </el-form-item>
            <el-form-item label="商店类型">
              <el-select v-model="form.type" placeholder="请选择" style="width: 200px">
                <el-option v-for="item in types" :key="item.value" :label="item.label" :value="item.value"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="商店描述">
              <el-input v-model="form.detail" type="textarea" :rows="2" style="width: 200px"></el-input>
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
              <el-input v-model="form.phone" style="float:left; width: 64%"></el-input>
              <el-button type="primary" @click="getPass()" style="float:left">获取验证码</el-button>
            </el-form-item>
            <el-form-item label="验证码" >
              <el-input v-model="form.code"></el-input>
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
        <div id="all">
          <input type="text" id="suggestId" name="address_detail" placeholder="地址" v-model="address_detail" class="input_style" >
          <div id="allmap"></div>
        </div>
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
      </div>
    </el-dialog>
  </section>
</template>

<script>
import BMap from 'BMap'
export default {
  name: 'Register',
  data () {
    let checkName = (rule, value, callback) => {
      if (!value) {
        callback(new Error('请输入用户名'))
      } else {
        callback()
      }
    }
    let checkEmail = (rule, value, callback) => {
      if (!value) {
        callback(new Error('请输入邮箱地址'))
      } else {
        callback()
      }
    }
    let validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.form.checkPass !== '') {
          this.$refs.form.validateField('checkPass')
        }
        callback()
      }
    }
    // let validatePass2 = (rule, value, callback) => {
    //   if (value === '') {
    //     callback(new Error('请再次输入密码'))
    //   } else if (value !== this.form.password) {
    //     callback(new Error('两次输入密码不一致!'))
    //   } else {
    //     callback()
    //   }
    // }
    return {
      x: 0,
      address_detail: '',
      userlocation: { lng: '', lat: '' },
      dialogFormVisible: false,
      form: {
        id: '',
        username: '',
        password: '',
        phone: '',
        code: '',
        email: '',
        communityId: '',
        name: '',
        merchantPhone: '',
        address: '',
        type: '',
        detail: ''
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
      communities: [],
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
      result: -1,
      rules: {
        username: [
          { validator: checkName, trigger: 'blur' },
          { required: true }
        ],
        password: [
          { validator: validatePass, trigger: 'blur' },
          { required: true }
        ],
        // checkPass: [
        //   { validator: validatePass2, trigger: 'blur' },
        //   { required: true }
        // ],
        email: [
          { validator: checkEmail, trigger: 'blur' },
          { required: true },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
        ]
      },
      formRules: {
        /* eslint-disable */
        name:
          [{required: true,message: '请输入用户名',trigger: 'blur'},
            {min: 5,max: 30,message: '长度在 5 到 30 个字符'},
            //{pattern: /^[\u4E00-\u9FA5]+$/, message: '用户名只能为中文'}],
            //{ pattern:/^[a-zA-Z]w{1,4}$/, message: '以字母开头，长度在2-5之间， 只能包含字符、数字和下划线'}],
          ],
        password:
          [{required: true,message: '请输入密码',trigger: 'blur'},
            { min: 5,max: 25,message: '长度在 5 到 25 个字符'},
            {pattern: /^(\w){5,25}$/, message: '只能输入5-25个字母、数字、下划线'}],
        phone:
          [{ required: true,message: '请输入手机号码',trigger: 'blur'},
            {validator:function(rule,value,callback){
              if(/^1[34578]\d{9}$/.test(value) == false){
                callback(new Error("请输入正确的手机号"));
              }else{
                callback();
              }
            }, trigger: 'blur'}],
        email:
          [{required: true, message: '请输入邮箱号', trigger: 'blur'},
            {validator:function(rule,value,callback){
              if (value === '') {
                callback(new Error('请正确填写邮箱'));
              } else {
                if (value !== '') {
                  var reg=/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
                  if(!reg.test(value)){
                    callback(new Error('请输入有效的邮箱'));
                  }
                }else{
                  callback();
                }
              }
            }, trigger: 'blur'}],
      }
    }
  },
  mounted () {
    /* eslint-disable */
    this.loadCommunity()
    this.$nextTick(function () {
      let th = this
      // 创建Map实例
      let map = new BMap.Map('allmap')
      // 初始化地图,设置中心点坐标，
      let point = new BMap.Point(121.160724, 31.173277) // 创建点坐标，汉得公司的经纬度坐标
      map.centerAndZoom(point, 15)
      map.enableScrollWheelZoom()
      let geolocation = new BMap.Geolocation()
      geolocation.getCurrentPosition((r) => {
        if (r.point) {
          // let markers = new BMap.Marker(r.point)
          map.panTo(r.point)
          map.centerAndZoom(r.point, 16)
        }
      }, { enableHighAccuracy: true })
      var ac = new BMap.Autocomplete( // 建立一个自动完成的对象
        {
          'input': 'suggestId',
          'location': map
        })
      var myValue
      ac.addEventListener('onconfirm', function (e) { // 鼠标点击下拉列表后的事件
        var _value = e.item.value
        myValue = _value.province + _value.city + _value.district + _value.street + _value.business
        this.address_detail = myValue
        setPlace()
      })

      function setPlace () {
        map.clearOverlays() // 清除地图上所有覆盖物
        function myFun () {
          th.userlocation = local.getResults().getPoi(0).point // 获取第一个智能搜索的结果
          map.centerAndZoom(th.userlocation, 18)
          map.addOverlay(new BMap.Marker(th.userlocation)) // 添加标注
        }

        var local = new BMap.LocalSearch(map, { // 智能搜索
          onSearchComplete: myFun
        })
        local.search(myValue)

        // 测试输出坐标（指的是输入框最后确定地点的经纬度）
        map.addEventListener('click', function (e) {
          // 经度
          console.log(th.userlocation.lng)
          // 纬度
          console.log(th.userlocation.lat)
        })
      }
    })
  },
  methods: {
    nextStepAndValid (form) {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          this.x++
        } else {
          alert('表单信息不正确，请重新填写！');
          return false;
        }
      });
    },
    loadCommunity () {
      this.$axios
        .get('/user-server/api/user/communities')
        .then(response => {
          for (let i = 0; i < response.data.length; i++) {
            let item = {
              id: response.data[i].id,
              value: response.data[i].information,
              label: response.data[i].name
            }
            this.communities.push(item)
          }
        })
    },
    preStep () {
      this.x--
    },
    nextStep () {
      if (this.x === 0) {
        if (this.form.communityId === '' || this.form.id === '') {
          this.$alert('表单必须填写完整才能进入下一步！')
          return 0
        }
      }
      if (this.x === 1) {
        if (this.form.username === '' || this.form.password === '' || this.form.email === '') {
          this.$alert('表单必须填写完整才能进入下一步！')
          return 0
        }
        if (this.result !== 0 && this.result !== 1) {
          this.$alert('请验证用户名是否可用！')
          return 0
        }
        if (this.result === 0) {
          this.$alert('请更换用户名！')
          return 0
        }
        if (this.form.id === '商家') {
          if (this.form.name === '' || this.form.merchantPhone === '' || this.form.type === '' || this.form.detail === '') {
            this.$alert('表单必须填写完整才能进入下一步！')
            return 0
          }
        }
      }
      this.x++
    },
    submit () {
      if (this.form.phone === '' || this.form.code === '') {
        this.$alert('表单必须填写完整才能提交！')
        return 0
      }
      if (this.form.id === '居民') {
        let bodyFormData = new FormData()
        bodyFormData.set('phone', this.form.phone)
        bodyFormData.set('code', this.form.code)
        bodyFormData.set('username', this.form.username)
        bodyFormData.set('communityId', this.form.communityId)
        bodyFormData.set('password', this.form.password)
        bodyFormData.set('email', this.form.email)
        this.$axios({
          method: 'post',
          url: '/user-server/api/user/register',
          data: bodyFormData,
          config: { headers: { 'Content-type': 'multipart/form-data' } } }
        )
          .then(response => {
            if (response.data.register === 1) {
              this.x++
            } else {
              this.$alert('验证码错误！')
            }
          })
      } else if (this.form.id === '管理员') {
        let bodyFormData = new FormData()
        bodyFormData.set('phone', this.form.phone)
        bodyFormData.set('code', this.form.code)
        bodyFormData.set('username', this.form.username)
        bodyFormData.set('communityId', this.form.communityId)
        bodyFormData.set('password', this.form.password)
        bodyFormData.set('email', this.form.email)
        this.$axios({
          method: 'post',
          url: '/user-server/api/user/registerManager',
          data: bodyFormData,
          config: { headers: { 'Content-type': 'multipart/form-data' } } }
        )
          .then(response => {
            if (response.data.register === 1) {
              this.x++
            } else {
              this.$alert('验证码错误！')
            }
          })
      } else if (this.form.id === '商家') {
        let bodyFormData = new FormData()
        bodyFormData.set('username', this.form.username)
        bodyFormData.set('password', this.form.password)
        bodyFormData.set('email', this.form.email)
        bodyFormData.set('name', this.form.name)
        bodyFormData.set('merchantPhone', this.form.merchantPhone)
        bodyFormData.set('type', this.form.type)
        this.form.address = 'lng:' + this.userlocation.lng + ',' + 'lat:' + this.userlocation.lat
        bodyFormData.set('address', this.form.address)
        bodyFormData.set('detail', this.form.detail)
        bodyFormData.set('phone', this.form.phone)
        bodyFormData.set('code', this.form.code)
        bodyFormData.set('communityId', this.form.communityId)
        this.$axios({
          method: 'post',
          url: '/user-server/api/merchant/register',
          data: bodyFormData,
          config: { headers: { 'Content-type': 'multipart/form-data' } } }
        )
          .then(response => {
            if (response.data.register === 1) {
              this.x++
            } else {
              this.$alert('验证码错误！')
            }
          })
      }
    },
    getPass () {
      if (this.form.phone === '') {
        this.$alert('请输入手机号！')
      } else {
        let bodyFormData = new FormData()
        bodyFormData.set('phone', this.form.phone)
        if (this.form.id === '管理员') {
          bodyFormData.set('id', '1')
        } else {
          bodyFormData.set('id', '0')
        }
        this.$axios({
          method: 'post',
          url: '/user-server/api/user/phoneAvailable',
          data: bodyFormData,
          config: { headers: { 'Content-type': 'multipart/form-data' } } }
        ).then(response => {
          if (response.data.available === 1) {
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
            this.$alert('手机号不可用，请更换手机号！')
          }
        })
      }
      console.log('获取验证码！')
    },
    checkName () {
      if (this.form.username === '') {
        this.$alert('请输入用户名！')
      } else if (this.form.id === '居民' || this.form.id === '商家') {
        let bodyFormData = new FormData()
        bodyFormData.set('username', this.form.username)
        this.$axios({
          method: 'post',
          url: '/user-server/api/user/usernameAvailable',
          data: bodyFormData,
          config: { headers: { 'Content-type': 'multipart/form-data' } } }
        ).then(response => {
          if (response.data.available === 1) {
            this.$alert('用户名可用！')
            this.result = 1
          } else {
            this.$alert('用户名重复，请更换用户名！')
            this.result = 0
          }
        })
      } else if (this.form.id === '管理员') {
        let bodyFormData = new FormData()
        bodyFormData.set('username', this.form.username)
        this.$axios({
          method: 'post',
          url: '/user-server/api/user/usernameAvailableManager',
          data: bodyFormData,
          config: { headers: { 'Content-type': 'multipart/form-data' } } }
        ).then(response => {
          if (response.data.available === 1) {
            this.$alert('用户名可用！')
            this.result = 1
          } else {
            this.$alert('用户名重复，请更换用户名！')
            this.result = 0
          }
        })
      }
    }
  }
}
</script>

<style>
  #allmap{
     width: 300px;
     height: 300px;
     font-family: "微软雅黑";
     border:1px solid green;
   }
  .tangram-suggestion-main {z-index: 9999999999;}
</style>
