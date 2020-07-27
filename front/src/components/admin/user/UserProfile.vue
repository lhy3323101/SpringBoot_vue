<template>
  <div>
    <el-dialog
      title="修改用户信息"
      :visible.sync="dialogFormVisible">
      <el-form v-model="selectedUser" style="text-align: left" ref="dataForm">
        <el-form-item label="用户名" label-width="120px" prop="userName">
          <label>{{selectedUser.userName}}</label>
        </el-form-item>
        <el-form-item label="真实姓名" label-width="120px" prop="name">
          <el-input v-model="selectedUser.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="手机号" label-width="120px" prop="phone">
          <el-input v-model="selectedUser.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" label-width="120px" prop="mail">
          <el-input v-model="selectedUser.mail" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" label-width="120px" prop="password">
          <el-button type="warning" @click="resetPassword(selectedUser.username)">重置密码</el-button>
        </el-form-item>
        <el-form-item label="角色分配" label-width="120px" prop="roles">
          <el-checkbox-group v-model="selectedRolesIds">
            <el-checkbox v-for="(role,i) in roles" :key="i" :label="role.id">{{role.nameZh}}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit(selectedUser)">确 定</el-button>
      </div>
    </el-dialog>
    <el-card style="margin: 18px 2%;width: 95%">
      <el-table
        :data="users"
        stripe
        :default-sort = "{prop: 'id', order: 'ascending'}"
        style="width: 100%"
        @selection-change = "log"
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
          prop="userName"
          label="用户名"
          fit>
        </el-table-column>
        <el-table-column
          prop="name"
          label="真实姓名"
          fit>
        </el-table-column>
        <el-table-column
          prop="phone"
          label="手机号"
          fit>
        </el-table-column>
        <el-table-column
          prop="mail"
          label="邮箱"
          show-overflow-tooltip
          fit>
        </el-table-column>
        <el-table-column
          label="状态"
          sortable
          width="100">
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.enabled"
              active-color="#13ce66"
              inactive-color="#ff4949"
              @change="(value) => commitStatusChange(value, scope.row)">
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column
          label="操作"
          width="120">
          <template slot-scope="scope">
            <el-button
              @click="editUser(scope.row)"
              type="text"
              size="small">
              编辑
            </el-button>
            <el-button
              type="text"
              size="small">
              移除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="margin: 20px 0 20px 0;float: left">
        <el-button round style="background: #409eff;color:whitesmoke">批量删除</el-button>
      </div>
      <div style="margin: 20px 0 50px 0">
        <el-pagination
          background
          style="float:right;"
          layout="total, prev, pager, next, jumper"
          @current-change="handleCurrentChange"
          :page-size="this.pageSize"
          :total="this.total">
        </el-pagination>
      </div>
    </el-card>
  </div>
</template>

<script>
    import {admin} from '@/api/index.js'

    export default {
        name: "UserProfile",
        data(){
            return{
                users:[],
                dialogFormVisible:false,
                selectedUser:{},
                selectedRolesIds:[],
                selectUser:[],
                pageSize: 10,
                total:0,
                roles:[],
            }
        },
        methods:{
            initRoles(){
                admin.initRoles()
                    .then(data => {
                        if (data && data.code === 1){}
                        this.roles = data.re;
                    })
            },
            queryUsers(){
                let _this = this;
                let param = {
                    pageSize:this.pageSize,
                    pageNo:1
                }
                admin.initUserList(param)
                    .then(data => {
                        if (data && data.code === 1){
                            _this.users = data.re.list;
                            _this.total = data.re.total;
                        }
                    })
            },
            log(val){
                console.log(val);
                let _this = this;
                let testUser = [];
                for (let i=0;i<val.length;i++){
                    if (_this.selectUser.indexOf(val[i].id) === -1){
                        testUser.push(val[i]);
                    }
                }
                _this.selectUser = [];
                for (let i=0;i<val.length;i++){
                    _this.selectUser.push(val[i].id)
                }
            },
            editUser (user) {
                this.dialogFormVisible = true
                this.selectedUser = user
                let roleIds = []
                if(user.roles && user.roles.length > 0){
                    for (let i = 0; i < user.roles.length; i++) {
                        roleIds.push(user.roles[i])
                    }
                }
                this.selectedRolesIds = roleIds
            },
            handleCurrentChange(page) {
                let _this = this;
                let param = {
                    pageSize:this.pageSize,
                    pageNo:page
                };
                admin.queryUsers(param)
                    .then(data => {
                        if (data && data.code === 1){
                            _this.users = data.re.list;
                            _this.total = data.re.total;
                        }
                    })

            },
            commitStatusChange (value, user) {
                let _this = this;
                let message = this.$message;
                let param ={
                    id:user.id,
                    enabled:value
                }
                if (user.userName !== 'lhy') {
                    admin.changeUserStatus(param)
                        .then(data => {
                            if (data && data.code === 1){
                                if (value){
                                    message.success('用户 [' + user.userName + '] 已启用')
                                }else {
                                    message.warning('用户 [' + user.userName + '] 已禁用')
                                }
                            }
                        })
                        .finally(() =>{
                            _this.queryUsers();
                        })
                } else {
                    user.enabled = true
                    this.$alert('不能禁用超级管理员账户')
                }
            },
            onSubmit(user){
                let _this = this;
                let roles = [];
                let message = this.$message;
                for(let i = 0; i < _this.selectedRolesIds.length ; i++){
                    roles.push(_this.selectedRolesIds[i]);
                }
                let param = {
                    id:user.id,
                    name :user.name,
                    userName:user.userName,
                    phone:user.phone,
                    mail:user.mail,
                    roles:roles
                }
                admin.saveUser(param)
                    .then(data => {
                        if (data && data.code === 1){
                            _this.dialogFormVisible = false;
                            _this.queryUsers();
                            message.success("保存成功")
                        }else if (data){
                            _this.dialogFormVisible = false;
                            message.warning(data.msg)
                        }
                    })
                    .catch(() => {
                        message.error("保存失败，请联系管理员")
                    })
            }
        },
        mounted() {
            this.queryUsers();
            this.initRoles();
        },
        computed:{
            tableHeight () {
                return window.innerHeight - 320
            }
        }

    }
</script>

<style scoped>

</style>
