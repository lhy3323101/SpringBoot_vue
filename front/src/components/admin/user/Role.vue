<template>
  <div>
    <el-dialog
      title="修改用户信息"
      :visible.sync="dialogFormVisible">
      <el-form v-model="selectRole" style="text-align: left" ref="dataForm">
        <el-form-item label="角色名称：" label-width="120px" prop="nameZh">
          <label>{{selectRole.nameZh}}</label>
        </el-form-item>
        <el-form-item label="角色：" label-width="120px" prop="name">
          <el-input v-model="selectRole.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="功能配置：" label-width="120px" prop="permissions">
          <el-checkbox-group v-model="selectPermissions">
            <el-checkbox v-for="(permission,i) in permissions" :key="i" :label="permission.id">{{permission.desc}}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="菜单配置：" label-width="120px" prop="menus">
          <el-tree
            :data="menus"
            :props="props"
            show-checkbox
            :default-checked-keys="this.selectedMenusIds"
            node-key="id"
            ref="tree">
          </el-tree>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit(selectRole)">确 定</el-button>
      </div>
    </el-dialog>
    <el-card style="margin: 18px 2%;width: 95%">
      <el-table
        :data="roles"
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
          prop="nameZh"
          label="角色名称"
          fit>
        </el-table-column>
        <el-table-column
          prop="name"
          label="角色"
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
              @click="editRole(scope.row)"
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
    </el-card>
  </div>
</template>

<script>

    import {admin} from '@/api/index.js'

    export default {
        name: "Role",
        data(){
            return{
                roles:[],
                selectRole:{},
                selectPermissions:[],
                permissions:[],
                menus:[],
                props: {
                    id: 'id',
                    label: 'nameZh',
                    children: 'children'
                },
                selectedMenusIds:[],
                dialogFormVisible:false,
            }
        },
        methods:{
            commitStatusChange(value,role){
                let _this = this;
                let message = this.$message;
                let param ={
                    id:role.id,
                    enabled:value
                };
                if ( role.name === 'sysAdmin'){
                    role.enabled = true
                    this.$message.warning("不可禁用系统管理员");
                    return;
                }
                admin.changeRoleStatus(param)
                    .then(data => {
                        if (data && data.code === 1){
                            if (value){
                                message.success('角色 [' + role.nameZh + '] 已启用')
                            }else {
                                message.warning('角色 [' + role.nameZh + '] 已禁用')
                            }
                        }
                    })
                    .finally(() =>{
                        _this.initRoles();
                    })

            },
            onSubmit(value){
                let message = this.$message;
                let _this = this;
                let pids = [];
                for (let i = 0 ; i< this.selectPermissions.length; i++ ){
                    pids.push(this.selectPermissions[i])
                }
                let mids = this.$refs.tree.getCheckedKeys();
                let param ={
                    id:value.id,
                    name:value.name,
                    menus:mids,
                    permissions:pids,
                }
                admin.updateRole(param)
                    .then(data => {
                        if (data && data.code === 1){
                            message.success("修改角色成功");
                        }else {
                            message.warning("修改角色异常，请重试，重试无效请联系管理员")
                        }
                    })
                    .catch(() => {
                        message.error("修改角色异常，请联系管理员")
                    })
                    .finally(() => {
                        this.dialogFormVisible = false;
                        this.initRoles();
                    })
            },
            initRoles(){
                let _this = this;
                let message = this.$message;
                admin.initRoles()
                    .then(data => {
                        if (data && data.code === 1){
                            _this.roles = data.re;
                        }else {
                            message.error(data.msg);
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
            initMenus(){
                let _this = this;
                let message = this.$message;
                admin.initMenus()
                    .then(data => {
                        if (data && data.code === 1){
                            _this.menus = data.re;
                        }
                    })
                    .catch(() => {
                        message.error("初始化菜单失败");
                    })
            },
            editRole (role) {
                this.selectRole = role;
                let permissions = [];
                this.selectPermissions = [];
                this.selectedMenusIds = [];
                if(role.permissions && role.permissions.length > 0){
                    for (let i = 0; i < role.permissions.length; i++) {
                        permissions.push(role.permissions[i])
                    }
                }
                this.selectPermissions = permissions;
                let menus = [];
                for (let j = 0; j<role.menus.length ; j++){
                    menus.push(role.menus[j]);
                }
                this.selectedMenusIds = menus;
                if (this.$refs.tree) {
                    this.$refs.tree.setCheckedKeys(menus)
                }
                this.dialogFormVisible = true;
            },

        },
        computed:{
            tableHeight () {
                return window.innerHeight - 320
            }
        },
        mounted() {
            this.initRoles();
            this.initPermissions();
            this.initMenus();
        }
    }
</script>

<style scoped>

</style>
