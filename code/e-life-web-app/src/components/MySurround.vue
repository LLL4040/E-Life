<template>
  <div>
    <el-row :gutter="10" style="padding-top: 20px">
      <el-col :span="16">
        <el-card class="box-card">
          <!--<baidu-map class="bm-view" center="上海" >
            <bm-navigation anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-navigation>
            <bm-geolocation anchor="BMAP_ANCHOR_BOTTOM_RIGHT" :showAddressBar="true" :autoLocation="true"></bm-geolocation>
          </baidu-map>-->
          <div id="map"></div>
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
// import BMap from 'BMap'

export default {
  name: 'MySurround',
  data () {
    return {
      search: ''
    }
  },
  mounted () {
    this.createMap()
  },
  methods: {
    // createMap () {
    //   /* eslint-disable */
    //   // 创建Map实例
    //   let map = new BMap.Map("map")
    //   // 初始化地图,设置中心点坐标和地图级别
    //   map.centerAndZoom(new BMap.Point(116.404, 39.915), 11)
    //   //添加地图类型控件
    //   map.addControl(new BMap.MapTypeControl({
    //     mapTypes:[BMAP_NORMAL_MAP, BMAP_HYBRID_MAP]
    //   }))
    //   // 设置地图显示的城市 此项是必须设置的
    //   map.setCurrentCity("北京")
    //   //开启鼠标滚轮缩放
    //   map.enableScrollWheelZoom(true)
    // }
  }
}
</script>

<style>
  .bm-view {
    width: 100%;
    height: 450px;
  }
  #map{
    width: 100%;
    height: 450px;
  }
</style>
