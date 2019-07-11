<template>
  <div>
    <div align="center">
      <el-input v-model="search" size="medium" style="width: 300px" suffix-icon="el-icon-search" placeholder="输入用户名关键字搜索"/>
    </div>
    <div style="padding-top: 20px;">
      <el-card style="padding-left: 20px">
        <el-table :data="userData.filter(data => !search || data.username.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
          <el-table-column prop="username" label="姓名" align="center"></el-table-column>
          <el-table-column prop="community" label="小区" align="center"></el-table-column>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button size="mini" type="danger" icon="el-icon-plus" circle @click="handleAdd1(scope.row)"></el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </div>
    <el-dialog title="添加好友" :visible.sync="dialogFormVisible">
      <el-form :model="myApply">
        <el-form-item label="留言">
          <el-input v-model="myApply.content" autocomplete="off" type="textarea" :autosize="{ minRows: 1, maxRows: 3}"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleAdd2()">提 交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'MyFriendAdd',
  data () {
    return {
      search: '',
      dialogFormVisible: false,
      myApply: {
        myName: '',
        hisName: '',
        content: ''
      },
      userData: [{
        username: '233',
        community: 'xxx'
      }]
    }
  },
  methods: {
    handleAdd1 (row) {
      this.myApply.hisName = row.username
      this.dialogFormVisible = true
    },
    handleAdd2 () {
      this.dialogFormVisible = false
    }
  }
}
</script>

<style scoped>

</style>
