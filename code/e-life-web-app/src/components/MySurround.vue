<template>
  <div>
    <el-row :gutter="10" style="padding-top: 20px">
      <el-col :span="16">
        <el-card class="box-card">
          <!--<baidu-map class="bm-view" center="上海" >
            <bm-navigation anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-navigation>
            <bm-geolocation anchor="BMAP_ANCHOR_BOTTOM_RIGHT" :auto-location="location" v-model="location" :showAddressBar="true" :autoLocation="true"></bm-geolocation>
          </baidu-map>-->
          <p>{{center}}</p>
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
import BMap from 'BMap'

export default {
  name: 'MySurround',
  data () {
    return {
      search: '',
      center: { lng: 116.40387397, lat: 39.91488908 },
      userInfo: {
        community: '',
        communityId: 0,
        username: '',
        email: '',
        phone: ''
      },
      merchantList: [],
      location: null
    }
  },
  mounted () {
    this.loadData()
    this.loadMerchant()
    this.createMap()
  },
  methods: {
    loadData () {
      this.userInfo.username = sessionStorage.getItem('username')
      if (this.userInfo.username === '' || this.userInfo.username === null) {
        this.$router.push({ name: 'Login' })
      }
      this.userInfo.phone = sessionStorage.getItem('phone')
      this.userInfo.communityId = sessionStorage.getItem('communityId')
      this.userInfo.email = sessionStorage.getItem('email')
      this.userInfo.community = sessionStorage.getItem('community')
    },
    loadMerchant () {
      let bodyFormData = new FormData()
      let url = '/user-server/api/merchant/findAll'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        this.merchantList = response.data
      })
    },
    createMap () {
      /* eslint-disable */
      // 创建Map实例
      let map = new BMap.Map("map")
      // 初始化地图,设置中心点坐标和地图级别
      let address = sessionStorage.getItem('communityAddress')
      let location = {"lng": address.split(',')[0].split(':')[1], "lat": address.split(',')[1].split(':')[1]}
      this.center = location
      let point = new BMap.Point(this.center.lng, this.center.lat)
      map.centerAndZoom(point, 16)
      //添加地图类型控件
      map.addControl(new BMap.MapTypeControl({
        mapTypes:[BMAP_NORMAL_MAP, BMAP_HYBRID_MAP]
      }))
      // 设置地图显示的城市 此项是必须设置的
      map.setCurrentCity("上海")
      //开启鼠标滚轮缩放
      map.enableScrollWheelZoom(true)
      map.enableDoubleClickZoom(true)

      //let icons = require('../../static/pin.png')
      let icons = '../../static/pin.png'
      let markers = new BMap.Marker(location)
      let icon = new BMap.Icon(icons, new BMap.Size(15, 15))
      markers.setIcon(icon)
      map.panTo(location)
      map.centerAndZoom(location, 16)
      map.addOverlay(markers)

      //定位

      // let geolocation = new BMap.Geolocation()
      // geolocation.getCurrentPosition((r) =>{
      //   if(r.point){
      //     this.center.lng = r.longitude
      //     this.center.lat = r.latitude
      //     let markers = new BMap.Marker(r.point)
      //     console.log(r.point)
      //     map.panTo(r.point)
      //     map.centerAndZoom(r.point, 16)
      //     let location = {"lng": this.merchantList[0].address.split(',')[0].split(':')[1], "lat": this.merchantList[0].address.split(',')[1].split(':')[1]}
      //     console.log(location)
      //     let marker = new BMap.Marker(location)
      //     map.addOverlay(marker)
    //   let geolocation = new BMap.Geolocation()
    //   geolocation.getCurrentPosition((r) =>{
    //     if(r.point){
    //       this.center.lng = r.longitude
    //       this.center.lat = r.latitude
    //       let markers = new BMap.Marker(r.point)
    //       map.panTo(r.point)
    //       map.centerAndZoom(r.point, 16)
    //       // let location = {"lng": this.merchantList[0].address.split(',')[0].split(':')[1], "lat": this.merchantList[0].address.split(',')[1].split(':')[1]}
    //       // console.log(location)
    //       // let marker = new BMap.Marker(location)
    //       // map.addOverlay(marker)
    //       // marker.addEventListener("click",function () {
    //       //   this.$alert("you")
    //       //   console.log("0001");
    //       //   //map.openInfoWindow(infoWindows,points);//参数：窗口、点  根据点击的点出现对应的窗口
    //       // });
    //   //   }
    //   // }, {enableHighAccuracy: true})
    //   // let i = 0
    //   // for(; i < this.merchantList.length; i++){
    //   //   let location = {"lng": this.merchantList[i].split(',')[0].split(':')[1], "lat": this.merchantList[i].split(',')[1].split(':')[1]}
    //   //   map.addOverlay(new BMap.Marker(location))
    //   // }
    //   // let location = {"lng": this.merchantList[0].address.split(',')[0].split(':')[1], "lat": this.merchantList[0].address.split(',')[1].split(':')[1]}
    //   // console.log(location)
    //   // map.addOverlay(new BMap.Marker(location))
    }
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
