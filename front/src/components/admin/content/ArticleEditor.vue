<template>
  <div>
    <el-row style="margin: 18px 0px 0px 18px ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin'}">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/admin/content/book'}">内容管理</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/admin/content/article'}">文章管理</el-breadcrumb-item>
        <el-breadcrumb-item>编辑器</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <el-row>
      <el-input
        v-model="article.title"
        style="margin: 10px 0px;font-size: 18px;"
        placeholder="请输入标题">
      </el-input>
    </el-row>
    <el-row style="height: calc(100vh - 140px);">
      <mavon-editor  v-model="article.contentMd"
                     style="height: 100%;"
                     ref=md
                     @save="saveArticles"
                     fontSize="16px">
        <button type="button" class="op-icon el-icon-document" :title="'摘要/封面'" slot="left-toolbar-after"
                @click="dialogVisible = true"></button>
      </mavon-editor>
      <el-dialog
        :visible.sync="dialogVisible"
        width="30%">
        <el-divider content-position="left">摘要</el-divider>
        <el-input
          type="textarea"
          v-model="article.articleAbstract"
          rows="6"
          maxlength="255"
          show-word-limit></el-input>
        <el-divider content-position="left">封面</el-divider>
        <div style="margin-top: 20px">
          <el-input v-model="this.article.cover" autocomplete="off" placeholder="图片 URL"></el-input>
          <img-upload @onUpload="uploadImg" @delUrl="delUrl" ref="imgUpload" style="margin-top: 5px"></img-upload>
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
        </span>
      </el-dialog>
    </el-row>
  </div>
</template>

<script>
  import ImgUpload from '../../library/ImgUpload'
  import {article} from '@/api/index.js'

    export default {
        name: "ArticleEditor",
        components:{ImgUpload},
        data(){
            return{
                article:{
                    cover:'',
                    articleAbstract:'',

                },
                dialogVisible:false,
            }
        },
        methods:{
            uploadImg (){
                this.article.cover = this.$refs.imgUpload.url;
            },
            delUrl(data){
              this.article.cover = data.url;
            },
            saveArticles(value,render){
                this.$confirm('是否保存并发布文章?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() =>{
                    let param = {
                        id:this.article.id,
                        title:this.article.title,
                        cover:this.article.cover,
                        date:this.article.date,
                        articleAbstract:this.article.articleAbstract,
                        contentHtml:render,
                        contentMd:value
                    };
                    article.saveArticle(param)
                        .then(data => {
                            if (data && data.code === 1){
                                this.$message.success("保存成功")
                            }
                        })
                }).catch(() => {
                    this.$message({
                        type:'error',
                        message:'未发布成功'
                    })
                })
            }
        }
    }
</script>

<style scoped>

</style>
