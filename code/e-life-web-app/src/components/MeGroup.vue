<template>
  <div>
    <div align="left">
      <el-button style="margin-top: -20px" size="medium" type="primary" plain icon="el-icon-refresh" circle @click="refresh()"></el-button>
    </div>
    <div align="center">
      <el-input v-model="search" size="medium" style="width: 350px" suffix-icon="el-icon-search" placeholder="输入内容关键字筛选"/>
      <el-button style="float: left;" size="medium" type="primary" round @click="dialogFormVisible = true">添加团购</el-button>
    </div>
    <div style="padding-top: 20px;">
      <el-card class="box-card">
        <el-table :data="group.filter(data => !search || data.content.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="left" inline class="demo-table-expand">
                <el-form-item label="封面">
                  <button @click="showPhoto(props.row)" type="button" style="cursor: pointer; background-color: transparent; border: 0;">
                    <img :src="props.row.photo" style="width: 100%; height: 100%">
                  </button>
                </el-form-item>
                <el-form-item label="详情">
                  <span>{{ props.row.content }}</span>
                </el-form-item>
              </el-form>
            </template>
          </el-table-column>
          <el-table-column prop="start" label="开始时间"></el-table-column>
          <el-table-column prop="end" label="截止时间"></el-table-column>
          <el-table-column prop="num" label="人数上限"></el-table-column>
          <el-table-column prop="title" label="标题"></el-table-column>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button size="medium" type="danger" icon="el-icon-delete" circle @click="handleDelete(scope.row)"></el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
      <el-dialog title="团购需求" :visible.sync="dialogFormVisible">
        <el-form :model="commands">
          <el-form-item label="起始时间">
            <el-date-picker v-model="time" type="datetimerange" :picker-options="pickerOptions" value-format="yyyy-MM-dd HH:mm:ss"
                            range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" align="right">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="团购标题">
            <el-input v-model="commands.title" ></el-input>
          </el-form-item>
          <el-form-item label="团购最大人数">
            <el-input v-model="commands.num" ></el-input>
          </el-form-item>
          <el-form-item label="封面">
            <button class="upload-button" id="upImg" type="button" @click="upF()"></button>
            <input type='file' accept="image/*" id="file" ref="file" style="display: none"/>
          </el-form-item>
          <el-form-item label="团购内容">
            <el-input v-model="commands.content" autocomplete="off" type="textarea" :autosize="{ minRows: 3, maxRows: 5}"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="addDiscount()">发 布</el-button>
        </div>
      </el-dialog>
      <el-dialog :visible.sync="dialogFormVisible2">
        <div>
          <img :src="photo" width="100%">
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MeGroup',
  data () {
    return {
      search: '',
      photo: '',
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
      commands: {
        start: '',
        end: '',
        title: '',
        content: '',
        num: ''
      },
      dialogFormVisible: false,
      dialogFormVisible2: false,
      group: [],
      time: [],
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
  mounted () {
    this.refresh()
  },
  methods: {
    refresh () {
      this.loadData()
      this.loadGroup()
      this.$forceUpdate()
    },
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
    },
    loadGroup () {
      let bodyFormData = new FormData()
      bodyFormData.set('merchantId', parseInt(this.form.id))
      let url = '/group-server/api/discount/findDiscountByMerchantId'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.length > 0 && response.data[0].login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          this.group = response.data
        }
      })
    },
    addDiscount () {
      this.dialogFormVisible = false
      let imagFile = this.$refs.file.files[0]
      let bodyFormData = new FormData()
      bodyFormData.set('startTime', this.time[0])
      bodyFormData.set('endTime', this.time[1])
      bodyFormData.set('merchantId', parseInt(this.form.id))
      bodyFormData.set('number', this.commands.num)
      bodyFormData.set('content', this.commands.content)
      bodyFormData.set('status', 1)
      bodyFormData.set('communityId', this.form.communityId)
      bodyFormData.set('title', this.commands.title)
      bodyFormData.append('photo', imagFile)
      let url = '/group-server/api/discount/addDiscount'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        console.log(response.data)
        if (response.data.login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          if (response.data.add === 1) {
            this.$alert('添加成功！')
            this.loadGroup()
            this.dialogFormVisible = false
            this.$forceUpdate()
          } else {
            this.$alert('添加失败！')
          }
        }
      })
    },
    handleDelete (row) {
      let bodyFormData = new FormData()
      bodyFormData.set('id', row.id)
      let url = '/group-server/api/discount/deleteDiscount'
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
            this.loadGroup()
            this.$forceUpdate()
          } else {
            this.$alert('删除失败！')
          }
        }
      })
    },
    upF () {
      let element = document.getElementById('file')
      element.addEventListener('change', function (e) {
        document.getElementById('upImg').className = 'upload-button2'
      })
      element.click()
    },
    showPhoto (row) {
      let bodyFormData = new FormData()
      bodyFormData.set('path', row.path)
      let url = '/group-server/api/discount/photo'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          this.photo = response.data.photo
          this.dialogFormVisible2 = true
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
