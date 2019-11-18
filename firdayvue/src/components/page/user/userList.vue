<template>
  <div>
    <div style="margin-bottom:10px;">
       <el-input  v-model="params.name" placeholder="请输入用户名" style="width:200px;"></el-input>
       <el-button  type="primary" icon="el-icon-search" @click="onSerch">搜索</el-button>
      <el-button type="primary" size="mini" @click="toPath('/useradd')">新增用户</el-button>
    </div>

    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column prop="username" label="用户名" align="center"></el-table-column>
      <el-table-column prop="nickname" label="昵称" align="center"></el-table-column>
      <el-table-column prop="headImgUrl" label="头像" align="center"></el-table-column>
      <el-table-column prop="phone" label="电话" align="center"></el-table-column>
      <!-- <el-table-column prop="telephone" label="地址"></el-table-column> -->
      <el-table-column prop="email" label="邮件" align="center"></el-table-column>
      <el-table-column prop="birthday" label="生日" align="center"></el-table-column>
      <el-table-column prop="sex" label="性别" width="50px" align="center">
        <template slot-scope="scope">
          <p>{{scope.row.sex==1?"男":"女"}}</p>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间" align="center"></el-table-column>
      <el-table-column prop="updateTime" label="更新时间" align="center"></el-table-column>
      <el-table-column prop="updateTime" label="操作" width="150px" align="center">
        <template slot-scope="scope">
          <div>
            <el-button type="primary" size="mini" @click="onEdit(scope.row.id)">编辑</el-button>
            <el-button type="danger" size="mini" @click="ondel(scope.row.id)">删除</el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>
    <pagination
      @eventSizeChange="eventSizeChange"
      @eventCurrentChange="eventCurrentChange"
      :total="total"
      :currentPage="params.pageNum"
      :pageSize="params.pageSize"
    ></pagination>
  </div>
</template>

<script>
import pagination from "@/components/common/pagination";
export default {
  data() {
    return {
      total: 0,
      params: {
        pageNum: 1,
        pageSize: 10,
        name:""
      },
      tableData: []
    };
  },
  mounted() {
    this.getData();
  },
  methods: {
    onSerch(){
      this.params.pageNum=1;
      this.getData();
    },
    toPath(path) {
      this.$router.push({
        path
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
            .post("/user/delete", {
              id
            })
            .then(res => {
              var { code } = res.data;
              if (code == 200) {
                this.getData();
                this.$message({
                  type: "success",
                  message: "删除成功!"
                });
              }else{
                 this.$message.error('删除失败');
              }
            });
        })
        .catch(() => {});
    },
    onEdit(id) {
      this.$router.push({
        path: "/useradd",
        query: {
          id
        }
      });
    },
    getData() {
      this.$axios
        .get("/user/list", {
          params: this.params
        })
        .then(res => {
          var { code, datas, count } = res.data;
          if (code == 200) {
            this.total = count;
            this.tableData = datas;
          }
        });
    },
    eventSizeChange(e) {
      this.params.pageSize = e;
      this.params.pageNum = 1;
      this.getData();
    },
    eventCurrentChange(e) {
      this.params.pageNum = e;
      this.getData();
    }
  },
  components: {
    pagination
  }
};
</script>

<style scoped>
</style>
