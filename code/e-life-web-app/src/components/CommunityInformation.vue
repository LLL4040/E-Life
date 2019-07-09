<template>
  <div>
    <div align="center" style="width: 800px; padding-left: 200px">
      <el-carousel indicator-position="outside" :interval="4000" height="300px">
        <el-carousel-item v-for="item in 3" :key="item">
          <el-image style="width: 500px; height: 300px" :src="require('../../public/img/alert.jpg')"></el-image>
          <h4 style="position: relative; bottom: 150px; left: 10px">暂时没有紧急通知哦</h4>
        </el-carousel-item>
      </el-carousel>
    </div>
    <el-row :gutter="20">
      <el-col :span="12">
        <el-card class="box-card" shadow="never">
          <div slot="header" class="clearfix">
            <span>最新资讯</span>
          </div>
          <div v-for="o in 4" :key="o" class="text item">
            {{'列表内容 ' + o }}
          </div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="box-card" shadow="never">
          <div slot="header" class="clearfix">
            <span>活动安排</span>
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
  name: 'CommunityInformation',
  data () {
    return {
      notice: [],
      news: [],
      activity: []
    }
  },
  mounted () {
  }
}
</script>

<style scoped>
</style>
