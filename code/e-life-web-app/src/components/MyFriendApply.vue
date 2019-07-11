<template>
  <div>
    <div align="center">
      <el-input v-model="search" size="medium" style="width: 400px" suffix-icon="el-icon-search" placeholder="输入0或±1筛选状态-(1:已通过、-1:未通过、0:未处理)"/>
    </div>
    <el-row :gutter="10" style="padding-top: 20px">
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>我的申请</span>
          </div>
          <el-table :data="my.filter(data => !search || data.status === search)" style="width: 100%">
            <el-table-column type="expand">
              <template slot-scope="props">
                <el-form label-position="left" inline class="demo-table-expand">
                  <el-form-item label="留言">
                    <span>{{ props.row.content }}</span>
                  </el-form-item>
                </el-form>
              </template>
            </el-table-column>
            <el-table-column label="用户名" prop="username" align="center"></el-table-column>
            <el-table-column label="小区" prop="community" align="center"></el-table-column>
            <el-table-column label="状态" prop="status" align="center">
              <template slot-scope="scope">
                <el-button v-if="scope.row.status === '1'" type="success" plain size="small">已通过</el-button>
                <el-button v-if="scope.row.status === '0'" type="primary" plain size="small">未处理</el-button>
                <el-button v-if="scope.row.status === '-1'" type="danger" plain size="small">未通过</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>申请加我</span>
          </div>
          <el-table :data="others" style="width: 100%">
            <el-table-column type="expand">
              <template slot-scope="props">
                <el-form label-position="left" inline class="demo-table-expand">
                  <el-form-item label="留言">
                    <span>{{ props.row.content }}</span>
                  </el-form-item>
                </el-form>
              </template>
            </el-table-column>
            <el-table-column label="用户名" prop="username" align="center"></el-table-column>
            <el-table-column label="小区" prop="community" align="center"></el-table-column>
            <el-table-column label="操作" width="150px" align="center">
              <template slot-scope="scope">
                <el-button size="mini" type="primary" plain @click="handleA(scope.row)">同意</el-button>
                <el-button size="mini" type="danger" plain @click="handleR(scope.row)">拒绝</el-button>
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
  name: 'MyFriendApply',
  data () {
    return {
      search: '',
      my: [{
        username: '233',
        community: 'xxx',
        content: 'hhh',
        status: '0'
      }, {
        username: '233',
        community: 'xxx',
        content: 'hhh',
        status: '1'
      }, {
        username: '233',
        community: 'xxx',
        content: 'hhh',
        status: '-1'
      }],
      others: [{
        username: '233',
        community: 'xxx',
        content: 'hhh'
      }]
    }
  },
  mounted () {
  },
  methods: {
    handleA (row) {
    },
    handleD (row) {
    }
  }
}
</script>

<style>
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
</style>
