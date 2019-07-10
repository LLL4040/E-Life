<template>
  <div>
    <div align="center">
      <el-input v-model="search" size="medium" style="width: 300px" suffix-icon="el-icon-search" placeholder="输入关键字搜索"/>
    </div>
    <el-row :gutter="10" style="padding-top: 20px">
      <el-col :span="18">
        <el-card class="box-card">
          <div v-for="o in 4" :key="o" class="text item">
            {{'列表内容 ' + o }}
            <el-divider></el-divider>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>话题标签</span>
            <el-button style="float: right; padding: 3px 0; font-size: 16px;" type="text" @click="dialogFormVisible = true">我要发帖</el-button>
          </div>
          <el-row :gutter="10">
            <el-col :span="7">
              <el-tag>标签一</el-tag>
            </el-col>
            <el-col :span="7">
            <el-tag type="success">标签二</el-tag>
            </el-col>
              <el-col :span="7">
            <el-tag type="info">标签三</el-tag>
              </el-col>
                <el-col :span="7">
            <el-tag type="warning">标签四</el-tag>
                </el-col>
                  <el-col :span="7">
            <el-tag type="danger">标签五</el-tag>
                  </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <el-dialog title="发帖详情" :visible.sync="dialogFormVisible">
      <el-form :model="myPost">
        <el-form-item label="标题">
          <el-input v-model="myPost.title" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="内容">
          <el-input v-model="myPost.content" autocomplete="off" type="textarea" :autosize="{ minRows: 3, maxRows: 5}"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogFormVisible = false">发 布</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'Forum',
  data () {
    return {
      search: '',
      dialogFormVisible: false,
      myPost: {
        username: '',
        title: '',
        content: ''
      }
    }
  }
}
</script>

<style scoped>

</style>
