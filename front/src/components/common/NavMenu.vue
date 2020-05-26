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
    <a href="https://www.baidu.com" style="color: #222;float: right;padding: 20px;">更多功能</a>
    <em class="el-icon-menu" style="float:right;font-size: 45px;color: #222;padding-top: 8px"></em>
    <span style="position: absolute;padding-top: 20px;right: 46%;font-size: 20px;font-weight: bold">Angel Palace</span>
  </el-menu>
</template>

<script>
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
        }
    }
</script>

<style scoped>
  a{
    text-decoration: none;
  }

  span {
    pointer-events: none;
  }
</style>
