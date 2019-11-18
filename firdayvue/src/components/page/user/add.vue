<template>
  <div>
    <div>
      <span class="text">用户名：</span>
      <el-input v-model="params.username" placeholder="请输入用户名" style="width:300px;"></el-input>
    </div>
    <div>
      <span class="text">昵称：</span>
      <el-input v-model="params.nickname" placeholder="请输入用户名" style="width:300px;"></el-input>
    </div>
    <div>
      <span class="text">密码：</span>
      <el-input type="password" v-model="params.password" placeholder="请输入用户名" style="width:300px;"></el-input>
    </div>
    <div>
      <span class="text">确认密码：</span>
      <el-input type="password"  v-model="params.repassword" placeholder="请输入用户名" style="width:300px;"></el-input>
    </div>
    <div>
      <span class="text">电话：</span>
      <el-input v-model="params.phone" placeholder="请输入用户名" style="width:300px;"></el-input>
    </div>
    <div>
      <span class="text">邮箱：</span>
      <el-input v-model="params.email" placeholder="请输入用户名" style="width:300px;"></el-input>
    </div>
    <div>
      <span class="text">性别：</span>
      <el-select style="width:300px;" v-model="params.sex" placeholder="请选择性别">
        <el-option
          v-for="item in sexList"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        ></el-option>
      </el-select>
    </div>
    <div>
      <span class="text">角色：</span>
      <el-select style="width:300px;" v-model="params.roleId" placeholder="请选择性别">
        <el-option v-for="item in roleList" :key="item.id" :label="item.name" :value="item.id"></el-option>
      </el-select>
    </div>
    <div>
      <span class="text">生日：</span>
      <el-date-picker
        v-model="params.birthday"
        value-format="yyyy-MM-dd"
        type="date"
        placeholder="选择日期"
        style="width:300px;"
      ></el-date-picker>
    </div>

    <div style="margin-bottom:10px;">
      <el-button type="primary" size="mini" @click="onSave">保存</el-button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      sexList: [
        {
          value: 1,
          label: "男"
        },
        {
          value: 0,
          label: "女"
        }
      ],
      roleList: [],
      params: {
        id:"",
        username: "",
        password: "",
        repassword: "",
        nickname: "",
        phone: "",
        email: "",
        birthday: "",
        sex: "",
        roleId: ""
      }
    };
  },
  mounted() {
    var id=this.$route.query.id||"";
    this.id=id;
    if(id){
      this.userData(id);
    }
    this.getRoleList();
  },
  methods: {
    userData(id){
      this.$axios.get("/user/"+id).then(res => {
        var { code,data } = res.data;
        if (code == 200) {
          this.params=data;
        }
      });
    },
    onSave() {
      var params=this.params;
      if(params.password!=params.repassword){
         this.$message.error('两次密码输入不一致');
        return false;
      }
      this.$axios.post("/user/save",this.params).then(res => {
        var { code } = res.data;
        if (code == 200) {
          this.$router.go(-1);
        }
      });
    },
    getRoleList() {
      this.$axios.get("/role/all").then(res => {
        var { code, datas } = res.data;
        if (code == 200) {
          this.roleList = datas;
        }
      });
    }
  },
  components: {}
};
</script>

<style lang="scss" scoped>
.text {
  width: 80px;
  display: inline-block;
  margin-bottom: 20px;
}
</style>
