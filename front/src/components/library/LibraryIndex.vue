<template>
  <el-container>
  <el-aside style="width: 200px;margin-top: 20px">
    <switch></switch>
    <SideMenu @indexSelect ='test' :sendData="pid" ></SideMenu>
  </el-aside>
  <el-main>
    <Book class="books-area"></Book>
  </el-main>
  </el-container>
</template>

<script>
  import SideMenu from './SideMenu'
  import Book from './Book'

    export default {
        name: "LibraryIndex",
        components:{SideMenu,Book},
        data(){
            return{
                pid:'',
            }
        },
        methods:{
            test(data){
                console.log(data)
                this.pid = data.key
            }
        }
    }
</script>

<style scoped>
  .books-area {
    width: 990px;
    margin-left: auto;
    margin-right: auto;
  }
</style>
