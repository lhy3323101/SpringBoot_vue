<template>
  <div style="margin-top: 40px">
    <!--<el-button @click="addArticle()">添加文章</el-button>-->
    <div class="articles-area">
      <el-card style="text-align: left">
        <div v-for="article in articles" :key="article.id">
          <div style="float:left;width:85%;height: 150px;">
            <router-link class="article-link" :to="{path:'article/detail',query:{id: article.id}}"><span style="font-size: 20px"><strong>{{article.title}}</strong></span></router-link>
            <el-divider content-position="left">{{article.date}}</el-divider>
            <router-link class="article-link" :to="{path:'article/detail',query:{id: article.id}}"><p>{{article.articleAbstract}}</p></router-link>
          </div>
          <el-image
            style="margin:18px 0 0 30px;width:100px;height: 100px"
            :src="article.cover"
            fit="cover"></el-image>
          <el-divider></el-divider>
        </div>
      </el-card>
    </div>
    <el-pagination
      background
      :page-sizes="[10, 50, 100]"
      layout="total, prev, pager, next, jumper"
      @current-change="handleCurrentChange"
      :page-size="pageSize"
      :total="total">
    </el-pagination>
  </div>
</template>

<script>

    import {article} from '@/api/index.js'

    export default {
        name: 'ArticleList',
        data () {
            return {
                articles: [],
                pageSize: 10,
                total: '',
            }
        },
        mounted () {
            this.loadArticles()
        },
        methods: {
            loadArticles() {
                let _this = this;
                let param = {
                    size:this.pageSize,
                };
                article.initArticleList(param)
                    .then(data => {
                        if (data && data.code === 1){
                            _this.articles = data.re.list;
                            _this.total = data.re.total;
                        }
                    })
            },
            handleCurrentChange(page) {
                let _this = this;
                let param = {
                    size:this.pageSize,
                    pageNo:page
                };
                article.queryList(param)
                    .then(data => {
                        if (data && data.code === 1){
                            _this.articles = data.re.list;
                            _this.total = data.re.total;
                        }
                    })
            }
        }
    }
</script>

<style scoped>
  .articles-area {
    width: 990px;
    height: 750px;
    margin-left: auto;
    margin-right: auto;
  }

  .article-link {
    text-decoration: none;
    color: #606266;
  }

  .article-link:hover {
    color: #409EFF;
  }
</style>

