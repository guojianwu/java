<template>
  <div>
    <div class="login_top_class">
      <div class="bgcenter">
        <img class="storeIcon_img" src="../../assets/img/EDSlogo.png" alt="">
      </div>
    </div>
    <div class="login-wrap">
        <div class="ms-login" v-if="login_status == 0">
            <div class="ms-title">登录供货商平台</div>
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0px" class="ms-content">
                <el-form-item prop="username">
                    <el-input v-model="ruleForm.username" placeholder="用户账号">
                        <el-button slot="prepend" class="iconfont icon-gerenzhongxin"></el-button>
                    </el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input type="password" placeholder="密码" v-model="ruleForm.password" @keyup.enter.native="submitForm('ruleForm')">
                        <el-button slot="prepend" class="iconfont icon-lock"></el-button>
                    </el-input>
                </el-form-item>
                <div class="login-btn">
                    <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
                </div>
                <p class="login-tips" @click="changePage(1)">忘记密码</p>
            </el-form>
        </div>
        <div class="ms-login" v-else-if="login_status == 1">
            <div class="ms-title">找回密码</div>
            <el-form :model="ruleForm2" :rules="rules" ref="ruleForm2" label-width="0px" class="ms-content">
                <el-form-item prop="username">
                    <el-input v-model="ruleForm2.username" placeholder="用户账号" @blur="getPhone(ruleForm2.username)">
                        <el-button slot="prepend" class="iconfont icon-gerenzhongxin"></el-button>
                    </el-input>
                </el-form-item>
                <el-form-item prop="phone">
                    <el-input placeholder="已绑定的手机号" v-model="ruleForm2.phone" :disabled="true">
                        <el-button slot="prepend" class="iconfont icon-lock"></el-button>
                    </el-input>
                </el-form-item>
                <el-form-item prop="code">
                    <el-input type="nember" placeholder="请输入手机验证码" v-model="ruleForm2.code" @keyup.enter.native="findPassword('ruleForm2')">
                        <el-button slot="prepend" class="iconfont icon-renzheng"></el-button>
                        <span slot="append" class="v_code" @click="getCode(ruleForm2.username)">获取验证码</span>
                    </el-input>
                </el-form-item>
                <div class="login-btn">
                    <el-button type="primary" @click="findPassword('ruleForm2')">确认</el-button>
                </div>
                <p class="login-tips" @click="changePage(0)">返回登录</p>
            </el-form>
        </div>
        <div class="ms-login" v-else>
            <div class="ms-title">新密码</div>
            <el-form :model="ruleForm3" :rules="rules" ref="ruleForm3" label-width="0px" class="ms-content">
              <el-form-item>
                <span v-model="ruleForm3.username" class="account_name">账号：{{ruleForm3.username}}</span>
              </el-form-item>
              <el-form-item prop="newPassword">
                  <el-input type="password" v-model="ruleForm3.newPassword" placeholder="请输入新密码">
                      <el-button slot="prepend" class="iconfont icon-lock"></el-button>
                  </el-input>
              </el-form-item>
              <el-form-item prop="okPassword">
                  <el-input type="password" placeholder="再次输入新密码" v-model="ruleForm3.okPassword" @keyup.enter.native="setPassword('ruleForm3')">
                      <el-button slot="prepend" class="iconfont icon-lock"></el-button>
                  </el-input>
              </el-form-item>
              <div class="login-btn">
                  <el-button type="primary" @click="setPassword('ruleForm3')">确认</el-button>
              </div>
              <p class="login-tips" @click="changePage(0)">返回登录</p>
            </el-form>
        </div>
    </div>
    <div id="footer">
      <div class="footer_bg">
        <div class="footer_box">
          <div class="footer_title">EDS供货商平台</div>
          <ul>
            <li>EDS会员商城</li>
            <li>店小二后台管理系统</li>
            <li>易掌管订货系统</li>
            <li>E淘分销管理系统</li>
            <li>EDS分销管理系统</li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
        data: function(){
            return {
                login_status:0,
                ruleForm: {
                    username: 'admin',
                    password: '123456'
                },
                ruleForm2: {
                    username: '',
                    phone: '',
                    code: ''
                },
                ruleForm3: {
                    username: '',
                    code:'',
                    newPassword: '',
                    okPassword: ''
                },
                rules: {
                    username: [
                        { required: true, message: '请输入用户名', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' }
                    ],
//                  phone: [
//                      { required: true, message: '请输入手机号码', trigger: 'blur' },
//                      { pattern: /^[1]([3-9])[0-9]{9}$/,message: '请输入正确的手机号码',trigger: 'blur'}
//                  ],
                    code: [
                        { required: true, message: '请输入验证码', trigger: 'blur' }
                    ],
                    newPassword: [
                        { required: true, message: '请输入密码', trigger: 'blur' }
                    ],
                    okPassword: [
                        { required: true, message: '请输入密码', trigger: 'blur' }
                    ],
                }
            }
        },
        methods: {
            submitForm(formName) {

                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$axios.post('/login',{
                          username:this.ruleForm.username,
                          password:this.ruleForm.password
                        }).then((res)=>{
                          if(res.data.code==200){
                            var data=res.data.data;
                            this.$axios.defaults.headers.common['token'] ="Bearer "+ data.token;
                            localStorage.setItem('info',JSON.stringify(data));
                            this.$router.push('/');
                          }else{
                            this.$message.error(res.data.msg);
                          }
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            setPassword(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                      if(this.ruleForm3.newPassword!=this.ruleForm3.okPassword){
                        this.$message.error("新密码与再次输入的新密码不一致");
                        return;
                      }
                        this.$axios.post(this.HOST+'/account/resetPwd/forget',{
                          account:this.ruleForm3.username,
                          code:this.ruleForm3.code,
                          newPwd:this.ruleForm3.newPassword,
                          reNewPwd:this.ruleForm3.okPassword,
                        }).then((res)=>{
                          if(res.data.code==200){
                            this.$alert('修改成功, 去登录', '提示', {
                              type: 'success'
                            }).then(() => {
                              this.login_status=0;
                            }).catch(() => {
                              this.$message({
                                type: 'info',
                                message: '已取消'
                              });
                            });
                          }else{
                            this.$message.error(res.data.message);
                          }
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            findPassword(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$axios.post(this.HOST+'/account/resetPwd/verify',{
                          account:this.ruleForm2.username,
                          code:this.ruleForm2.code
                        }).then((res)=>{
                          if(res.data.code==200){
                            this.login_status=2;
                            this.ruleForm3.username=res.data.data.account;
                            this.ruleForm3.code=res.data.data.code;
                          }else{
                            this.$message.error(res.data.message);
                          }
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            changePage(status){
              this.login_status=status;
            },
            getPhone(account){
              this.$axios.post(this.HOST+'/account/phone',{
                account:account
              }).then((res)=>{
                if(res.data.code==200){
                    this.ruleForm2.phone=res.data.data.phone;
               }
              })
            },
            getCode(account){
              if(account){
                this.$axios.post(this.HOST+'/account/resetPwd/verifyCode',{
                  account:account
                }).then((res)=>{
                  this.$message.success('发送成功');
                }) 
              }else{
                this.$message.error('请输入用户账号');
              }
            
            }
        }
    }
</script>

<style scoped>
  .account_name{
    color: #fff;
  }
  .v_code{
    color: #14A4FF;
    cursor: pointer;
  }
  .copy_box a{
    color: #14A3FF;
  }
  .copy_box{
    font-size: 14px;
    color: #BBBBBB;
  }
  .footer_box ul{
    height: 30px;
  }
  .footer_box li:first-child{
    border: none;
  }
  .footer_box li{
    font-size:16px;
    color:#3B3B3B;
    float: left;
    padding: 0 31px;
    border-left: 1px solid #3B3B3B;
  }
  .footer_title{
      font-size: 30px;
      font-weight: bold;
      color: rgba(59,59,59,1);
      padding: 53px 0 30px 0; 
  }
  .footer_bg .footer_box {
        width: 922px;
        height: 250px;
        margin: 0 auto;
        text-align: center;
    }
    .footer_bg {
        width: 100%;
        height: 350px;
        margin: 0 auto;
    }
    .login_top_class {
        height: 130px;
    }
    .login_top_class .bgcenter {
/*        width: 1175px;*/
        height: 72px;
        /*margin: 0 auto;
        padding: 23px 10px;*/
       position: relative;
    }
    .login_top_class .bgcenter img{
        position: absolute;
        top: 33px;
        left: 366px;
    }
    .login-wrap{
        position: relative;
        width:100%;
        height:100%;
        background-image: url(../../assets/img/login-bg.png);
/*        background-size: 100%;*/
        height: 600px;
    }
    .ms-title{
        width:100%;
        line-height: 50px;
        text-align: center;
        font-size:20px;
        color: #fff;
        border-bottom: 1px solid #ddd;
    }
    .ms-login{
        position: absolute;
        left:70%;
        top:50%;
        width:350px;
        margin:-190px 0 0 -175px;
        border-radius: 5px;
        background: rgba(255,255,255, 0.3);
        overflow: hidden;
    }
    .ms-content{
        padding: 30px 30px;
    }
    .login-btn{
        text-align: center;
    }
    .login-btn button{
        width:100%;
        height:36px;
        margin-bottom: 10px;
    }
    .login-tips{
        font-size:12px;
        line-height:30px;
        color:#fff;
        float: right;
        margin-bottom: 40px;
        cursor: pointer;
    }
    #app{
        overflow: initial !important;
    }
</style>