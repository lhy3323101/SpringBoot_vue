<template>
  <div>
    <el-row style="margin: 18px 0px 0px 18px ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>内容管理</el-breadcrumb-item>
        <el-breadcrumb-item>文章管理</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <el-link href="/admin/content/editor" :underline="false" target="_blank" class="add-link">
      <el-button type="success">写文章</el-button>
    </el-link>
    <el-card style="margin: 18px 2%;width: 95%">
      <el-table
        :data="articles"
        stripe
        style="width: 100%"
        :max-height="tableHeight">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline>
              <el-form-item>
                <span>{{ props.row.articleAbstract }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column
          prop="title"
          label="题目（展开查看摘要）"
          fit>
        </el-table-column>
        <el-table-column
          prop="date"
          label="发布日期"
          width="200">
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="180">
          <template slot-scope="scope">
            <el-button
              @click.native.prevent="viewArticle(scope.row.id)"
              type="text"
              size="small">
              查看
            </el-button>
            <el-button
              @click.native.prevent="editArticle(scope.row)"
              type="text"
              size="small">
              编辑
            </el-button>
            <el-button
              @click.native.prevent="deleteArticle(scope.row.id)"
              type="text"
              size="small">
              移除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="margin: 20px 0 50px 0">
        <el-pagination
          background
          style="float:right;"
          layout="total, prev, pager, next, jumper"
          @current-change="handleCurrentChange"
          :page-size="pageSize"
          :total="total">
        </el-pagination>
      </div>
    </el-card>
  </div>
</template>

<script>
    import {article} from '@/api/index.js'

    export default {
        name: 'ArticleManagement',
        data () {
            return {
                articles: [],
                pageSize: 10,
                total: ''
            }
        },
        mounted () {
            this.loadArticles()
        },
        computed: {
            tableHeight () {
                return window.innerHeight - 320
            }
        },
        methods: {
            loadArticles() {
                let _this = this;
                let param = {
                    pageSize:this.pageSize,
                    pageNo:1
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
                    pageSize:this.pageSize,
                    pageNo:page
                };
                article.queryList(param)
                    .then(data => {
                        if (data && data.code === 1){
                            _this.articles = data.re.list;
                            _this.total = data.re.total;
                        }
                    })
            },
            viewArticle (id) {
                let articleUrl = this.$router.resolve(
                    {
                        path: 'article/detail',
                        query: {
                            id: id
                        }
                    }
                )
                window.open(articleUrl.href, '_blank')
            },
            editArticle (article) {
                this.$router.push(
                    {
                        name: 'Editor',
                        params: {
                            article: article
                        }
                    }
                )
            },
            deleteArticle (id) {
                this.$confirm('此操作将永久删除该文章, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                      let param = {
                          id:id,
                      };
                      article.deleteArticle(param)
                          .then(data => {
                              if (data.code === 1){
                                  this.loadArticles();
                              }
                          })
                    }
                ).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    })
                })
            }
        }
    }
</script>

<style scoped>
  .add-link {
    margin: 18px 0 15px 10px;
    float: left;
  }
</style>

