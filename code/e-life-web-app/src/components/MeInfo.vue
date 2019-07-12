<template>
  <div>
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
          <el-input v-model="form.shop" style="width: 200px"></el-input>
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
        <el-form-item label="商店地址" style="padding-right: 130px">
          <el-button style="float: right; padding: 10px 0; font-size: 16px;" type="text" @click="dialogFormVisible = true">选择地址</el-button>
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
        id: '1',
        username: '233',
        email: '123',
        phone: '1551',
        community: '快乐小区',
        shop: '星海网咖',
        merchantPhone: '11111',
        type: '休闲娱乐',
        detail: '嘻嘻嘻',
        address: ''
      },
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
      }]
    }
  }
}
</script>

<style scoped>

</style>
