<template>
  <div class="login-wrap">
    <el-form :model="loginForm" :rules="rules" class="login-container" label-position="left"
             label-width="0px" v-loading="loading">
      <h3 class="login_title">系统登录</h3>
      <el-form-item prop="userName">
        <el-input type="text" v-model="loginForm.userName"
                  auto-complete="off" placeholder="账号"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="loginForm.password"
                  auto-complete="off" placeholder="密码"></el-input>
      </el-form-item>
      <el-checkbox class="login_remember" v-model="checked"
                   label-position="left"><span style="color: #505458">记住密码</span></el-checkbox>
      <el-form-item style="width: 100%">
        <el-button type="primary" style="width: 40%;background: #505458;border: none" @click="login">登录</el-button>
        <router-link to="register"><el-button type="primary" style="width: 40%;background: #505458;border: none">注册</el-button></router-link>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
    import {api} from '@/api/index.js'

    export default {
        name: "Login",
        data:function () {
            return {
                rules: {
                    userName: [
                        { required: true, message: '请输入用户名', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' }
                    ]
                },
                loginForm: {
                    userName: 'admin',
                    password: '123123'
                },
                checked:true,
                loading: false
            }
        },
        methods:{
            login(){
                let message = this.$message;
                api.login(this.loginForm)
                    .then(data => {
                        console.log(data)
                        if (data.code === 1){
                            message.success(data.msg);
                            this.$router.replace({path:'/index'})
                        }else {
                            message.error(data.msg)
                        }
                    })
            },
        }
    }
</script>

<style scoped>
  .login-wrap{
    background-position: center;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
    background-image: url("../assets/img/login.jpg");
    margin:0;
  }

  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 90px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }

  .login_remember {
    margin: 0px 0px 35px 0px;
    text-align: left;
  }
</style>
