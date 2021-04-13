<template>
  <div class="login-wrap">
    <el-form :model="loginForm" :rules="rules" class="login-container" label-position="left"
             label-width="0px" v-loading="loading" @keyup.enter.native="login">
      <h3 class="login_title">系统登录</h3>
      <el-form-item prop="userName">
        <el-input type="text" v-model="loginForm.userName"
                  auto-complete="off" placeholder="账号"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="loginForm.password"
                  auto-complete="off" placeholder="密码"></el-input>
      </el-form-item>
      <el-form-item prop="validCode">
        <div class="col-xs-6">
          <el-input placeholder="请输入验证码" v-model="loginForm.validCode" type="text" :maxlength="4">
          </el-input>
        </div>
      </el-form-item>
      <div class="col-xs-6" style="margin-top: 10px;margin-bottom: 10px">
        <el-image :src="validUrl" @click="updateImage"></el-image>
      </div>
      <el-checkbox class="login_remember" v-model="loginForm.rememberFlag"
                   label-position="left"><span style="color: #505458">记住我(保持登录)</span></el-checkbox>
      <el-form-item style="width: 100%">
        <el-button type="primary" style="width: 40%;background: #505458;border: none" @click.native.prevent="login">登录</el-button>
        <router-link to="register"><el-button type="primary" style="width: 40%;background: #505458;border: none">注册</el-button></router-link>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
    import {api} from '@/api/index.js'
    import {BaseUrl} from '@/utils/common.js'

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
                    ],
                    validCode: [
                        { required: true, message: '请输入验证码', trigger: 'blur' }
                    ],
                },
                loginForm: {
                    userName: '',
                    password: '',
                    rememberFlag:false,
                    validCode:'',
                    uuid:'',
                },
                validUrl:"",
                imageUuid:"",
                loading: false
            }
        },
        created(){
           this.getValidImage();
        },
        methods:{
            /** 新建一个uuid*/
            createUuid(){
                let s = [];
                let hexDigits = "0123456789abcdef";
                for (let i = 0; i < 36; i++) {
                    s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
                }
                s[14] = "4";
                s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);
                s[8] = s[13] = s[18] = s[23] = "-";
                this.imageUuid = s.join("");

            },
            /**获取验证码图片*/
            getValidImage(){
                if (!this.imageUuid){
                    this.createUuid();
                }
                this.validUrl = BaseUrl() + "image/getValidImage?uuid=" + this.imageUuid;
            },
            /**更新验证码图片*/
            updateImage(){
                this.validUrl = BaseUrl() + "image/getValidImage?uuid=" + this.imageUuid + "&d=" + new Date()*1;
            },
            login(){
                let _this = this;
                let message = this.$message;
                this.loginForm.uuid = this.imageUuid;
                api.login(this.loginForm)
                    .then(data => {
                        if (data.code === 1){
                            _this.$store.commit('login',data.re);
                            let path = this.$route.query.redirect;
                            this.$router.replace({path: path === '/' || path === undefined ? '/admin' : path})
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
