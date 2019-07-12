<template>
  <div>
    <div align="center">
      <el-input v-model="search" size="medium" style="width: 350px" suffix-icon="el-icon-search" placeholder="输入内容关键字筛选"/>
    </div>
    <div style="padding-top: 20px;">
      <el-card class="box-card">
        <el-table :data="demand.filter(data => !search || data.content.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="left" inline class="demo-table-expand">
                <el-form-item label="详情">
                  <span>{{ props.row.content }}</span>
                </el-form-item>
              </el-form>
            </template>
          </el-table-column>
          <el-table-column prop="start" label="开始时间"></el-table-column>
          <el-table-column prop="end" label="截止时间"></el-table-column>
          <el-table-column prop="num" label="团内人数"></el-table-column>
          <el-table-column prop="title" label="标题"></el-table-column>
        </el-table>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MeDemand',
  data () {
    return {
      search: '',
      demand: [{
        id: '1',
        start: 'xxx',
        end: 'xxx',
        num: '100',
        title: '奶茶第二杯半价！速来！',
        content: '提醒奶茶小助手，快来和我一起成为一天八杯奶茶的小可爱吧~'
      }]
    }
  },
  methods: {
  }
}
</script>

<style scoped>

</style>
