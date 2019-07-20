<template>
  <div>
    <div align="center">
      <el-input v-model="search" size="medium" style="width: 350px" suffix-icon="el-icon-search" placeholder="输入内容关键字筛选"/>
    </div>
    <div style="padding-top: 20px;">
      <el-card class="box-card" :gutter="24">
        <el-col span="12">
          <el-card class="box-card">
            <div id="map"></div>
          </el-card>
        </el-col>
        <el-col span="12">
          <el-table :data="shop.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
            <el-table-column type="expand">
              <template slot-scope="props">
                <el-form label-position="left" inline class="demo-table-expand">
                  <el-form-item label="详细描述">
                    <span>{{ props.row.detail }}</span>
                  </el-form-item>
                </el-form>
              </template>
            </el-table-column>
            <el-table-column prop="id" label="编号"></el-table-column>
            <el-table-column prop="name" label="商店名称"></el-table-column>
            <el-table-column prop="phone" label="商店电话"></el-table-column>
          </el-table>
        </el-col>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MarketService',
  data () {
    return {
      search: '',
      userInfo: {
        community: '',
        communityId: 0,
        username: '',
        email: '',
        phone: ''
      },
      communityAddress: '',
      shop: []
    }
  },
  methods: {
    loadData () {
      this.userInfo.username = sessionStorage.getItem('username')
      if (this.userInfo.username === '' || this.userInfo.username === null) {
        this.$router.push({ name: 'Login' })
      }
      this.userInfo.phone = sessionStorage.getItem('phone')
      this.userInfo.community = sessionStorage.getItem('community')
      this.userInfo.communityId = sessionStorage.getItem('communityId')
      this.userInfo.email = sessionStorage.getItem('email')
      this.communityAddress = sessionStorage.getItem('communityAddress')
      this.loadShop()
    },
    loadShop () {
      let bodyFormData = new FormData()
      bodyFormData.set('type', '超市送货')
      let url = '/user-server/api/merchant/findAllByType'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        this.shop = response.data
        this.loadMap()
      })
    },
    loadMap () {
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
      for (; i < this.shop.length; i++){
        let point = new BMap.Point(this.shop[i].address.split(',')[0].split(':')[1], this.shop[i].address.split(',')[1].split(':')[1])
        let icons = require('../../static/black.png')
        let info = "<p style='font-size:14px;'>" + this.shop[i].name + "</p>"
          + "<p style='font-size:14px;'>" + this.shop[i].detail + "</p>"
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
  },
  mounted () {
    this.loadData()
  }
}
</script>

<style scoped>
  #map{
    width: 100%;
    height: 450px;
  }
</style>
