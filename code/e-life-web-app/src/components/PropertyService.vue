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
            <el-input v-model="search" size="medium" style="width: 300px" suffix-icon="el-icon-search" placeholder="输入状态关键字搜索"/>
            </span>
          </div>
          <el-table :data="requestData.filter(data => !search || data.status.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
            <el-table-column prop="time" label="时间"></el-table-column>
            <el-table-column prop="content" label="内容"></el-table-column>
            <el-table-column prop="status" label="状态"></el-table-column>
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
        content: '1',
        status: '未处理'
      }]
    }
  }
}
</script>

<style scoped>

</style>
