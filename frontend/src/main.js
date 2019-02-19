// main.js

// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store/index.js'
// import axios from 'axios' // Vue.js Quick Start 279

// import huskyEZCreator from './assets/naver/se2/js/service/HuskyEZCreator.js'
// import editor from './assets/js/editor.js'
import ui from './assets/js/ui.js'

// Vue.prototype.$axios = axios // Vue.js Quick Start 279
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  // huskyEZCreator,
  // editor,
  ui,
  components: { App },
  template: '<App/>'
})
