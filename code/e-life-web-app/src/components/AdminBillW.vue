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
          </div>
          <el-table :data="propertyBill.filter(data => !search || data.username.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
            <el-table-column label="时间" prop="time" align="center"></el-table-column>
            <el-table-column label="金额" prop="amount" align="center"></el-table-column>
            <el-table-column label="用户名" prop="username" align="center"></el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'AdminBillW',
  data () {
    return {
      search: '',
      parkBill: [{
        time: '1',
        amount: '1',
        username: '233'
      }],
      propertyBill: [{
        time: '1',
        amount: '1',
        username: '233'
      }]
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
