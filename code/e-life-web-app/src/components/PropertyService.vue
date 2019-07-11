<template>
  <div>
    <el-row :gutter="10" style="padding-top: 20px">
      <el-col :span="10" align="left">
        <el-card class="box-card">
          上班时间：<br>
          联系电话：<br>
          办公室地址：
        </el-card>
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>添加请求</span>
          </div>
          <el-form label-width="70px" :model="form">
            <el-form-item label="联系方式">
              <el-input v-model="form.phone"></el-input>
            </el-form-item>
            <el-form-item label="问题描述">
              <el-input v-model="form.content" type="textarea" :autosize="{ minRows: 5, maxRows: 10}"></el-input>
            </el-form-item>
            <el-form-item align="right">
              <el-button type="primary" @click="submit()">提 交</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
      <el-col :span="14">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>我的请求&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <el-input v-model="search" size="medium" style="width: 400px" suffix-icon="el-icon-search" placeholder="输入0或±1筛选状态-(1:已解决、-1:未处理、0:处理中)"/>
            </span>
          </div>
          <el-table :data="requestData.filter(data => !search || data.status.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
            <el-table-column prop="time" label="时间"></el-table-column>
            <el-table-column prop="content" label="内容"></el-table-column>
            <el-table-column label="状态" prop="status" align="center">
              <template slot-scope="scope">
                <el-button v-if="scope.row.status === '1'" type="success" plain size="small">已解决</el-button>
                <el-button v-if="scope.row.status === '0'" type="primary" plain size="small">处理中</el-button>
                <el-button v-if="scope.row.status === '-1'" type="danger" plain size="small">未处理</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'PropertyService',
  data () {
    return {
      search: '',
      info: {
        time: '',
        phone: '',
        address: ''
      },
      form: {
        username: '',
        phone: '',
        content: ''
      },
      requestData: [{
        time: '1',
        content: '啊啊啊木得电活不下去啊快点修！！',
        status: '-1'
      }]
    }
  }
}
</script>

<style scoped>

</style>
