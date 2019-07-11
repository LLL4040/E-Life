<template>
  <div>
    <div align="center">
      <el-input v-model="search" size="medium" style="width: 400px" suffix-icon="el-icon-search" placeholder="输入0或1筛选包裹状态-(0表示未取/1表示已取)"/>
    </div>
    <div style="padding-top: 20px;">
      <el-card style="padding-left: 20px">
        <el-table :data="messages.filter(data => !search || data.status === search)" style="width: 100%">
          <el-table-column prop="time" label="时间" align="center"></el-table-column>
          <el-table-column prop="manager" label="代签人" align="center"></el-table-column>
          <el-table-column prop="status" label="状态" align="center">
            <template slot-scope="scope">
              <el-button v-if="scope.row.status === '1'" type="success" plain size="small">已取</el-button>
              <el-button v-if="scope.row.status === '0'" type="primary" plain size="small">待取</el-button>
            </template>
          </el-table-column>
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
  name: 'MyParcel',
  data () {
    return {
      search: '',
      messages: [{
        time: 'yyyy-mm-dd',
        manager: '门口王大爷',
        status: '0'
      }]
    }
  }
}
</script>

<style scoped>

</style>
