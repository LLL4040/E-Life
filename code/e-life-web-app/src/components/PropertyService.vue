<template>
  <div>
    <div align="left">
      <el-button style="margin-top: -20px" size="medium" type="primary" plain icon="el-icon-refresh" circle @click="refresh()"></el-button>
    </div>
    <el-row :gutter="10" style="padding-top: 20px">
      <el-col :span="10" align="left">
        <el-card class="box-card" style="font-size: 16px;">
          上班时间：周一至周五下午三点到五点<br>
          联系电话：111-1111-1111<br>
          办公室地址：xxxxxxxxxx
        </el-card>
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span style="font-size: 16px;">添加请求</span>
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
            <span style="font-size: 16px;">我的请求&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <el-button-group>
              <el-button type="success" plain size="small" @click="search = 2">只看已解决</el-button>
              <el-button type="primary" plain size="small" @click="search = 1">只看处理中</el-button>
              <el-button type="danger" plain size="small" @click="search = 0">只看未处理</el-button>
            </el-button-group>
            </span>
          </div>
          <el-table :data="maintain.filter(data => typeof data.time !== 'undefined' && ((search === -1) || data.status === search))" style="width: 100%">
            <el-table-column type="expand">
              <template slot-scope="props">
                <el-form label-position="left" inline class="demo-table-expand">
                  <el-form-item label="维修人员姓名">
                    <span>{{ props.row.maintainname }}</span>
                  </el-form-item>
                  <el-form-item label="维修人员联系方式">
                    <span>{{ props.row.phone }}</span>
                  </el-form-item>
                </el-form>
              </template>
            </el-table-column>
            <el-table-column prop="time" label="时间"></el-table-column>
            <el-table-column prop="content" label="内容"></el-table-column>
            <el-table-column label="状态" prop="status" align="center">
              <template slot-scope="scope">
                <el-button v-if="scope.row.status === 2" type="success" plain size="small">已解决</el-button>
                <el-button v-if="scope.row.status === 1" type="primary" plain size="small">处理中</el-button>
                <el-button v-if="scope.row.status === 0" type="danger" plain size="small">未处理</el-button>
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
  name: 'PropertyService',
  data () {
    return {
      search: -1,
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
      maintain: [],
      userInfo: {
        community: '',
        communityId: 0,
        username: '',
        email: '',
        phone: ''
      }
    }
  },
  mounted () {
    this.refresh()
  },
  methods: {
    refresh () {
      this.loadData()
      this.loadMain()
      this.$forceUpdate()
    },
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
    loadMain () {
      let bodyFormData = new FormData()
      bodyFormData.set('username', this.userInfo.username)
      let url = '/lifeservice-server/api/maintain/userFindMaintain'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.length > 0 && response.data[0].login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          this.maintain = response.data.reverse()
        }
      })
    },
    submit () {
      let bodyFormData = new FormData()
      bodyFormData.set('content', this.form.content)
      bodyFormData.set('username', this.userInfo.username)
      bodyFormData.set('userphone', this.form.phone)
      let url = '/lifeservice-server/api/maintain/addMaintain'
      this.$axios({
        method: 'post',
        url: url,
        data: bodyFormData,
        config: { headers: { 'Content-type': 'multipart/form-data' } } }
      ).then(response => {
        if (response.data.login === 0) {
          this.$router.push({ name: 'Login' })
        } else {
          this.$alert('添加成功！')
          this.loadMain()
          this.$forceUpdate()
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
