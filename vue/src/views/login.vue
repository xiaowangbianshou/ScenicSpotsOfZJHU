<template less>
  <div class="login-container">
  <div class="login-body">
    <div class="login-panel">
      <div class="login-logo">
        <span class="login-logo-i">
          I
        </span>
        <span class="login-logo-love">
            <svg viewBox="64 64 896 896" focusable="false" fill="currentColor" width="1em" height="1em" data-icon="heart" aria-hidden="true"><path d="M923 283.6a260.04 260.04 0 00-56.9-82.8 264.4 264.4 0 00-84-55.5A265.34 265.34 0 00679.7 125c-49.3 0-97.4 13.5-139.2 39-10 6.1-19.5 12.8-28.5 20.1-9-7.3-18.5-14-28.5-20.1-41.8-25.5-89.9-39-139.2-39-35.5 0-69.9 6.8-102.4 20.3-31.4 13-59.7 31.7-84 55.5a258.44 258.44 0 00-56.9 82.8c-13.9 32.3-21 66.6-21 101.9 0 33.3 6.8 68 20.3 103.3 11.3 29.5 27.5 60.1 48.2 91 32.8 48.9 77.9 99.9 133.9 151.6 92.8 85.7 184.7 144.9 188.6 147.3l23.7 15.2c10.5 6.7 24 6.7 34.5 0l23.7-15.2c3.9-2.5 95.7-61.6 188.6-147.3 56-51.7 101.1-102.7 133.9-151.6 20.7-30.9 37-61.5 48.2-91 13.5-35.3 20.3-70 20.3-103.3.1-35.3-7-69.6-20.9-101.9z"></path></svg>
        </span>
        <span class="login-logo-huzu">HUZU</span>
      </div>
    </div>
    <div  class="login-form" role="form" autocomplete="off">
      <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>管理员登录</b></div>
      <el-form :model="user" :rules="rules" ref="userForm" >
        <el-form-item prop="username">
          <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" v-model="user.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password v-model="user.password"></el-input>
        </el-form-item>
        <el-form-item style="margin: 10px 0; text-align: right">
          <el-button type="primary" size="medium"  autocomplete="off" @click="submitForm('userForm')">登录</el-button>
        </el-form-item>
    </el-form>
    </div>
  </div>/
  <div class="login-footer">
    <a href="http://beian.miit.gov.cn/" target="_blank"><span class="copyright">Copyright © 2021-{{year}} 湖州师范学院 版权所有 </span>ICP证：京ICP备15010239号-3</a>
  </div>
</div>

</template>

<script>
export default {
  name:"Login",
    data(){
        return{
            user:{},
            year:2023,
            rules: {
              username: [
                { required: true, message: '请输入用户名', trigger: 'blur' },
                { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
              ],
              password: [
                { required: true, message: '请输入密码', trigger: 'blur' },
                { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
              ]}
        }
    },
    methods:{
        //登录
        login(){
            // // // if (this.name.trim() == '' || this.password.trim() == '' ) return;
            // if(this.username==='admin'&&this.password==='123456'){
            //     localStorage.setItem('token','xxxxx')
            //     this.$router.push('/admin')
            // }else{
            //     alert('密码错误')
            //     localStorage.removeItem('token')
            // }
            this.$http.post("/admin/login",this.user).then(res=>{
                  if(res.code==="200"){
                    localStorage.setItem("user",JSON.stringify(res.data))
                    this.$router.push("/admin")
                    this.$message.success("登录成功")
                  }else{
                    this.$message.error(res.msg)
                  }
            })
        },
        submitForm(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              this.login()
            }
        });
      },
        forgetpwd(){
          console.log('修改密码');
        }
    }
}
</script>

<style lang="scss">
@import "../scss/login.component.scss"

</style>