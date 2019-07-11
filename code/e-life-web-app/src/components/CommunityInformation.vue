<template>
  <div>
    <div align="center" style="width: 800px; padding-left: 200px">
      <el-carousel indicator-position="outside" :interval="4000" height="300px">
        <el-carousel-item v-for="item in 3" :key="item">
          <el-image style="width: 500px; height: 300px" :src="require('../../public/img/alert.jpg')"></el-image>
          <h4 style="position: relative; bottom: 150px; left: 10px">暂时没有紧急通知哦</h4>
        </el-carousel-item>
      </el-carousel>
    </div>
    <el-row :gutter="10">
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>最新资讯</span>
          </div>
          <el-table :data="news" style="width: 100%">
            <el-table-column type="expand">
              <template slot-scope="props">
                <el-form label-position="left" inline class="demo-table-expand">
                  <el-form-item label="封面">
                    <img :src="props.row.photo" style="width: 80px; height: 80px">
                  </el-form-item>
                  <el-form-item label="详情">
                    <span>{{ props.row.content }}</span>
                  </el-form-item>
                </el-form>
              </template>
            </el-table-column>
            <el-table-column prop="time" label="时间" align="center"></el-table-column>
            <el-table-column prop="title" label="标题" align="center"></el-table-column>
          </el-table>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>活动安排</span>
          </div>
          <el-table :data="activity" style="width: 100%">
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
                <el-button v-if="scope.row.status === '1'" type="danger" plain size="small">已开始</el-button>
                <el-button v-if="scope.row.status === '0'" type="success" plain size="small">报名中</el-button>
              </template>
            </el-table-column>
            <el-table-column label="操作" align="center">
              <template slot-scope="scope">
                <el-button v-if="scope.row.status === '0'" size="mini" type="primary" plain @click="dialogFormVisible = true">申请加入</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>
    <el-dialog title="申请加入活动" :visible.sync="dialogFormVisible">
      <el-form :model="apply">
        <el-form-item label="留言">
          <el-input v-model="apply.content" autocomplete="off" type="textarea" :autosize="{ minRows: 2, maxRows: 3}"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleA()">提 交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'CommunityInformation',
  data () {
    return {
      dialogFormVisible: false,
      notice: [],
      news: [{
        time: 'xxx',
        title: 'xx',
        photo: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1562870568907&di=f976f7d2d4659c7ba947beba11ab09d0&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201505%2F19%2F20150519231117_wmYEU.thumb.700_0.jpeg',
        content: 'xxxx'
      }],
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
        title: '',
        username: '',
        content: ''
      }]
    }
  },
  mounted () {
  },
  methods: {
    handleA () {
      this.dialogFormVisible = false
    }
  }
}
</script>

<style scoped>
</style>
