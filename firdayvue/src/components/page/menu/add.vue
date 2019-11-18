<template>
  <div>
    <div>
      <span class="text">菜单名：</span>
      <el-input v-model="params.name" placeholder="请输入菜单名" style="width:300px;"></el-input>
    </div>
    <div>
      <span class="text">样式：</span>
      <el-input v-model="params.css" placeholder="请输入样式" style="width:300px;"></el-input>
    </div>
    <div>
      <span class="text">链接：</span>
      <el-input v-model="params.href" placeholder="请输入链接" style="width:300px;"></el-input>
    </div>
    <div>
      <span class="text">类型：</span>
      <el-select style="width:300px;" v-model="params.type" placeholder="请选择类型">
        <el-option
          v-for="item in menuType"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        ></el-option>
      </el-select>
    </div>
    <div>
      <span class="text">权限值：</span>
      <el-input v-model="params.permission" placeholder="请输入权限值" style="width:300px;"></el-input>
    </div>
    <div>
      <span class="text">排序：</span>
      <el-input v-model="params.sort" placeholder="请输入排序" style="width:300px;"></el-input>
    </div>
    <div class="tree">
      <span class="text">上级菜单：</span>
      <div class="tree-item">
        <el-tree
          ref="tree"
          @check="checkChange"
          :data="data"
          show-checkbox
          check-strictly
          node-key="id"
          :props="defaultProps"
          :default-checked-keys="params.permissionIds"
          :default-expanded-keys="params.permissionIds"
        ></el-tree>
        <!-- <el-tree
          :data="data"
          show-checkbox
          node-key="id"
          :props="defaultProps"
          check-strictly
          @check-change="handleClick"
          :default-checked-keys="params.permissionIds"
          ref="treeForm"
        ></el-tree>-->
      </div>
    </div>

    <!--   
  
        :default-expand-all="true"
   :default-expanded-keys="[2, 3]"
    :default-checked-keys="[5]"-->
    <div style="margin-top:10px;">
      <el-button type="primary" size="mini" @click="onSave">{{params.id?"修改":"保存"}}</el-button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      menuType: [
        {
          value: 1,
          label: "菜单"
        },
        {
          value: 2,
          label: "按钮"
        }
      ],
      roleList: [],
      params: {
        id: "",
        parentId: "",
        name: "",
        css: "",
        href: "",
        type: 1,
        permission: "",
        sort: "",

        permissionIds: []
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
    this.params.id = id;
    if (id) {
      this.permissionData(id);
    }
    this.getPermission();
  },
  methods: {
    handleClick(data, checked, node) {
      console.log(data, checked, node);
      this.$refs.treeForm.setCheckedNodes([]);
      this.$refs.treeForm.setCheckedNodes([data]);
      // this.i++;
      // if (this.i % 2 == 0) {
      //   if (checked) {
      //     this.$refs.treeForm.setCheckedNodes([]);
      //     this.$refs.treeForm.setCheckedNodes([data]);
      //     //交叉点击节点
      //   } else {
      //     this.$refs.treeForm.setCheckedNodes([]);
      //     //点击已经选中的节点，置空
      //   }
      // }
    },

    checkChange(data, checked, indeterminate) {
      let arr = [data.id];
      this.$refs.tree.setCheckedKeys(arr);
      this.params.permissionIds = arr;
    },
    getPermission() {
      this.$axios.get("/permission/findAll").then(res => {
        var { code, data } = res.data;
        if (code == 200) {
          this.data = data;
          if (this.params.id) {
            this.selectDisabled(this.data, this.params.id);
          }
        }
      });
    },
    selectDisabled(data, id) {
      if (!data || data.length == 0) {
        return false;
      }
      for (var i = 0; i < data.length; i++) {
        if (data[i].id == id) {
          data[i].disabled = true;
          this.disabledAll(data[i].child);
          return false;
        }
        this.selectDisabled(data[i].child, id);
      }
    },
    disabledAll(data) {
      if (!data || data.length == 0) {
        return false;
      }
      for (var i = 0; i < data.length; i++) {
        data[i].disabled = true;
         this.disabledAll(data[i].child);
      }
    },
    permissionData(id) {
      this.$axios
        .get("/permission/findById", {
          params: {
            id
          }
        })
        .then(res => {
          var { code, data } = res.data;
          if (code == 200) {
            this.params = data;
            this.params.permissionIds = [data.parentId];
          }
        });
    },
    onSave() {
      var params = this.params;
      params.parentId = params.permissionIds[0];
      console.log(params);

      this.$axios.post("/permission/save", this.params).then(res => {
        var { code,msg } = res.data;
        if (code == 200) {
          this.$router.go(-1);
        }else{
           this.$message.error(msg);
        }
      });
    },
  },
  components: {}
};
</script>

<style lang="scss" scoped>
/deep/ .el-tree > div {
  // display: inline-block;
}
.tree {
  display: flex;
  .text {
    width: 85px;
  }
  .tree-item {
    // flex: 1;
    width: 300px;
  }
}
.text {
  width: 80px;
  display: inline-block;
  margin-bottom: 20px;
}
</style>
