<template>
  <div>
    <div v-for="o in 4" :key="o" class="text item">
      <el-card class="box-card">
        {{'列表内容 ' + o }}
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MarketService'
}
</script>

<style scoped>

</style>
