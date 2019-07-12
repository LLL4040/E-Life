<template>
  <div>
    <div align="center">
      <el-input v-model="search" size="medium" style="width: 350px" suffix-icon="el-icon-search" placeholder="输入用户名关键字筛选"/>
    </div>
    <div style="padding-top: 20px;">
      <el-card class="box-card">
        <el-table :data="requestData.filter(data => !search || data.username.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="left" inline class="demo-table-expand">
                <el-form-item label="维修人员姓名">
                  <span>{{ props.row.repairman }}</span>
                </el-form-item>
                <el-form-item label="维修人员联系方式">
                  <span>{{ props.row.rPhone }}</span>
                </el-form-item>
              </el-form>
            </template>
          </el-table-column>
          <el-table-column prop="time" label="时间"></el-table-column>
          <el-table-column prop="username" label="用户名"></el-table-column>
          <el-table-column prop="phone" label="联系方式"></el-table-column>
          <el-table-column prop="content" label="内容"></el-table-column>
          <el-table-column label="状态" prop="status" align="center">
            <template slot-scope="scope">
              <el-button v-if="scope.row.status === '1'" type="success" plain size="small">已解决</el-button>
              <el-button v-if="scope.row.status === '0'" type="primary" plain size="small">处理中</el-button>
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
  name: 'AdminRepairY',
  data () {
    return {
      search: '',
      requestData: [{
        time: '1',
        username: '233',
        phone: '1551',
        content: '啊啊啊木得电活不下去啊快点修！！',
        repairman: '电工小李',
        rPhone: '12345',
        status: '0'
      },
      {
        time: '2',
        username: '233',
        phone: '1551',
        content: '啊啊啊木得电活不下去啊快点修！！',
        repairman: '电工小李',
        rPhone: '12345',
        status: '1'
      }]
    }
  },
  methods: {
    handleDelete () {
    }
  }
}
</script>

<style scoped>

</style>
