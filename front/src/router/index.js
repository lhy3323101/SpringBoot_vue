import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'

Vue.use(Router)

export default new Router({
  mode:'history',
  routes: [
    {
      path:'/home',
      name:'Home',
      component:resolve => require(['../components/Home.vue'], resolve),
      redirect:'/index',
      children:[
        {
          path: '/index',
          name: 'HelloWorld',
          component: HelloWorld,
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/library',
          name: 'Library',
          component:resolve => require(['../components/library/LibraryIndex.vue'], resolve),
          meta: {
            requireAuth: true
          }
        },
      ]
    },
    {
      path: '/login',
      component: resolve => require(['../components/Login.vue'], resolve),
    },
    {
      path: '/',
      name:'Home',
      redirect:'/index',
      component:resolve => require(['../components/Home.vue'], resolve),
      meta: {
        requireAuth: true
      }
    },
    {
      path: '/register',
      component: resolve => require(['../components/Register.vue'], resolve)
    },
  ]
})
