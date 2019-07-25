<template>
  <div>
    <div align="center">
      <el-input v-model="search" size="medium" style="width: 350px" suffix-icon="el-icon-search" placeholder="输入标题关键字筛选"/>
    </div>
    <div style="padding-top: 20px;">
      <el-card class="box-card">
        <el-table :data="demand.filter(data => !search || data.title.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="left" inline class="demo-table-expand">
                <el-form-item label="详情">
                  <span>{{ props.row.content }}</span>
                </el-form-item>
              </el-form>
            </template>
          </el-table-column>
          <el-table-column prop="title" label="标题"></el-table-column>
          <el-table-column prop="start" label="开始时间"></el-table-column>
          <el-table-column prop="end" label="截止时间"></el-table-column>
          <el-table-column prop="num" label="团内人数"></el-table-column>
        </el-table>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MeDemand',
  data () {
    return {
      search: '',
      form: {
        id: '',
        username: '',
        email: '',
        phone: '',
        community: '',
        communityId: '',
        shop: '',
        merchantPhone: '',
        type: '',
        detail: '',
        address: ''
      },
      demand: []
    }
  },
  methods: {
    loadData () {
      this.form.username = sessionStorage.getItem('username')
      if (this.form.username === '' || this.form.username === null) {
        this.$router.push({ name: 'Login' })
      }
      this.form.id = sessionStorage.getItem('id')
      this.form.email = sessionStorage.getItem('email')
      this.form.phone = sessionStorage.getItem('phone')
      this.form.community = sessionStorage.getItem('community')
      this.form.communityId = sessionStorage.getItem('communityId')
      this.form.shop = sessionStorage.getItem('name')
      this.form.merchantPhone = sessionStorage.getItem('merchantPhone')
      this.form.type = sessionStorage.getItem('type')
      this.form.detail = sessionStorage.getItem('detail')
      this.form.address = sessionStorage.getItem('address')
    },
    loadDemand () {
      let bodyFormData = new FormData()
      bodyFormData.set('communityId', this.form.communityId)
      let url = '/group-server/api/demand/getAllDemand'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.length > 0 && response.data[0].login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          this.demand = response.data
        }
      })
    }
  },
  mounted () {
    this.loadData()
    this.loadDemand()
  }
}
</script>

<style scoped>

</style>
