<template>
  <div>
    <div style="padding-top: 20px;">
      <el-card style="padding-left: 20px">
        <el-table :data="messages" style="width: 100%">
          <el-table-column prop="time" label="时间" align="center"></el-table-column>
          <el-table-column prop="manager" label="发送人" align="center"></el-table-column>
          <el-table-column prop="content" label="内容" align="center"></el-table-column>
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
  name: 'MyMessage',
  data () {
    return {
      messages: [{
        time: '1',
        manager: '1',
        content: '快交钱！'
      }]
    }
  }
}
</script>

<style scoped>

</style>
