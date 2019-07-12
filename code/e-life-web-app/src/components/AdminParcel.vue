<template>
  <div>
    <div align="center">
      <el-input v-model="search" size="medium" style="width: 400px" suffix-icon="el-icon-search" placeholder="输入0或1筛选包裹状态-(0表示未取/1表示已取)"/>
    </div>
    <div style="padding-top: 20px;">
      <el-card style="padding-left: 20px">
        <div slot="header" class="clearfix" style="padding-right: 20px;">
          <span>已发送邮包通知列表</span>
          <el-button style="float: right;" size="medium" type="primary" icon="el-icon-plus" circle @click="dialogFormVisible = true"></el-button>
        </div>
        <el-table :data="messages.filter(data => !search || data.status === search)" style="width: 100%">
          <el-table-column prop="time" label="时间" align="center"></el-table-column>
          <el-table-column prop="manager" label="代签人" align="center"></el-table-column>
          <el-table-column prop="status" label="状态" align="center">
            <template slot-scope="scope">
              <el-button v-if="scope.row.status === '1'" type="success" plain size="small">已取</el-button>
              <el-button v-if="scope.row.status === '0'" type="primary" plain size="small">待取</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </div>
    <el-dialog title="发送通知" :visible.sync="dialogFormVisible">
      <el-form :model="sendM">
        <el-form-item label="接收人用户名">
          <el-input v-model="sendM.user"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="sendM()">发 送</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'AdminParcel',
  data () {
    return {
      search: '',
      dialogFormVisible: false,
      messages: [{
        time: 'yyyy-mm-dd',
        manager: '门口王大爷',
        status: '0'
      }],
      sendM: {
        manager: '',
        user: ''
      }
    }
  }
}
</script>

<style scoped>

</style>
