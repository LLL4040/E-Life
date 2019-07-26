<template>
  <div>
    <div align="left">
      <el-button style="margin-top: -20px" size="medium" type="primary" plain icon="el-icon-refresh" circle @click="refresh()"></el-button>
    </div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>编辑个人信息</span>
      </div>
      <el-form ref="form" :inline="true" :model="form" label-width="70px">
        <el-form-item label="用户名">
          <el-input v-model="form.username" style="width: 200px" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.email" style="width: 200px" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="form.phone" style="width: 200px" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="小区">
          <el-input v-model="form.community" style="width: 200px" :disabled="true"></el-input>
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
          <el-input v-model="form.detail" type="textarea" :rows="3" style="width: 200px"></el-input>
        </el-form-item>
        <!--<el-form-item label="商店地址" style="padding-right: 130px">
          <el-button style="float: right; padding: 10px 0; font-size: 16px;" type="text" @click="dialogFormVisible = true">选择地址</el-button>
        </el-form-item>-->
        <el-form-item label="商店地址">
          <el-input v-model="form.address" style="width: 200px" :disabled="true"></el-input>
        </el-form-item>
        <div style="clear:both"></div>
        <el-form-item style="padding-left: 400px;">
          <el-button type="primary" @click="save()">保存</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'MeInfo',
  data () {
    return {
      dialogFormVisible: false,
      form: {
        username: '',
        id: '',
        email: '',
        phone: '',
        community: '',
        name: '',
        merchantPhone: '',
        type: '',
        detail: '',
        address: ''
      },
      types: [{
        value: '超市送货',
        label: '超市送货'
      }, {
        value: '电脑维修',
        label: '电脑维修'
      }, {
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
      }]
    }
  },
  mounted () {
    this.loadData()
  },
  methods: {
    refresh () {
      this.loadData()
      this.$forceUpdate()
    },
    loadData () {
      this.form.username = sessionStorage.getItem('username')
      if (this.form.username === '' || this.form.username === null) {
        this.$router.push({ name: 'Login' })
      }
      this.form.id = sessionStorage.getItem('id')
      this.form.email = sessionStorage.getItem('email')
      this.form.phone = sessionStorage.getItem('phone')
      this.form.community = sessionStorage.getItem('community')
      this.form.name = sessionStorage.getItem('name')
      this.form.merchantPhone = sessionStorage.getItem('merchantPhone')
      this.form.type = sessionStorage.getItem('type')
      this.form.detail = sessionStorage.getItem('detail')
      this.form.address = sessionStorage.getItem('address')
    },
    save () {
      let bodyFormData = new FormData()
      bodyFormData.set('id', parseInt(this.form.id))
      bodyFormData.set('name', this.form.name)
      bodyFormData.set('merchantPhone', this.form.merchantPhone)
      bodyFormData.set('type', this.form.type)
      bodyFormData.set('address', this.form.address)
      bodyFormData.set('detail', this.form.detail)
      let url = '/user-server/api/merchant/changeMerchant'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          if (response.data.change === 1) {
            this.$alert('修改成功!')
            sessionStorage.setItem('name', this.form.name)
            sessionStorage.setItem('detail', this.form.detail)
            sessionStorage.setItem('type', this.form.type)
            sessionStorage.setItem('merchantPhone', this.form.merchantPhone)
          } else {
            this.$alert('修改失败！')
          }
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
