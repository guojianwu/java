<template>
  <div>
    <div style="margin-bottom:10px;">
      <!-- <el-input  v-model="params.name" placeholder="请输入用户名" style="width:200px;"></el-input> -->
      <!-- <el-button  type="primary" icon="el-icon-search" @click="onSerch">搜索</el-button> -->
      <el-button type="primary" size="mini" @click="toPath('/menuadd')">新增菜单</el-button>
    </div>
    <div>
      <!--  default-expand-all -->
      <el-table
        :data="data"
        style="width: 100%;margin-bottom: 20px;"
        row-key="id"
        border
        :tree-props="{children: 'child', hasChildren: 'hasChildren'}"
      >
        <el-table-column type="index" width="50"></el-table-column>
        <el-table-column prop="name" label="名称" width="180"></el-table-column>
        <el-table-column prop="href" label="url" width="180"></el-table-column>
        <el-table-column prop="sort" label="排序"></el-table-column>
        <el-table-column prop="permission" label="资源标识"></el-table-column>
        <el-table-column prop="type" label="类型">
          <template slot-scope="scope">
            <div>{{scope.row.type==1?"菜单":"按钮"}}</div>
          </template>
        </el-table-column>
        <el-table-column prop="updateTime" label="操作" width="150px" align="center">
          <template slot-scope="scope">
            <div>
              <el-button type="primary" size="mini" @click="onEdit(scope.row.id)">编辑</el-button>
              <el-button type="danger" size="mini" @click="ondel(scope.row.id)">删除</el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      data: []
    };
  },
  mounted() {
    this.getPermission();
  },
  methods: {
    onEdit(id) {
      this.$router.push({
        path: "/menuadd",
        query:{
          id
        }
      });
    },
    ondel(id) {
      this.$confirm("确定删除该用户?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$axios
            .post("/permission/delete/"+id)
            .then(res => {
              var { code,msg } = res.data;
              if (code == 200) {
                this.getPermission();
                this.$message({
                  type: "success",
                  message: msg
                });
              }else{
                 this.$message.error(msg);
              }
            });
        })
        .catch(() => {});
    },
    toPath(path) {
      this.$router.push({
        path
      });
    },
    getPermission() {
      this.$axios.get("/permission/findAll").then(res => {
        var { code, data } = res.data;
        if (code == 200) {
          this.data = data[0].child;
        }
      });
    }
  },
  components: {}
};
</script>

<style scoped>
</style>
