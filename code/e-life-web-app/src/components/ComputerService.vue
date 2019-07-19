<template>
  <div>
    <div align="center">
      <el-input v-model="search" size="medium" style="width: 350px" suffix-icon="el-icon-search" placeholder="输入内容关键字筛选"/>
    </div>
    <div style="padding-top: 20px;">
      <el-card class="box-card">
        <el-table :data="shop.filter(data => !search || data.shop.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
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
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ComputerService',
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
      this.userInfo.communityId = sessionStorage.getItem('communityId')
      this.userInfo.email = sessionStorage.getItem('email')
      let bodyFormData = new FormData()
      bodyFormData.set('id', this.userInfo.communityId)
      let url = '/user-server/api/user/getCommunityById'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        this.userInfo.community = response.data.community
        sessionStorage.setItem('communityAddress', response.data.address)
        sessionStorage.setItem('community', this.userInfo.community)
      })
    },
    loadShop () {
      let bodyFormData = new FormData()
      bodyFormData.set('type', '电脑维修')
      let url = '/user-server/api/merchant/findAllByType'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        this.shop = response.data
      })
    }
  },
  mounted () {
    this.loadData()
    this.loadShop()
  }
}
</script>

<style scoped>

</style>
