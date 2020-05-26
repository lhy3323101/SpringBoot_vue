<template>
  <el-menu
    class="categories"
    default-active="0"
    @select="handleSelect"
    active-text-color="red">
    <el-menu-item index="0">
      <em class="el-icon-menu"></em>
      <span slot="title">全部</span>
    </el-menu-item>
    <el-menu-item index="1">
      <em class="el-icon-menu"></em>
      <span slot="title">文学</span>
    </el-menu-item>
    <el-menu-item index="2">
      <em class="el-icon-menu"></em>
      <span slot="title">流行</span>
    </el-menu-item>
    <el-menu-item index="3">
      <em class="el-icon-menu"></em>
      <span slot="title">文化</span>
    </el-menu-item>
    <el-menu-item index="4">
      <em class="el-icon-menu"></em>
      <span slot="title">生活</span>
    </el-menu-item>
    <el-menu-item index="5">
      <em class="el-icon-menu"></em>
      <span slot="title">经管</span>
    </el-menu-item>
    <el-menu-item index="6">
      <em class="el-icon-menu"></em>
      <span slot="title">科技</span>
    </el-menu-item>
    <el-menu-item index="7">
      <em class="el-icon-menu"></em>
      <span slot="title">父组件传给子组件的属性值:{{sendData}}</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
    export default {
        name: "SideMenu",
        props:['sendData'],
        methods:{
            handleSelect(index){
                let data = {
                    key:index
                }
                this.$emit('indexSelect',data)
            }
        }
    }
</script>

<style scoped>
  .categories {
    position: fixed;
    margin-left: 50%;
    left: -600px;
    top: 100px;
    width: 150px;
  }
</style>
