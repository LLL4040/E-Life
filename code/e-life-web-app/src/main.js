import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import 'jquery/dist/jquery.min.js'
import 'bootstrap/dist/js/bootstrap.min.js'
import BaiduMap from 'vue-baidu-map'

Vue.config.productionTip = false
Vue.prototype.$axios = axios
Vue.use(ElementUI)
Vue.use(BaiduMap, {
  ak: 'QREuS60uNlX1kjDDOn9HkplUMCdgkpkb'
})

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
