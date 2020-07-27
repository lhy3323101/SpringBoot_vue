<template>
  <div>
    <div>
      <h1>仅支持新增功能权限，如添加错误，请联系管理员</h1>
    </div>
    <el-row>
      <el-col>
        <el-button style="float: left;margin: 18px 2%" type="primary" round @click="editForm()">新增</el-button>
      </el-col>
    </el-row>
    <el-row>
      <el-col>
        <div>
          <el-card style="margin: 18px 2%;width: 95%">
            <el-table
              :data="permissions"
              stripe
              :default-sort = "{prop: 'id', order: 'ascending'}"
              style="width: 100%"
              :max-height="tableHeight">
              <el-table-column
                type="selection"
                width="55">
              </el-table-column>
              <el-table-column
                prop="id"
                label="id"
                sortable
                width="100">
              </el-table-column>
              <el-table-column
                prop="name"
                label="功能"
                fit>
              </el-table-column>
              <el-table-column
                prop="desc"
                label="功能描述"
                fit>
              </el-table-column>
              <el-table-column
                prop="url"
                label="url"
                fit>
              </el-table-column>
            </el-table>
          </el-card>
        </div>
      </el-col>
    </el-row>
    <el-dialog
      title="新增功能权限"
      center
      :visible.sync="dialogFormVisible">
      <el-form :model="dataForm" style="text-align: left" ref="dataForm" :rules="rules">
        <el-form-item label="名称(英文)：" label-width="120px" prop="name">
          <span style="color: red">英文单词之间的间隔请使用下划线(_)</span>
          <el-input v-model="dataForm.name" autocomplete="off" ></el-input>
        </el-form-item>
        <el-form-item label="功能描述：" label-width="120px" prop="desc">
          <el-input v-model="dataForm.desc" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="url：" label-width="120px" prop="url">
          <el-input v-model="dataForm.url" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit(dataForm)">确 定</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>

    import {admin} from '@/api/index.js'

    export default {
        name: "Permission",
        data(){
            return{
                rules:{
                    name:[
                        {required:true,trigger:'blur',message:"请填写功能名(英文)"}
                    ],
                    desc:[
                        {required: true, trigger:'blur', message:"请填写功能描述"}
                    ],
                    url:[
                        {required:true,trigger:'blur',message:"请填写功能链接"}
                    ]
                },
                dataForm:{
                    name:'',
                    url:'',
                    desc:'',
                },
                permissions:[],
                dialogFormVisible:false,
            }
        },
        methods:{
            editForm(){
                if (this.$refs["dataForm"]!==undefined) {
                    this.$refs["dataForm"].resetFields();
                }
                this.dataForm = {
                    name:'',
                    url:'',
                    desc:''
                };
                this.dialogFormVisible = true;
            },
            onSubmit(value){
                let message = this.$message;
                let param ={
                    desc:value.desc,
                    name:value.name,
                    url:value.url,
                };
                this.$refs['dataForm'].validate(valid => {
                    if (valid){
                        admin.addPermission(param)
                            .then(data => {
                                if (data && data.code === 1){
                                    message.success("新增功能成功");
                                }else {
                                    message.warning("新增功能异常，请重试，重试无效请联系管理员")
                                }
                            })
                            .catch(() => {
                                message.error("新增功能异常，请联系管理员")
                            })
                            .finally(() => {
                                this.dialogFormVisible = false;
                                this.initPermissions();
                            })

                    }
                })
            },
            initPermissions(){
                let _this = this;
                let message = this.$message;
                admin.initPermissions()
                    .then(data => {
                        if (data && data.code === 1){
                            _this.permissions = data.re;
                        }
                    })
                    .catch(() => {
                        message.error("初始化功能列表失败")
                    })
            },
        },
        computed:{
            tableHeight () {
                return window.innerHeight - 320
            }
        },
        mounted() {
            this.initPermissions();
        }
    }
</script>

<style scoped>

</style>
