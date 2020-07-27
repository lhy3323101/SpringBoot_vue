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
          path:'/index',
          name:'HelloWorld',
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
      path:'/login',
      component: resolve => require(['../components/Login.vue'], resolve),
    },
    {
      path:'/',
      name:'Home',
      redirect:'/admin',
      component:resolve => require(['../components/Home.vue'], resolve),
      meta: {
        requireAuth: true
      }
    },
    {
      path:'/register',
      component: resolve => require(['../components/Register.vue'], resolve)
    },
    {
      path:'/admin',
      name:'Admin',
      component: resolve => require(['../components/admin/AdminIndex.vue'],resolve),
      meta: {
        requireAuth: true
      }
    },
    {
      path: '/admin/content/editor',
      name: 'Editor',
      component: resolve => require(['../components/admin/content/ArticleEditor.vue'],resolve),
      meta: {
        requireAuth: true
      }
    },
    {
      path: '/article',
      name: 'ArticleList',
      component: resolve => require(['../components/article/ArticleList.vue'],resolve),
      meta: {
        requireAuth: true
      }
    },
    {
      path: '/article/detail',
      name: 'ArticleDetail',
      component: resolve => require(['../components/article/ArticleDetails.vue'],resolve),
      meta: {
        requireAuth: true
      }
    },

  ]
})

export const createRouter = routes => new Router({
  mode:'history',
  routes: [
    {
      path:'/home',
      name:'Home',
      component:resolve => require(['../components/Home.vue'], resolve),
      redirect:'/index',
      children:[
        {
          path:'/index',
          name:'HelloWorld',
          component: HelloWorld,
          meta: {
            requireAuth: true
          }
        },
        {
          path:'/library',
          name:'Library',
          component:resolve => require(['../components/library/LibraryIndex.vue'], resolve),
          meta: {
            requireAuth: true
          }
        },
      ]
    },
    {
      path:'/login',
      component: resolve => require(['../components/Login.vue'], resolve),
    },
    {
      path:'/',
      name:'Home',
      redirect:'/index',
      component:resolve => require(['../components/Home.vue'], resolve),
      meta: {
        requireAuth: true
      }
    },
    {
      path:'/register',
      component: resolve => require(['../components/Register.vue'], resolve)
    },
    {
      path:'/admin',
      name:'Admin',
      component: resolve => require(['../components/admin/AdminIndex.vue'],resolve),
      meta: {
        requireAuth: true
      }
    },
    {
      path: '/admin/content/editor',
      name: 'Editor',
      component: resolve => require(['../components/admin/content/ArticleEditor.vue'],resolve),
      meta: {
        requireAuth: true
      }
    }
  ]
})
