<template>
  <body>
  <!-- Hero section -->
  <section id="hero" class="text-white tm-font-big tm-parallax">
    <!-- Navigation -->
    <nav class="navbar navbar-expand-md tm-navbar" id="tmNav">
      <div class="container">
        <div class="tm-next">
          <img src="../../public/img/logo.gif" width="60px">
          <a href="#" class="navbar-brand">E-LIFE</a>
        </div>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <i class="fas fa-bars navbar-toggler-icon"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item">
              <router-link router-link :to="{name:'Login'}" class="nav-link tm-nav-link" style="font-size: 20px;">登录</router-link>
            </li>
            <li class="nav-item">
              <router-link router-link :to="{name:'Register'}" class="nav-link tm-nav-link" style="font-size: 20px;">注册</router-link>
            </li>
            <li class="nav-item">
              <a class="nav-link tm-nav-link" href="#contact" style="font-size: 20px;">联系我们</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <div class="text-center tm-hero-text-container">
      <div class="tm-hero-text-container-inner">
        <h2 class="tm-hero-title">E-LIFE</h2>
        <p class="tm-hero-subtitle" style="font-size: 24px;">
          欢迎来到智能生活小区平台！
          <br />
        </p>
      </div>
    </div>

    <div class="tm-next tm-intro-next">
      <a href="#introduction" class="text-center tm-down-arrow-link">
        <i class="fas fa-3x fa-caret-down tm-down-arrow"></i>
      </a>
    </div>
  </section>

  <section id="introduction" class="tm-section-pad-top">
    <div class="container">
      <div class="row">
        <div class="col-lg-6">
          <img src="../../public/img/intro.gif" alt="Image" class="img-fluid tm-intro-img" />
        </div>
        <div class="col-lg-6">
          <div class="tm-intro-text-container">
            <h2 class="tm-text-primary mb-4 tm-section-title" style="font-size: 24px;">在这里你可以做什么？</h2>
            <el-tabs type="border-card" class="mb-4 tm-intro-text">
              <el-tab-pane label="小区居民">
                <span style="line-height: 50px; font-size: 20px;">如果你是<span style="font-family: 黑体; font-size: 20px;color: #3ba0dd;">小区居民</span>，你可以<br></span>
                <li style="line-height: 45px; font-size: 18px;">享受小区服务和特有的优惠活动</li>
                <li style="line-height: 45px; font-size: 18px;">第一时间获取最新资讯和紧急通知</li>
                <li style="line-height: 45px; font-size: 18px;">在论坛上友好互动，增进邻里关系</li>
              </el-tab-pane>
              <el-tab-pane label="物业人员">
                <span style="line-height: 50px; font-size: 20px;">如果你是<span style="font-size: 20px;color: #3ba0dd;">物业管理人员</span>，你可以<br></span>
                <li style="line-height: 45px; font-size: 18px;">更方便的发布通知和资讯</li>
                <li style="line-height: 45px; font-size: 18px;">通知和收取管理费用</li>
                <li style="line-height: 45px; font-size: 18px;">安排小区活动丰富居民生活</li>
              </el-tab-pane>
              <el-tab-pane label="周边商家">
                <span style="line-height: 50px; font-size: 20px;">如果你是<span style="font-size: 20px;color: #3ba0dd;">商家、快递人员、超市人员</span>，你可以<br></span>
                <li style="line-height: 45px; font-size: 18px;">更方便的发布优惠信息</li>
                <li style="line-height: 45px; font-size: 18px;">查看小区居民需求来调整优惠策略</li>
                <li style="line-height: 45px; font-size: 18px;">收件派件更加快捷</li>
              </el-tab-pane>
            </el-tabs>
            <span style="line-height: 16px;">&nbsp;</span>
            <div class="tm-next" align="center">
              <router-link router-link :to="{name:'Login'}" class="tm-intro-text tm-btn-primary" style="font-size: 18px;">登录</router-link>
              <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
              <router-link router-link :to="{name:'Register'}" class="tm-intro-text tm-btn-primary" style="font-size: 18px;">注册</router-link>
            </div>
          </div>
        </div>
      </div>

      <div class="row tm-section-pad-top">
        <div class="col-lg-4">
          <i class="fas fa-3x fa-address-book text-center tm-icon" style="float:left"></i>
          <h3 class="text-center tm-text-primary mb-4" style="padding-top:12px; float:left; font-size: 24px;">&nbsp;&nbsp;好友管理</h3>
          <div style="clear:both"></div>
          <p style="line-height: 45px;">
            用户可以注册登录智能生活小区平台，添加和管理好友
          </p>
        </div>
        <div class="col-lg-4 mt-5 mt-lg-0">
          <i class="fas fa-3x fa-bullhorn text-center tm-icon" style="float:left"></i>
          <h3 class="text-center tm-text-primary mb-4" style="padding-top:12px; float:left; font-size: 24px;">&nbsp;&nbsp;小区资讯</h3>
          <div style="clear:both"></div>
          <p style="line-height: 45px;">
            在这里可以看到小区的公告、最新资讯、紧急通知
          </p>
        </div>
        <div class="col-lg-4 mt-5 mt-lg-0">
          <i class="fas fa-3x fa-bell text-center tm-icon" style="float:left"></i>
          <h3 class="text-center tm-text-primary mb-4" style="padding-top:12px; float:left; font-size: 24px;">&nbsp;&nbsp;物业通知</h3>
          <div style="clear:both"></div>
          <p style="line-height: 45px;">
            物业管理人员会将通知发送给对应用户，用户可在通知栏查看
          </p>
        </div>
        <div class="col-lg-4">
          <i class="fas fa-3x fa-comments text-center tm-icon" style="float:left"></i>
          <h3 class="text-center tm-text-primary mb-4" style="padding-top:12px; float:left; font-size: 24px;">&nbsp;&nbsp;小区话题</h3>
          <div style="clear:both"></div>
          <p style="line-height: 45px;">
            平台上有小区论坛，用户可以在论坛中结识好友、畅所欲言
          </p>
        </div>
        <div class="col-lg-4 mt-5 mt-lg-0">
          <i class="fas fa-3x fa-phone text-center tm-icon" style="float:left"></i>
          <h3 class="text-center tm-text-primary mb-4" style="padding-top:12px; float:left; font-size: 24px;">&nbsp;&nbsp;小区服务</h3>
          <div style="clear:both"></div>
          <p style="line-height: 45px;">
            在这里用户可以享受超市送货、物业维修、电脑维修等服务
          </p>
        </div>
        <div class="col-lg-4 mt-5 mt-lg-0">
          <i class="fas fa-3x fa-money-check text-center tm-icon" style="float:left"></i>
          <h3 class="text-center tm-text-primary mb-4" style="padding-top:12px; float:left; font-size: 24px;">&nbsp;&nbsp;物业缴费</h3>
          <div style="clear:both"></div>
          <p style="line-height: 45px;">
            在线查询和缴纳物业费、停车费，无需前往物业收费处
          </p>
        </div>
        <div class="col-lg-4">
          <i class="fas fa-3x fa-envelope text-center tm-icon" style="float:left"></i>
          <h3 class="text-center tm-text-primary mb-4" style="padding-top:12px; float:left; font-size: 24px;">&nbsp;&nbsp;邮包提醒</h3>
          <div style="clear:both"></div>
          <p style="line-height: 45px;">
            有邮包寄到小区被收发室代签之后，会给用户发送邮包提醒信息，
            同时用户也可以享受上门寄件服务
          </p>
        </div>
        <div class="col-lg-4 mt-5 mt-lg-0">
          <i class="fas fa-3x fa-hand-holding-usd text-center tm-icon" style="float:left"></i>
          <h3 class="text-center tm-text-primary mb-4" style="padding-top:12px; float:left; font-size: 24px;">&nbsp;&nbsp;周边优惠</h3>
          <div style="clear:both"></div>
          <p style="line-height: 45px;">
            APP将以地图的方式，展示住户小区周边的优惠商家，团购活动等信息。
            户主可以凭客户端，便捷的查找商家位置，
            并在指定商家，享受专为住户提供的专项优惠活动，获得更多超值优惠。
          </p>
        </div>
        <div class="col-lg-4 mt-5 mt-lg-0">
          <i class="fas fa-3x fa-shopping-cart text-center tm-icon" style="float:left"></i>
          <h3 class="text-center tm-text-primary mb-4" style="padding-top:12px; float:left; font-size: 24px;">&nbsp;&nbsp;小区团购</h3>
          <div style="clear:both"></div>
          <p style="line-height: 45px;">
            是为小区居民特别推出的团购项目，小区居民只要展示手机客户端，
            证明您是某小区的住户，在商家即可享受相应的团购优惠。
            无需出示任何其他手续
          </p>
        </div>
      </div>
    </div>
  </section>

  <!-- Contact -->
  <section id="contact" class="tm-section-pad-top tm-parallax-2">
    <div class="container tm-container-contact">
      <div class="row">
        <div class="col-12">
          <h2 class="mb-4 tm-section-title" style="font-size: 24px;">联系我们</h2>
          <div class="mb-5 tm-underline">
            <div class="tm-underline-inner"></div>
          </div>
          <p class="mb-5" style="line-height: 45px;">
            没有找到自己的小区？请小区负责人通过以下方式联系我们，即可将自己的小区注册成为智能生活小区平台的一员，
            享受我们平台带来的种种快捷与便利！如果在使用过程中出现问题，也可以通过以下方式联系我们解决哦：)<br>
            (点击电话号码或者邮箱地址即可快速拨打电话或发邮件)
          </p>
        </div>

        <div class="col-sm-12 col-md-6 d-flex align-items-center tm-contact-item">
          <a href="tel:0100200340" class="tm-contact-item-link">
            <i class="fas fa-3x fa-phone mr-4"></i>
            <span class="mb-0">010-020-0340</span>
          </a>
        </div>
        <div class="col-sm-12 col-md-6 d-flex align-items-center tm-contact-item">
          <a href="mailto:info@company.co" class="tm-contact-item-link">
            <i class="fas fa-3x fa-envelope mr-4"></i>
            <span class="mb-0">info@company.co</span>
          </a>
        </div>
      </div>
    </div>
  </section>
  </body>
</template>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>

<script>
/* eslint-disable */
function getOffSet () {
  let _offset = 450
  let windowHeight = window.innerHeight

  if (windowHeight > 500) {
    _offset = 400
  }
  if (windowHeight > 680) {
    _offset = 300
  }
  if (windowHeight > 830) {
    _offset = 210
  }

  return _offset
}

function setParallaxPosition ($doc, multiplier, $object) {
  let offset = getOffSet()
  let fromTop = $doc.scrollTop()
  let bgCss = 'center ' + (multiplier * fromTop - offset) + 'px'
  $object.css({ 'background-position': bgCss })
}

// Parallax function
// Adapted based on https://codepen.io/roborich/pen/wpAsm
let backgroundImageParallax = function ($object, multiplier, forceSet) {
  multiplier = typeof multiplier !== 'undefined' ? multiplier : 0.5
  multiplier = 1 - multiplier
  let $doc = $(document)
  // $object.css({"background-attatchment" : "fixed"});

  if (forceSet) {
    setParallaxPosition($doc, multiplier, $object)
  } else {
    $(window).scroll(function () {
      setParallaxPosition($doc, multiplier, $object)
    })
  }
}

$(function () {
  // Hero Section - Background Parallax
  backgroundImageParallax($('.tm-parallax'), 0.30, false)

  // Handle window resize
  window.addEventListener('resize', function () {
    backgroundImageParallax($('.tm-parallax'), 0.30, true)
  }, true)

  // Detect window scroll and update navbar
  $(window).scroll(function (e) {
    if ($(document).scrollTop() > 120) {
      $('.tm-navbar').addClass('scroll')
    } else {
      // $('.tm-navbar').removeClass('scroll')
    }
  })

  // Close mobile menu after click
  $('#tmNav a').on('click', function () {
    $('.navbar-collapse').removeClass('show')
  })

  // Add smooth scrolling to all links
  // https://www.w3schools.com/howto/howto_css_smooth_scroll.asp
  $('a').on('click', function (event) {
    if (this.hash !== '') {
      event.preventDefault()
      let hash = this.hash

      $('html, body').animate({
        scrollTop: $(hash).offset().top
      }, 400, function () {
        window.location.hash = hash
      })
    } // End if
  })


})

</script>
