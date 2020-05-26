<template>
  <div class="login-wrap">
    <el-form :model="registerFrom" ref="registerFrom" :rules="rules" class="login-container" label-position="left"
             label-width="0px" v-loading="loading">
      <h3 class="login_title">用户注册</h3>
      <el-form-item prop="userName">
        <el-input type="text" v-model="registerFrom.userName"
                  auto-complete="off" placeholder="账号"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="registerFrom.password"
                  auto-complete="off" placeholder="密码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input type="text" v-model="registerFrom.name"
                  auto-complete="off" placeholder="真实姓名"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input type="text" v-model="registerFrom.phone"
                  auto-complete="off" placeholder="电话号码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input type="text" v-model="registerFrom.mail"
                  auto-complete="off" placeholder="E-Mail"></el-input>
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-button type="primary" style="width: 40%;background: #505458;border: none" v-on:click="register">注册</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
    import {api} from '@/api/index.js'
    export default {
        name: "Resister",
        data(){
            return {
                rules: {
                    userName: [{required: true, message: '用户名不能为空', trigger: 'blur'}],
                    password: [{required: true, message: '密码不能为空', trigger: 'blur'}]
                },
                registerFrom:{
                    userName: '',
                    password: '',
                    name: '',
                    phone: '',
                    mail: ''
                },
                loading: false
            }
        },
        methods:{
            register(){
                let message = this.$message;
                this.$refs['registerFrom'].validate((valid) =>{
                    if (valid) {
                        api.register(this.registerFrom)
                            .then(data => {
                                if (data.code === 1){
                                    message.success("注册成功");
                                    this.$router.replace({path:'/'})
                                }else {
                                    message.error(data.msg);
                                    return false;
                                }
                            })
                    }else {
                        console.log('error submit!!');
                        return false;
                    }
                })

            }
        }
    }
</script>

<style scoped>

  .login-wrap{
    background:url("../assets/img/login.jpg");
    background-position: center;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
  }

  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 120px auto;
    width: 380px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 3px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }

  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }
</style>
