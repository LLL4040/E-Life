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
    <el-row :gutter="10">
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>最新资讯</span>
          </div>
          <div v-for="o in 4" :key="o" class="text item">
            {{'列表内容 ' + o }}
          </div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>活动安排</span>
          </div>
          <div v-for="o in 4" :key="o" class="text item">
            {{'列表内容 ' + o }}
          </div>
<!--          <el-table :data="messages" style="width: 100%">-->
<!--            <el-table-column prop="time" label="时间" align="center"></el-table-column>-->
<!--            <el-table-column prop="manager" label="发送人" align="center"></el-table-column>-->
<!--            <el-table-column prop="content" label="内容" align="center"></el-table-column>-->
<!--            <el-table-column label="操作" align="center">-->
<!--              <template slot-scope="scope">-->
<!--                <el-button size="medium" type="danger" icon="el-icon-delete" circle @click="handleDelete(scope.$index, scope.row)"></el-button>-->
<!--              </template>-->
<!--            </el-table-column>-->
<!--          </el-table>-->
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
      activity: [{
      }]
    }
  },
  mounted () {
  }
}
</script>

<style scoped>
</style>
