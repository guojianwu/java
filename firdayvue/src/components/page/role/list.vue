<template>
  <div>
    <div style="margin-bottom:10px;">
       <el-input  v-model="params.name" placeholder="请输入用户名" style="width:200px;"></el-input>
       <el-button  type="primary" icon="el-icon-search" @click="onSerch">搜索</el-button>
      <el-button type="primary" size="mini" @click="toPath('/roleadd')">新增角色</el-button>
    </div>

    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column prop="name" label="角色" align="center"></el-table-column>
      <el-table-column prop="description" label="描述" align="center"></el-table-column>
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
            .post("/role/delete/"+id)
            .then(res => {
              var { code,msg } = res.data;
              if (code == 200) {
                this.getData();
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
    onEdit(id) {
      this.$router.push({
        path: "/roleadd",
        query: {
          id
        }
      });
    },
    getData() {
      this.$axios
        .get("/role/list", {
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
