<template>
  <div>
    <div align="center">
      <el-input v-model="search" size="medium" style="width: 300px" suffix-icon="el-icon-search" placeholder="输入关键字搜索"/>
    </div>
    <el-row :gutter="20" style="padding-top: 20px">
      <el-col :span="12">
        <el-card class="box-card" shadow="never">
          <div slot="header" class="clearfix">
            <span>商家团购信息</span>
          </div>
          <div v-for="o in 4" :key="o" class="text item">
            {{'列表内容 ' + o }}
          </div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="box-card" shadow="never">
          <div slot="header" class="clearfix">
            <span>自发团购需求</span>
            <el-button style="float: right; padding: 3px 0; font-size: 16px;" type="text">我要开团</el-button>
          </div>
          <div v-for="o in 4" :key="o" class="text item">
            {{'列表内容 ' + o }}
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'GroupBuy'
}
</script>

<style scoped>

</style>
