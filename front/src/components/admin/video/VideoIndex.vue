<template>
  <el-row>
    <el-col>
      <el-table
        :data="tableData"
        style="width: 100%">
        <el-table-column
          type="index"
          width="50">
        </el-table-column>
        <el-table-column
          label="影片名"
          width="180">
          <template slot-scope="scope">
            <a href="javascript:void(0);" v-on:click="handleClick(scope.row.name)">{{scope.row.label}}</a>
          </template>
        </el-table-column>
        <el-table-column
          prop="introduction"
          label="影片介绍">
        </el-table-column>
      </el-table>
    </el-col>
  </el-row>
</template>

<script>



    export default {
        name: "videoIndex",
        data(){
            return{
                tableData:[
                    {
                    label:"《天堂测试》",
                    introduction:"这是一部用于测试的短片",
                    },
                    {
                    label:"《典礼》",
                    name:"典礼",
                    introduction:"典礼短视频"
                    }],
            }
        },
        methods:{
            handleClick(namePath){
                if(namePath !== undefined && namePath){
                    console.log("preview")
                    window.open("http://localhost:8080/video/preview/" + namePath)
                }else {
                    console.log("preview123")
                    window.open("http://localhost:8080/video/preview/123")
                }
            }
        }
    }
</script>

<style scoped>

</style>
