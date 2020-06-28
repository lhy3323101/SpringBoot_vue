<template>
  <el-menu
    :default-active="'/index'"
    router
    mode="horizontal"
    background-color="white"
    text-color="#222"
    active-text-color="#ffd04b"
    style="min-width: 1300px">
    <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name">{{item.navItem}}</el-menu-item>
    <em class="el-icon-switch-button" v-on:click="logout" style="float:right;font-size: 30px;color: #222;padding: 20px"></em>
    <span style="position: absolute;padding-top: 20px;right: 46%;font-size: 20px;font-weight: bold">Angel Palace</span>
  </el-menu>
</template>

<script>
    import {api} from '@/api/index.js'

    export default {
        name: "NavMenu",
        data(){
            return {
                navList: [
                    {name: '/index', navItem: '首页'},
                    {name: '/jotter', navItem: '笔记本'},
                    {name: '/library', navItem: '图书馆'},
                    {name: '/admin', navItem: '个人中心'}
                ]
            }
        },
        methods:{
            logout(){
                var _this = this;
                api.logout().then(data => {
                    if (data.code === 1){
                        _this.$store.commit('logout');
                        _this.$router.replace('login');
                    }
                })
            }
        }
    }
</script>

<style scoped>
  a{
    text-decoration: none;
  }

  .el-icon-switch-button {
    cursor: pointer;
    outline:0;
  }

  span {
    pointer-events: none;
  }
</style>
