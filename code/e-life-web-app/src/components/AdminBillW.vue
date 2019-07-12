<template>
  <div>
    <div align="center">
      <el-input v-model="search" size="medium" style="width: 350px" suffix-icon="el-icon-search" placeholder="输入用户名关键字查找用户账单"/>
    </div>
    <el-row :gutter="10" style="padding-top: 20px">
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>停车费账单</span>
            <el-button style="float: right;" size="medium" type="primary" icon="el-icon-plus" circle @click="dialogFormVisible1 = true"></el-button>
          </div>
          <el-table :data="parkBill.filter(data => !search || data.username.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
            <el-table-column label="时间" prop="time" align="center"></el-table-column>
            <el-table-column label="金额" prop="amount" align="center"></el-table-column>
            <el-table-column label="用户名" prop="username" align="center"></el-table-column>
          </el-table>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>物业费账单</span>
            <el-button style="float: right;" size="medium" type="primary" icon="el-icon-plus" circle @click="dialogFormVisible2 = true"></el-button>
          </div>
          <el-table :data="propertyBill.filter(data => !search || data.username.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
            <el-table-column label="时间" prop="time" align="center"></el-table-column>
            <el-table-column label="金额" prop="amount" align="center"></el-table-column>
            <el-table-column label="用户名" prop="username" align="center"></el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>
    <el-dialog title="添加停车费账单" :visible.sync="dialogFormVisible1">
      <el-form :model="newT">
        <el-form-item label="截止时间">
          <el-date-picker v-model="newT.time" type="datetime" placeholder="选择日期时间"></el-date-picker>
        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="newT.username"></el-input>
        </el-form-item>
        <el-form-item label="金额">
          <el-input v-model="newT.amount"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="releaseA()">发 布</el-button>
      </div>
    </el-dialog>
    <el-dialog title="添加物业费账单" :visible.sync="dialogFormVisible2">
      <el-form :model="newW">
        <el-form-item label="截止时间">
          <el-date-picker v-model="newW.time" type="datetime" placeholder="选择日期时间"></el-date-picker>
        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="newW.username"></el-input>
        </el-form-item>
        <el-form-item label="金额">
          <el-input v-model="newW.amount"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible2 = false">取 消</el-button>
        <el-button type="primary" @click="releaseA()">发 布</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'AdminBillW',
  data () {
    return {
      search: '',
      dialogFormVisible1: false,
      dialogFormVisible2: false,
      parkBill: [{
        time: '1',
        amount: '1',
        username: '233'
      }],
      propertyBill: [{
        time: '1',
        amount: '1',
        username: '233'
      }],
      newT: {
        time: '',
        amount: '',
        username: ''
      },
      newW: {
        time: '',
        amount: '',
        username: ''
      }
    }
  },
  mounted () {

  },
  methods: {
  }
}
</script>

<style scoped>

</style>
