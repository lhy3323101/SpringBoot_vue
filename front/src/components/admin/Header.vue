<template>
  <el-card class="admin-header">
    <a href="/index">
      <img src="../../assets/img/icon/icon2.png" alt="" width="55px" style="float: left;margin-top: -5px;">
    </a>
    <span style="font-size: 32px;font-weight: bold;position:absolute;left: 100px">HEAVEN</span>
    <i class="el-icon-switch-button" v-on:click="logout" style="font-size: 40px;float: right"></i>
  </el-card>
</template>

<script>
    import {createRouter} from '../../router/index'
    import {api} from '@/api/index.js'

    export default {
        name: 'Header',
        methods: {
            logout () {
                var _this = this
                api.logout().then(data => {
                    if (data.code === 1){
                        _this.$store.commit('logout');
                        _this.$router.replace('/login');
                        // 清空路由，防止路由重复加载
                        const newRouter = createRouter()
                        _this.$router.matcher = newRouter.matcher
                    }
                })
            }
        }
    }
</script>

<style scoped>
  .admin-header {
    height: 80px;
    opacity: 0.85;
    line-height: 40px;
    width: 100% !important;
  }
  .el-icon-switch-button {
    cursor: pointer;
    outline:0;
  }
</style>
