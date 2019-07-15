<template>
  <div>
    <div align="center">
      <el-input v-model="search" size="medium" style="width: 300px" suffix-icon="el-icon-search" placeholder="输入关键字搜索"/>
    </div>
    <el-row :gutter="10" style="padding-top: 20px">
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>商家团购</span>
          </div>
          <el-table :data="group.filter(data => !search || data.content.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
            <el-table-column type="expand">
              <template slot-scope="props">
                <el-form label-position="left" inline class="demo-table-expand">
                  <el-form-item label="开始时间">
                    <span>{{ props.row.start }}</span>
                  </el-form-item>
                  <el-form-item label="截止时间">
                    <span>{{ props.row.end }}</span>
                  </el-form-item>
                  <el-form-item label="详情">
                    <span>{{ props.row.content }}</span>
                  </el-form-item>
                </el-form>
              </template>
            </el-table-column>
            <el-table-column prop="title" label="标题" width="200px"></el-table-column>
            <el-table-column prop="num" label="人数上限"></el-table-column>
            <el-table-column label="操作" align="center">
              <template slot-scope="scope">
                <el-button size="mini" type="primary" plain @click="handleCheck(scope.row.mId)">查看商家</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>自发需求</span>
            <el-button style="float: right; padding: 3px 0" type="text" @click="dialogFormVisible2 = true">我要开团</el-button>
          </div>
          <el-table :data="demand.filter(data => !search || data.content.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
            <el-table-column type="expand">
              <template slot-scope="props">
                <el-form label-position="left" inline class="demo-table-expand">
                  <el-form-item label="开始时间">
                    <span>{{ props.row.start }}</span>
                  </el-form-item>
                  <el-form-item label="截止时间">
                    <span>{{ props.row.end }}</span>
                  </el-form-item>
                  <el-form-item label="详情">
                    <span>{{ props.row.content }}</span>
                  </el-form-item>
                </el-form>
              </template>
            </el-table-column>
            <el-table-column prop="title" label="标题" width="200px"></el-table-column>
            <el-table-column prop="num" label="团内人数"></el-table-column>
            <el-table-column label="操作" align="center">
              <template slot-scope="scope">
                <el-button v-if="scope.row.username !== userInfo.username" size="mini" type="primary" plain @click="handleA(scope.row)">加入</el-button>
                <el-button v-if="scope.row.username === userInfo.username" size="mini" type="danger" plain @click="handleD(scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>
    <el-dialog title="商家信息" :visible.sync="dialogFormVisible1">
      <el-form ref="form" :model="form" label-width="70px">
        <el-form-item label="商店名称">
          <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{ form.shop }}</span>
        </el-form-item>
        <el-form-item label="商店电话">
          <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{ form.merchantPhone }}</span>
        </el-form-item>
        <el-form-item label="商店类型">
          <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{ form.type }}</span>
        </el-form-item>
        <el-form-item label="商店描述">
          <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{ form.detail }}</span>
        </el-form-item>
        <el-form-item label="商店地址">
          <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{ form.address }}</span>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-dialog title="团购需求" :visible.sync="dialogFormVisible2">
      <el-form :model="commands">
        <el-form-item label="起始时间">
          <el-date-picker v-model="time" type="datetimerange" :picker-options="pickerOptions" value-format="yyyy-MM-dd HH:mm:ss"
            range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" align="right">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="开团标题">
          <el-input v-model="commands.title" ></el-input>
        </el-form-item>
        <el-form-item label="开团内容">
          <el-input v-model="commands.content" autocomplete="off" type="textarea" :autosize="{ minRows: 3, maxRows: 5}"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible2 = false">取 消</el-button>
        <el-button type="primary" @click="addDemand()">发 布</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'GroupBuy',
  data () {
    return {
      search: '',
      dialogFormVisible1: false,
      dialogFormVisible2: false,
      userInfo: {
        community: '',
        communityId: 0,
        username: '',
        email: '',
        phone: ''
      },
      time: [],
      group: [{
        mId: '1',
        id: '1',
        start: 'xxx',
        end: 'xxx',
        num: '100',
        title: '奶茶第二杯半价！速来！',
        content: '提醒奶茶小助手，快来和我一起成为一天八杯奶茶的小可爱吧~'
      }],
      demand: [{
        id: '1',
        start: 'xxx',
        end: 'xxx',
        num: '100',
        title: '奶茶第二杯半价！速来！',
        content: '提醒奶茶小助手，快来和我一起成为一天八杯奶茶的小可爱吧~'
      }],
      commands: {
        username: '',
        start: '',
        end: '',
        title: '',
        content: ''
      },
      form: {
        id: '1',
        shop: '星海网咖',
        merchantPhone: '11111',
        type: '休闲娱乐',
        detail: '嘻嘻嘻',
        address: 'xx,xx'
      },
      pickerOptions: {
        shortcuts: [{
          text: '未来一周',
          onClick (picker) {
            const start = new Date()
            const end = new Date()
            end.setTime(end.getTime() + 3600 * 1000 * 24 * 7)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '未来一个月',
          onClick (picker) {
            const start = new Date()
            const end = new Date()
            end.setTime(end.getTime() + 3600 * 1000 * 24 * 30)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '未来三个月',
          onClick (picker) {
            const start = new Date()
            const end = new Date()
            end.setTime(end.getTime() + 3600 * 1000 * 24 * 90)
            picker.$emit('pick', [start, end])
          }
        }]
      }
    }
  },
  methods: {
    loadData () {
      this.userInfo.username = sessionStorage.getItem('username')
      if (this.userInfo.username === '' || this.userInfo.username === null) {
        this.$router.push({ name: 'Login' })
      }
      this.userInfo.phone = sessionStorage.getItem('phone')
      this.userInfo.communityId = sessionStorage.getItem('communityId')
      this.userInfo.email = sessionStorage.getItem('email')
      let bodyFormData = new FormData()
      bodyFormData.set('id', this.userInfo.communityId)
      let url = '/user-server/api/user/getCommunityById'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        this.userInfo.community = response.data.community
        sessionStorage.setItem('community', this.userInfo.community)
      })
    },
    loadGroup () {
      let bodyFormData = new FormData()
      bodyFormData.set('communityId', this.userInfo.communityId)
      let url = '/group-server/api/discount/findDiscountByCommunityId'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        this.group = response.data
      })
    },
    loadDemand () {
      let bodyFormData = new FormData()
      bodyFormData.set('communityId', this.userInfo.communityId)
      let url = '/group-server/api/demand/getAllDemand'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        this.demand = response.data
      })
    },
    show () {
    },
    addDemand () {
      this.dialogFormVisible2 = false
      this.commands.start = this.time[0]
      this.commands.end = this.time[1]
      let bodyFormData = new FormData()
      bodyFormData.set('communityId', this.userInfo.communityId)
      bodyFormData.set('username', this.userInfo.username)
      bodyFormData.set('startTime', this.commands.start)
      bodyFormData.set('endTime', this.commands.end)
      bodyFormData.set('title', this.commands.title)
      bodyFormData.set('content', this.commands.content)
      let url = '/group-server/api/demand/addDemand'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.add === 1) {
          this.$alert('添加成功！')
          this.loadDemand()
        } else {
          this.$alert('添加失败！')
        }
      })
    },
    handleCheck (id) {
      this.dialogFormVisible1 = true
    },
    handleA (row) {
      let bodyFormData = new FormData()
      bodyFormData.set('id', row.id)
      bodyFormData.set('username', this.userInfo.username)
      let url = '/group-server/api/demand/participateDemand'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.participate === 1) {
          this.$alert('加入成功！')
          this.loadDemand()
        } else {
          this.$alert('加入失败！')
        }
      })
    },
    handleD (row) {
      let bodyFormData = new FormData()
      bodyFormData.set('id', row.id)
      let url = '/group-server/api/demand/deleteDemand'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.delete === 1) {
          this.$alert('删除成功！')
          this.loadDemand()
        } else {
          this.$alert('删除失败！')
        }
      })
    }
  },
  mounted () {
    this.loadData()
    this.loadGroup()
    this.loadDemand()
  }
}
</script>

<style scoped>

</style>
