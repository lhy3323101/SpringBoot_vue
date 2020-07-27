<template>
  <div class="articles-area">
    <el-card style="text-align: left;width: 990px;margin: 35px auto 0 auto">
      <div>
        <span style="font-size: 20px"><strong>{{article.title}}</strong></span>
        <el-divider content-position="left">{{article.date}}</el-divider>
        <div class="markdown-body">
          <div v-html="article.contentHtml"></div>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>

    import {article} from '@/api/index.js'
    export default {
        name: 'ArticleDetails',
        data () {
            return {
                article: []
            }
        },
        mounted () {
            this.loadArticle()
        },
        methods: {
            loadArticle () {
                let _this = this;
                let param ={
                    id:_this.$route.query.id
                };
                article.loadArticle(param)
                    .then(data => {
                        _this.article = data.re;
                    })
            }
        }
    }
</script>

<style scoped>
  @import "../../style/markdown.css";
</style>
