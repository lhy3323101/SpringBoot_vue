<template>
  <div>
    <el-row>
      <el-dialog title="新增/修改" center :visible.sync="dialogFormVisible">
        <el-form :model="dataSource" :rules="rules" ref="dataSource" label-position="right">
          <el-form-item label="编码:" :label-width="formLabelWidth" prop="code">
            <el-input v-model="dataSource.code" maxlength="50" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="值:" :label-width="formLabelWidth" prop="value">
            <el-input v-model="dataSource.value" maxlength="500" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="排序:" :label-width="formLabelWidth">
            <el-input-number v-model="dataSource.sortId" controls-position="right" :max="100" :min="0"></el-input-number>
          </el-form-item>
          <el-form-item label="备注:" :label-width="formLabelWidth">
            <el-input type="textarea" :rows="5" v-model="dataSource.remark" maxlength="100" placeholder="请输入内容" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
        </div>
      </el-dialog>
    </el-row>
    <el-row>
      <el-col>
        <el-button-group>
          <el-button type="primary" @click="toCreate" >新增</el-button>
        </el-button-group>
      </el-col>
    </el-row>
  </div>
</template>

<script>

    export default {
        data(){
            return{
                dataSource:{
                    sortId:0,
                    id:'',
                    code:'',
                    typeId:'',
                    type:'',
                    remark:'',
                    value:'',
                    parentId:''
                },
                rules: {
                    typeId: [
                        {required: true, trigger: 'change', message: "请输入字典类型"}
                    ],
                    value: [
                        {required: true, trigger: 'blur', message: "请输入字典value"}
                    ],
                    code: [
                        {required: true, trigger: 'blur', message: "请输入编码"},
                        {max: 500, message: '不能超过500个字符', trigger: 'blur' }
                    ]
                },
                formLabelWidth: '100px',
                dialogFormVisible:false,
            }
        },
        methods:{
            toCreate(){
                if (this.$refs["dataSource"]!==undefined) {
                    this.$refs["dataSource"].resetFields();
                }
                this.dataSource ={
                    sortId:0,
                        id:'',
                        code:'',
                        typeId:'',
                        type:'',
                        remark:'',
                        value:'',
                        parentId:''
                };
                this.dialogFormVisible = true;
            }
        }

    }
</script>

<style>
  .typeDetailClass .el-dialog { width: 30% }
  .typeDetailClass .el-input-number { width: auto }
  .typeDetailClass .el-select { width: auto}
  .typeImportClass .el-dialog { width: 30% }
  .typeExportClass .el-dialog { width: 80% }

</style>
