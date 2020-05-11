import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/index',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/',
      component: resolve => require(['../components/Login.vue'], resolve)
    },
    {
      path: '/register',
      component: resolve => require(['../components/Register.vue'], resolve)
    },
  ]
})
