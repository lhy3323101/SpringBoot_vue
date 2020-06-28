// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import request from './utils/request'
Vue.use(ElementUI)
Vue.config.productionTip = false
Vue.prototype.request = request;
import store from './store'

import {api} from '@/api/index.js'

router.beforeEach((to,from,next) => {
  if (to.meta.requireAuth){
    if (store.state.user && store.state.user.userName){
      console.log(store.state.user);
      api.authentication()
        .then(data => {
          if (data.code === 1){
            next();
          }else {
            next({
              path:'login',
              query:{redirect: to.fullPath}
            })
          }
        })
    }else {
      next({
        path:'login',
        query:{redirect: to.fullPath}
      })
    }
  }else {
    next()
  }
})

new Vue({
  el: '#app',
  router,
  render: h => h(App),
  store,
  components: { App },
  template: '<App/>'
})


