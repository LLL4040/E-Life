<template>
  <div>
    <div align="center">
      <el-input v-model="search" size="medium" style="width: 300px" suffix-icon="el-icon-search" placeholder="输入活动名称关键字进行搜索"/>
    </div>
    <el-row :gutter="10" style="padding-top: 20px">
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>活动列表</span>
            <el-button style="float: right;" size="medium" type="primary" icon="el-icon-plus" circle @click="dialogFormVisible = true"></el-button>
          </div>
          <el-table :data="activity.filter(data => !search || data.title.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
            <el-table-column type="expand">
              <template slot-scope="props">
                <el-form label-position="left" inline class="demo-table-expand">
                  <el-form-item label="封面">
                    <img :src="props.row.photo" style="width: 80px; height: 80px">
                  </el-form-item>
                  <el-form-item label="开始时间">
                    <span>{{ props.row.start }}</span>
                  </el-form-item>
                  <el-form-item label="结束时间">
                    <span>{{ props.row.end }}</span>
                  </el-form-item>
                  <el-form-item label="详情">
                    <span>{{ props.row.content }}</span>
                  </el-form-item>
                </el-form>
              </template>
            </el-table-column>
            <el-table-column label="标题" prop="title" align="center"></el-table-column>
            <el-table-column label="状态" align="center">
              <template slot-scope="scope">
                <el-button v-if="scope.row.status === '1'" type="danger" plain size="small">已结束</el-button>
                <el-button v-if="scope.row.status === '0'" type="success" plain size="small">报名中</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>申请列表</span>
          </div>
          <el-table :data="apply.filter(data => !search || data.title.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
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
            <el-table-column label="活动名" prop="title" align="center"></el-table-column>
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
    <el-dialog title="发起活动" :visible.sync="dialogFormVisible">
      <el-form :model="newA">
        <el-form-item label="活动标题">
          <el-input v-model="newA.title"></el-input>
        </el-form-item>
        <el-form-item label="起始时间">
          <el-date-picker v-model="time" type="datetimerange" :picker-options="pickerOptions"
                          range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" align="right">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="封面">
          <button class="upload-button" id="upImg" type="button" @click="upF()"></button>
          <input type='file' accept="image/*" id="file" ref="file" style="display: none"/>
        </el-form-item>
        <el-form-item label="详情">
          <el-input v-model="newA.content" autocomplete="off" type="textarea" :autosize="{ minRows: 3, maxRows: 5}"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="releaseA()">发 布</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'AdminActivity',
  data () {
    return {
      search: '',
      dialogFormVisible: false,
      activity: [{
        start: '2019-07-09',
        end: '2019-07-09',
        title: '一起晒太阳',
        photo: 'https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1562861072&di=a372079e04ab3835e2a86d4c75cf976e&src=http://b-ssl.duitang.com/uploads/item/201706/24/20170624232309_YzWdr.jpeg',
        content: '23333',
        status: '1'
      },
      {
        start: '2019-07-12',
        end: '2019-07-20',
        title: '一起摘橘子',
        photo: 'https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1562861123&di=b646c70c0d224f44aad7bdb55e620e45&src=http://img5.duitang.com/uploads/item/201608/19/20160819110610_APQUY.thumb.700_0.jpeg',
        content: '23333',
        status: '0'
      }],
      apply: [{
        title: '一起摘橘子',
        username: '233',
        content: '我超喜欢摘橘子的！！！选我选我！！！'
      }],
      time: [],
      newA: {
        start: '',
        end: '',
        title: '',
        photo: '',
        content: '',
        status: '0'
      }
    }
  },
  methods: {
    handleA () {
    },
    handleR () {
    },
    upF () {
      let element = document.getElementById('file')
      element.addEventListener('change', function (e) {
        document.getElementById('upImg').className = 'upload-button2'
      })
      element.click()
    },
    releaseA () {
      this.dialogFormVisible = false
    }
  }
}
</script>

<style scoped>

</style>
