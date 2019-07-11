<template>
  <div>
    <div align="center">
      <el-input v-model="search" size="medium" style="width: 300px" suffix-icon="el-icon-search" placeholder="输入关键字搜索"/>
    </div>
    <el-row :gutter="10" style="padding-top: 20px">
      <el-col :span="16">
        <el-card class="box-card">
          <div></div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>商品分类</span>
          </div>
          <el-row :gutter="10">
            <el-col :span="8">
              <el-tag>超市购物</el-tag>
            </el-col>
            <el-col :span="8">
              <el-tag type="success">休闲娱乐</el-tag>
            </el-col>
            <el-col :span="8">
              <el-tag type="warning">周边餐饮</el-tag>
            </el-col>
            <el-col :span="8">
              <el-tag type="danger">生活服务</el-tag>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'MySurround',
  data () {
    return {
      search: ''
    }
  }
}
</script>

<style scoped>

</style>
