<template>
  <div>
    <div align="center">
      <el-input v-model="search" size="medium" style="width: 350px" suffix-icon="el-icon-search" placeholder="输入用户名关键字筛选"/>
    </div>
    <div style="padding-top: 20px;">
      <el-card class="box-card">
        <el-table :data="requestData.filter(data => !search || data.username.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
          <el-table-column prop="time" label="时间"></el-table-column>
          <el-table-column prop="username" label="用户名"></el-table-column>
          <el-table-column prop="phone" label="联系方式"></el-table-column>
          <el-table-column prop="content" label="内容"></el-table-column>
          <el-table-column label="操作" width="150px" align="center">
            <template>
              <el-button size="mini" type="primary" plain @click="dialogFormVisible = true">处理</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </div>
    <el-dialog title="发起活动" :visible.sync="dialogFormVisible">
      <el-form :model="handleR">
        <el-form-item label="修理人员姓名">
          <el-input v-model="handleR.repairman"></el-input>
        </el-form-item>
        <el-form-item label="修理人员手机号">
          <el-input v-model="handleR.phone"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleIt()">提 交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'AdminRepairW',
  data () {
    return {
      search: '',
      dialogFormVisible: false,
      requestData: [{
        time: '1',
        username: '233',
        phone: '1551',
        content: '啊啊啊木得电活不下去啊快点修！！'
      }],
      handleR: {
        repairman: '',
        phone: ''
      }
    }
  },
  methods: {
    handleIt () {
      this.dialogFormVisible = false
    }
  }
}
</script>

<style scoped>

</style>
