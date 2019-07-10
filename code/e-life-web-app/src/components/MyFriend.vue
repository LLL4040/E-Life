<template>
  <div>
    <div align="center">
      <el-input v-model="search" size="medium" style="width: 300px" suffix-icon="el-icon-search" placeholder="输入用户名关键字搜索"/>
    </div>
    <div style="padding-top: 20px;">
      <el-card style="padding-left: 20px">
        <el-table :data="userData.filter(data => !search || data.username.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
          <el-table-column prop="username" label="姓名" align="center"></el-table-column>
          <el-table-column prop="phone" label="手机号" align="center"></el-table-column>
          <el-table-column prop="email" label="邮箱" align="center"></el-table-column>
          <el-table-column prop="community" label="小区" align="center"></el-table-column>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button size="medium" type="danger" icon="el-icon-delete" circle @click="handleDelete(scope.$index, scope.row)"></el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MyFriend',
  data () {
    return {
      search: '',
      userData: [{
        username: '233',
        phone: '120',
        email: '121',
        community: 'xxx'
      }]
    }
  },
  mounted () {
    this.getFriend()
  },
  methods: {
    getFriend () {
    },
    handleDelete () {
    }
  }
}
</script>

<style scoped>

</style>
