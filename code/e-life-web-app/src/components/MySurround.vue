<template>
  <div>
    <el-row :gutter="10" style="padding-top: 20px">
      <el-col :span="16">
        <el-card class="box-card">
          <baidu-map class="bm-view" center="上海" >
            <bm-navigation anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-navigation>
            <bm-geolocation anchor="BMAP_ANCHOR_BOTTOM_RIGHT" :showAddressBar="true" :autoLocation="true"></bm-geolocation>
          </baidu-map>
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
        <el-card class="box-card">
          <el-input v-model="search" size="medium" suffix-icon="el-icon-search" placeholder="输入关键字搜索"/>
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
  },
  mounted () {

  },
  methods: {
    getLocation () {
      let Bmap = window.VueBaiduMap
      let geolocation = new Bmap.Geolocation()
    }
  }
}
</script>

<style>
  .bm-view {
    width: 100%;
    height: 450px;
  }
</style>
