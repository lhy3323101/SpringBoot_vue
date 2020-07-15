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
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import store from './store'
import {api} from '@/api/index.js'

Vue.use(mavonEditor);

router.beforeEach((to,from,next) => {
  if (store.state.user && to.path.startsWith('/admin')){
    initAdminMenu(router, store)
  }

  if (to.meta.requireAuth){
    if (store.state.user && store.state.user.userName){
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

const formatRoutes = (routes) =>{
  let fmtRoutes = [];
  routes.forEach((route) => {
    if (route.children){
      route.children = formatRoutes(route.children)
    }
    let fmtRoute ={
      path:route.path,
      component: resolve => require(['./components/admin/' + route.component + '.vue'],resolve),
      name:route.name,
      nameZh:route.nameZh,
      iconCls:route.iconCls,
      meta:{
        requireAuth:true
      },
      children:route.children
    };
    fmtRoutes.push(fmtRoute)
  })
  return fmtRoutes;
}

const initAdminMenu = (router,store) => {
  if (store.state.adminMenus.length > 0){
    return;
  }
  api.queryMenus()
    .then(data =>{
      if (data && data.code === 1){
        var fmtRoutes = formatRoutes(data.re);
        router.addRoutes(fmtRoutes);
        store.commit('initAdminMenu',fmtRoutes)
      }
    })
    .catch(e =>{
      console.log(e)
    })
}

new Vue({
  el: '#app',
  router,
  render: h => h(App),
  store,
  components: { App },
  template: '<App/>'
})


