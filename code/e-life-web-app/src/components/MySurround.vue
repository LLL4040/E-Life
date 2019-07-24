<template>
  <div>
    <el-row :gutter="10" style="padding-top: 20px">
      <el-col :span="16">
        <el-card class="box-card">
          <div id="map"></div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>优惠商品搜索</span>
            <el-button size="medium" style="float: right" type="primary" @click="showBargain()">全部优惠商品</el-button>
          </div>
          <el-input v-model="search" size="mini" style="width: 235px" placeholder="搜索优惠商品"/>
          <el-button icon="el-icon-search" size="mini" type="primary" @click="searchBargain()"></el-button>
        </el-card>
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>商家分类</span>
            <el-button size="medium" style="float: right" type="primary" @click="createMap()">全部商家</el-button>
          </div>
          <el-row :gutter="25">
            <el-col :span="8">
              <el-tag color="blue" effect="dark" style="border: 0">超市购物</el-tag>
            </el-col>
            <el-col :span="8">
              <el-tag color="green" effect="dark" style="border: 0">休闲娱乐</el-tag>
            </el-col>
            <el-col :span="8">
              <el-tag color="saddleBrown" effect="dark" style="border: 0">周边餐饮</el-tag>
            </el-col>
            <el-col :span="8">
              <el-tag color="purple" effect="dark" style="border: 0">生活服务</el-tag>
            </el-col>
          </el-row>
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
      bargainList: [],
      location: null
    }
  },
  mounted () {
    this.loadData()
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
      this.loadMerchant()
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
        if (response.data.length > 0 && response.data[0].login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          this.merchantList = response.data
          this.loadBargain()
        }
      })
    },
    loadBargain () {
      let url = '/group-server/api/bargain/getAllBargain'
      this.$axios({
        method: 'get',
        url: url,
        config: { headers: { 'Content-type': 'multipart/form-data' } }
      }).then(response => {
        if (response.data.length > 0 && response.data[0].login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          this.bargainList = response.data
          this.createMap()
        }
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

      //let icons = require('../../static/brown.png')
      let markers = new BMap.Marker(point)

      let infoWindow = new BMap.InfoWindow("<p style='font-size:14px;'>"+ this.userInfo.community +"</p>");  //弹出窗口
      map.panTo(location)
      map.centerAndZoom(location, 16)
      map.addOverlay(markers)
      markers.addEventListener("click", function(){
        this.openInfoWindow(infoWindow);
      })
      let i = 0
      for (; i < this.merchantList.length; i++){
        let point = new BMap.Point(this.merchantList[i].address.split(',')[0].split(':')[1], this.merchantList[i].address.split(',')[1].split(':')[1])
        let icons
        switch (this.merchantList[i].type)
        {
          case '周边餐饮': icons = require('../../static/brown.png')
            break
          case '超市送货':
          case '超市购物': icons = require('../../static/blue.png')
            break
          case '休闲娱乐': icons = require('../../static/green.png')
            break
          case '电脑维修':
          case '生活服务': icons = require('../../static/purple.png')
            break
          // default: icons = require('../../static/black.png')
        }
        let info = "<p style='font-size:14px;'>" + this.merchantList[i].name + "</p>"
           + "<p style='font-size:14px;'>" + this.merchantList[i].detail + "</p>"
        let infoWindow = new BMap.InfoWindow(info)
        let icon = new BMap.Icon(icons, new BMap.Size(18, 35),{
          anchor: new BMap.Size(9, 34)
        })
        mark(point, icon, infoWindow)
      }
      function mark (point, icon, infoWindow) {
        let marker = new BMap.Marker(point)
        map.addOverlay(marker)
        marker.setIcon(icon)
        marker.addEventListener("click", function(){
          this.openInfoWindow(infoWindow);
        })
      }
    },
    searchBargain () {
      let data = []
      for(let i = 0; i < this.bargainList.length; i++){
        let bargain = this.bargainList[i]
        if((bargain.content.indexOf(this.search) !== -1) || (bargain.title.indexOf(this.search) !== -1)){
          data.push(bargain)
        }
      }
      this.$alert(data)
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

      //let icons = require('../../static/brown.png')
      let markers = new BMap.Marker(point)
      let infoWindow = new BMap.InfoWindow("<p style='font-size:14px;'>"+ this.userInfo.community +"</p>");  //弹出窗口
      map.panTo(location)
      map.centerAndZoom(location, 16)
      map.addOverlay(markers)
      markers.addEventListener("click", function(){
        this.openInfoWindow(infoWindow);
      })
      let i = 0
      for (; i < data.length; i++){
        let point = new BMap.Point(data[i].address.split(',')[0].split(':')[1], data[i].address.split(',')[1].split(':')[1])
        let icons = require('../../static/black.png')
        let info = "<p style='font-size:14px;'>" + data[i].title + "</p>"
          + "<p style='font-size:14px;'>" + data[i].goods + "</p>"
          + "<p style='font-size:14px;'>" + data[i].content + "</p>"
        let infoWindow = new BMap.InfoWindow(info)
        let icon = new BMap.Icon(icons, new BMap.Size(18, 35),{
          anchor: new BMap.Size(9, 34)
        })
        mark(point, icon, infoWindow)
      }
      function mark (point, icon, infoWindow) {
        let marker = new BMap.Marker(point)
        map.addOverlay(marker)
        marker.setIcon(icon)
        marker.addEventListener("click", function(){
          this.openInfoWindow(infoWindow);
        })
      }
    },
    showBargain () {
      /* eslint-disable */
      // 创建Map实例
      let map = new BMap.Map("map")
      // 初始化地图,设置中心点坐标和地图级别
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

      let markers = new BMap.Marker(point)
      let infoWindow = new BMap.InfoWindow("<p style='font-size:14px;'>"+ this.userInfo.community +"</p>");  //弹出窗口
      map.panTo(location)
      map.centerAndZoom(location, 16)
      map.addOverlay(markers)
      markers.addEventListener("click", function(){
        this.openInfoWindow(infoWindow);
      })
      let i = 0
      for (; i < this.bargainList.length; i++){
        let point = new BMap.Point(this.bargainList[i].address.split(',')[0].split(':')[1], this.bargainList[i].address.split(',')[1].split(':')[1])
        let icons = require('../../static/black.png')
        let info = "<p style='font-size:14px;'>" + this.bargainList[i].title + "</p>"
          + "<p style='font-size:14px;'>" + this.bargainList[i].goods + "</p>"
          + "<p style='font-size:14px;'>" + this.bargainList[i].content + "</p>"
        let infoWindow = new BMap.InfoWindow(info)
        let icon = new BMap.Icon(icons, new BMap.Size(18, 35),{
          anchor: new BMap.Size(9, 34)
        })
        mark(point, icon, infoWindow)
      }
      function mark (point, icon, infoWindow) {
        let marker = new BMap.Marker(point)
        map.addOverlay(marker)
        marker.setIcon(icon)
        marker.addEventListener("click", function(){
          this.openInfoWindow(infoWindow);
        })
      }
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
