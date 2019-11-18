<template>
  <div>
    <div>
      <span class="text">用户名：</span>
      <el-input v-model="params.name" placeholder="请输入用户名" style="width:300px;"></el-input>
    </div>
    <div>
      <span class="text">昵称：</span>
      <el-input v-model="params.description" placeholder="请输入用户名" style="width:300px;"></el-input>
    </div>
    <div>
      <el-tree
      
        @check="checkChange"
        :data="data"
        show-checkbox
        node-key="id"
        :props="defaultProps"
        :default-checked-keys="params.permissionIds"
        :default-expanded-keys="params.permissionIds"
      ></el-tree>
    </div>
    
<!--   
  
        :default-expand-all="true"
   :default-expanded-keys="[2, 3]"
        :default-checked-keys="[5]" -->
    <div style="margin-top:10px;">
      <el-button type="primary" size="mini" @click="onSave">{{params.id?"修改":"保存"}}</el-button>
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
        id: "",
        name: "",
        description: "",
        permissionIds:[]
      },

      data: [],
      defaultProps: {
        children: "child",
        label: "name"
      }
    };
  },
  mounted() {
    var id = this.$route.query.id || "";
    this.id = id;
    if (id) {
      this.roleData(id);
    }
    // this.getRoleList();
    this.getPermission();
  },
  methods: {
    checkChange(a,b){
      this.params.permissionIds=b.checkedKeys;
    },
    getPermission() {
      this.$axios.get("/permission/findAll").then(res => {
        var { code, data } = res.data;
        if (code == 200) {
          var root = [
            {
              name: "root",
              child: data
            }
          ];
          this.data = data;
        }
      });
    },
    roleData(id) {
      this.$axios.get("/role/getRoleById",{
        params:{
          id
        }
      }).then(res => {
        var { code, data } = res.data;
        if (code == 200) {
          this.params = data;
        }
      });
    },
    onSave() {
      this.$axios.post("/role/save", this.params).then(res => {
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
/deep/ .el-tree > div {
  // display: inline-block;
}
.text {
  width: 80px;
  display: inline-block;
  margin-bottom: 20px;
}
</style>
