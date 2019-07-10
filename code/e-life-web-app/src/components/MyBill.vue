<template>
  <div>
    <div align="center">
      <el-input v-model="search" size="medium" style="width: 350px" suffix-icon="el-icon-search" placeholder="输入状态关键字搜索，如：未缴费、已缴费"/>
    </div>
    <el-row :gutter="10" style="padding-top: 20px">
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>停车费账单</span>
          </div>
          <el-table :data="parkBill.filter(data => !search || data.status.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
            <el-table-column label="时间" prop="time" align="center"></el-table-column>
            <el-table-column label="金额" prop="amount" align="center"></el-table-column>
            <el-table-column label="状态" prop="status" align="center"></el-table-column>
          </el-table>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>物业费账单</span>
          </div>
          <el-table :data="propertyBill.filter(data => !search || data.status.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
            <el-table-column label="时间" prop="time" align="center"></el-table-column>
            <el-table-column label="金额" prop="amount" align="center"></el-table-column>
            <el-table-column label="状态" prop="status" align="center"></el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'MyBill',
  data () {
    return {
      search: '',
      parkBill: [{
        time: '1',
        amount: '1',
        status: '未缴费'
      }],
      propertyBill: [{
        time: '1',
        amount: '1',
        status: '未缴费'
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
