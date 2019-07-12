<template>
  <div>
    <div align="center">
      <el-input v-model="search" size="medium" style="width: 300px" suffix-icon="el-icon-search" placeholder="输入内容关键字搜索"/>
    </div>
    <el-row :gutter="10" style="padding-top: 20px">
      <el-col :span="12">
      <el-card style="padding-left: 20px">
        <div slot="header" class="clearfix">
          <span>紧急通知</span>
          <el-button style="float: right;" size="medium" type="primary" icon="el-icon-plus" circle @click="dialogFormVisible1 = true"></el-button>
        </div>
        <el-table :data="urgent.filter(data => !search || data.content.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="left" inline class="demo-table-expand">
                <el-form-item label="详情">
                  <span>{{ props.row.content }}</span>
                </el-form-item>
              </el-form>
            </template>
          </el-table-column>
          <el-table-column prop="time" label="时间" align="center"></el-table-column>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button size="medium" type="danger" icon="el-icon-delete" circle @click="handleDelete(scope.$index, scope.row)"></el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
      </el-col>
      <el-col :span="12">
        <el-card style="padding-left: 20px">
          <div slot="header" class="clearfix">
            <span>最新资讯</span>
            <el-button style="float: right;" size="medium" type="primary" icon="el-icon-plus" circle @click="dialogFormVisible2 = true"></el-button>
          </div>
          <el-table :data="news.filter(data => !search || data.content.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
            <el-table-column type="expand">
              <template slot-scope="props">
                <el-form label-position="left" inline class="demo-table-expand">
                  <el-form-item label="封面">
                    <img :src="props.row.photo" style="width: 80px; height: 80px">
                  </el-form-item>
                  <el-form-item label="时间">
                    <span>{{ props.row.time }}</span>
                  </el-form-item>
                  <el-form-item label="详情">
                    <span>{{ props.row.content }}</span>
                  </el-form-item>
                </el-form>
              </template>
            </el-table-column>
            <el-table-column prop="title" label="标题" align="center"></el-table-column>
            <el-table-column label="操作" align="center">
              <template slot-scope="scope">
                <el-button size="medium" type="danger" icon="el-icon-delete" circle @click="handleDelete(scope.$index, scope.row)"></el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>
    <el-dialog title="发布紧急通知" :visible.sync="dialogFormVisible1">
      <el-form :model="newUrgent">
        <el-form-item label="通知内容">
          <el-input v-model="newUrgent.content" autocomplete="off" type="textarea" :autosize="{ minRows: 3, maxRows: 5}"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="releaseU()">发 布</el-button>
      </div>
    </el-dialog>
    <el-dialog title="发布最新资讯" :visible.sync="dialogFormVisible2">
      <el-form :model="newNews">
        <el-form-item label="资讯标题">
          <el-input v-model="newNews.title"></el-input>
        </el-form-item>
        <el-form-item label="封面">
          <button class="upload-button" id="upImg" type="button" @click="upF()"></button>
          <input type='file' accept="image/*" id="file" ref="file" style="display: none"/>
        </el-form-item>
        <el-form-item label="资讯内容">
          <el-input v-model="newNews.content" autocomplete="off" type="textarea" :autosize="{ minRows: 3, maxRows: 5}"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible2 = false">取 消</el-button>
        <el-button type="primary" @click="releaseN()">发 布</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'AdminSendNotice',
  data () {
    return {
      search: '',
      dialogFormVisible1: false,
      dialogFormVisible2: false,
      urgent: [{
        time: 'xxx',
        content: 'xxxx'
      }],
      news: [{
        time: 'xxx',
        title: 'xx',
        photo: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1562870568907&di=f976f7d2d4659c7ba947beba11ab09d0&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201505%2F19%2F20150519231117_wmYEU.thumb.700_0.jpeg',
        content: 'xxxx'
      }],
      newUrgent: {
        content: '',
        manager: ''
      },
      newNews: {
        title: '',
        photo: '',
        content: '',
        manager: ''
      }
    }
  },
  mounted () {
  },
  methods: {
    releaseU () {
      this.dialogFormVisible1 = false
    },
    releaseN () {
      this.dialogFormVisible2 = false
    },
    handleDelete () {
    },
    upF () {
      let element = document.getElementById('file')
      element.addEventListener('change', function (e) {
        document.getElementById('upImg').className = 'upload-button2'
      })
      element.click()
    }
  }
}
</script>

<style>
  .upload-button {
    width: 120px;
    height: 120px;
    line-height: 120px;
    border: 1px solid #99a9bf;
    display: inline-block;
    background-color: #f6f6f6;
    background-image:url('../../public/img/upload.jpg');
    background-repeat:no-repeat;
    background-size:50% 50%;
    background-position: 30px 30px;
  }
  .upload-button2 {
    width: 120px;
    height: 120px;
    line-height: 120px;
    border: 1px solid #99a9bf;
    display: inline-block;
    background-color: #f6f6f6;
    background-image:url('../../public/img/true.jpg');
    background-repeat:no-repeat;
    background-size:50% 50%;
    background-position: 30px 30px;
  }
</style>
