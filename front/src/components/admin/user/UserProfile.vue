<template>
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
  </el-card>
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
            }
        },
        methods:{
          queryUsers(){
              admin.queryUsers()
                  .then(data => {
                      if (data.code === 1){
                          this.users = data.re;
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
              console.log(testUser);
              _this.selectUser = [];
              for (let i=0;i<val.length;i++){
                  _this.selectUser.push(val[i].id)
              }

            },
            editUser (user) {
                this.dialogFormVisible = true
                this.selectedUser = user
                let roleIds = []
                for (let i = 0; i < user.roles.length; i++) {
                    roleIds.push(user.roles[i].id)
                }
                this.selectedRolesIds = roleIds
            },
            commitStatusChange (value, user) {
                /*if (user.username !== 'admin') {
                    this.$axios.put('/admin/user/status', {
                        enabled: value,
                        username: user.username
                    }).then(resp => {
                        if (resp && resp.data.code === 200) {
                            if (value) {
                                this.$message('用户 [' + user.username + '] 已启用')
                            } else {
                                this.$message('用户 [' + user.username + '] 已禁用')
                            }
                        }
                    })
                } else {
                    user.enabled = true
                    this.$alert('不能禁用管理员账户')
                }*/
            }
        },
        mounted() {
            this.queryUsers();
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
