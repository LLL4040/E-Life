<template>
  <div>
    <div align="center">
      <el-input v-model="search" size="medium" style="width: 350px" suffix-icon="el-icon-search" placeholder="输入内容关键字筛选"/>
      <el-button style="float: left;" size="medium" type="primary" round @click="dialogFormVisible = true">添加优惠</el-button>
    </div>
    <div style="padding-top: 20px;">
      <el-card class="box-card">
        <el-table :data="bargains.filter(data => !search || data.content.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
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
          <el-table-column prop="title" label="标题"></el-table-column>
          <el-table-column prop="goods" label="商品"></el-table-column>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button size="medium" type="danger" icon="el-icon-delete" circle @click="handleDelete(scope.row)"></el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
      <el-dialog title="优惠商品" :visible.sync="dialogFormVisible">
        <el-form :model="commands">
          <el-form-item label="起始时间">
            <el-date-picker v-model="time" type="datetimerange" :picker-options="pickerOptions" value-format="yyyy-MM-dd HH:mm:ss"
                            range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" align="right">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="优惠标题">
            <el-input v-model="commands.title" ></el-input>
          </el-form-item>
          <el-form-item label="优惠商品">
            <el-input v-model="commands.goods" ></el-input>
          </el-form-item>
          <el-form-item label="优惠内容">
            <el-input v-model="commands.content" autocomplete="off" type="textarea" :autosize="{ minRows: 3, maxRows: 5}"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="addBargain()">发 布</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MeBargain',
  data () {
    return {
      search: '',
      form: {
        id: '',
        username: '',
        email: '',
        phone: '',
        community: '',
        communityId: '',
        shop: '',
        merchantPhone: '',
        type: '',
        detail: '',
        address: ''
      },
      bargains: [],
      dialogFormVisible: false,
      time: [],
      commands: {
        start: '',
        end: '',
        title: '',
        goods: '',
        content: ''
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
      this.form.username = sessionStorage.getItem('username')
      if (this.form.username === '' || this.form.username === null) {
        this.$router.push({ name: 'Login' })
      }
      this.form.id = sessionStorage.getItem('id')
      this.form.email = sessionStorage.getItem('email')
      this.form.phone = sessionStorage.getItem('phone')
      this.form.community = sessionStorage.getItem('community')
      this.form.communityId = sessionStorage.getItem('communityId')
      this.form.shop = sessionStorage.getItem('name')
      this.form.merchantPhone = sessionStorage.getItem('merchantPhone')
      this.form.type = sessionStorage.getItem('type')
      this.form.detail = sessionStorage.getItem('detail')
      this.form.address = sessionStorage.getItem('address')
      this.loadBargain()
    },
    loadBargain () {
      let bodyFormData = new FormData()
      bodyFormData.set('id', this.form.id)
      let url = '/group-server/api/bargain/getBargainByMerchantId'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.length > 0 && response.data[0].login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          this.bargains = response.data
        }
      })
    },
    addBargain () {
      let bodyFormData = new FormData()
      bodyFormData.set('startTime', this.time[0])
      bodyFormData.set('endTime', this.time[1])
      bodyFormData.set('merchantId', this.form.id)
      bodyFormData.set('goods', this.commands.goods)
      bodyFormData.set('content', this.commands.content)
      bodyFormData.set('status', 1)
      bodyFormData.set('title', this.commands.title)
      let url = '/group-server/api/bargain/addBargain'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          if (response.data.add === 1) {
            this.$alert('添加成功！')
            this.loadBargain()
            this.dialogFormVisible = false
          } else {
            this.$alert('添加失败！')
          }
        }
      })
    },
    handleDelete (row) {
      let bodyFormData = new FormData()
      bodyFormData.set('id', row.id)
      let url = '/group-server/api/bargain/deleteBargain'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          if (response.data.delete === 1) {
            this.$alert('删除成功！')
            this.loadBargain()
          } else {
            this.$alert('删除失败！')
          }
        }
      })
    }
  },
  mounted () {
    this.loadData()
  }
}
</script>

<style scoped>

</style>
