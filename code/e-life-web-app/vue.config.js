const path = require('path')
const webpack = require('webpack')

module.exports = {
  // 基本路径
  publicPath: '/',
  // 输出文件目录
  outputDir: 'static',
  // eslint-loader 是否在保存的时候检查
  lintOnSave: true,
  // webpack配置
  // see https://github.com/vuejs/vue-cli/blob/dev/docs/webpack.md
  chainWebpack: (config) => {
    config.plugin('provide').use(webpack.ProvidePlugin, [{
      $: 'jquery',
      jquery: 'jquery',
      jQuery: 'jquery',
      'window.jQuery': 'jquery',
      Popper: ['popper.js', 'default'] // 在jquery的配置下添加多这一行配置
    }])
  },
  configureWebpack: (config) => {
    if (process.env.NODE_ENV === 'production') {
      // 为生产环境修改配置...
      config.mode = 'production'
    } else {
      // 为开发环境修改配置...
      config.mode = 'development'
    }
    Object.assign(config, {
      // 开发生产共同配置
      resolve: {
        alias: {
          '@': path.resolve(__dirname, './src'),
          '@c': path.resolve(__dirname, './src/components')
          // 'jquery-1.9.1': path.resolve(__dirname, './src/assets/js/jquery-1.9.1.min.js'),
          // 'slick': path.resolve(__dirname, './src/assets/slick/slick.min.js'),
          // 'jquery.magnific-popup': path.resolve(__dirname, './src/assets/magnific-popup/jquery.magnific-popup.min.js'),
          // 'jquery.singlePageNav': path.resolve(__dirname, './src/assets/js/jquery.singlePageNav.min.js'),
          // 'bootstrap': path.resolve(__dirname, './src/assets/js/bootstrap.min.js')
        }
      }
    })
  },
  // 生产环境是否生成 sourceMap 文件
  productionSourceMap: true,
  // css相关配置
  css: {
    // 是否使用css分离插件 ExtractTextPlugin
    extract: true,
    // 开启 CSS source maps?
    sourceMap: false,
    // css预设器配置项
    loaderOptions: {},
    // 启用 CSS modules for all css / pre-processor files.
    modules: false
  },

  // use thread-loader for babel & TS in production build
  // enabled by default if the machine has more than 1 cores
  parallel: require('os').cpus().length > 1,
  // PWA 插件相关配置
  // see https://github.com/vuejs/vue-cli/tree/dev/packages/%40vue/cli-plugin-pwa
  pwa: {},
  // webpack-dev-server 相关配置
  devServer: {
    disableHostCheck: true,
    host: '0.0.0.0',
    port: 8081,
    https: false,
    open: false,
    hotOnly: false,
    proxy: {
      '': {
        target: 'http://localhost:9010/',
        changeOrigin: true,
        ws: true,
        pathRewrite: {
          '^/': ''
        }
      }
    }
  },
  // 第三方插件配置
  pluginOptions: {
    // ...
  }
}
