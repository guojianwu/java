<template>
    <section class="main">
        <div class="crumbs">
            <el-breadcrumb separator="/template">
                <el-breadcrumb-item>商品管理</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <!-- 搜索组件 -->
            <div class="drag-box">
              <search-template :params="searchParams"></search-template>
            </div>

            <el-table
              :data="tableData"
              stripe
              :height="tableHeight"
              style="width: 100%">
              <el-table-column
                fixed="left"
                v-slot="scope"
                label="商品图片"
                width="80">
                <img
                  class="pro-img"
                  :src="scope.row.imgUrl + '?x-oss-process=image/resize,h_60,w_60'" alt="">
                </el-table-column>
              <el-table-column
                fixed="left"
                label="商品名称"
                prop="productName">
                </el-table-column>
              <el-table-column
                label="商品编号"
                prop="productCode"
                width="180">
                </el-table-column>
              <el-table-column
                prop="price"
                label="价格"
                width="100">
                </el-table-column>
              <el-table-column
                prop="store"
                label="库存"
                width="80">
                </el-table-column>
              <el-table-column
                prop="addDate"
                label="添加日期"
                width="100">
                </el-table-column>
              <el-table-column
                v-slot="scope"
                label="操作"
                fixed="right"
                width="130">
                <el-button
                  v-if="scope.row.edit"
                  size="small"
                  type="primary"
                  icon="el-icon-edit"
                  @click="handleEdit(scope)"></el-button>
                <el-button
                  v-if="scope.row.edit"
                  size="small"
                  type="danger"
                  icon="el-icon-delete"
                  @click="handleDelete(scope)"></el-button>
                </el-table-column>

            </el-table>
            <div class="page-box">
              <el-pagination
                @size-change="handlePageSizeChange"
                @current-change="handlePageCurrentChange"
                :current-page.sync="currentPage"
                :page-size="100"
                layout="total, sizes, prev, pager, next, jumper"
                :total="1000">
              </el-pagination>
            </div>
            <!-- <div class="add-btn">
              <el-button
                size="medium"
                type="primary"
                @click="handleAdd()">新增</el-button>
            </div> -->
        </div>
        <!-- <template> -->
        <!-- <add-tpl-dialog :dialogFormVisible="dialogFormVisible" @resData="dialogVisible"></add-tpl-dialog> -->
        <!-- </template> -->
    </section>
</template>

<script>
  import searchTemplate from '@/components/common/search.vue'

  export default {
      name: 'draglist',
      data() {
          return {
            tableData: [],
            tableHeight: 400,
            currentPage:5,
            searchParams:{
              name: "ddd"
            }
          }
      },
      components:{
          searchTemplate
      },
      mounted(){
        this.init()
      },
      // beforeCreate(){
      //   this.tableHeight = '700'
      // },
      created(){
        this.tableHeight = document.body.scrollHeight * 0.7
        this.windowListen();
        // let a = document.getElementsByClassName("header").offsetHeight
        // console.dir(a)
      },
      methods: {
        windowListen(){
          window.onresize = () => {
            this.tableHeight = document.body.scrollHeight * 0.7 // 初始化表单高度
            // console.dir(document.body.scrollHeight + '-' + document.body.clientHeight)
          }
        },
        init(){
          const $e = this
          $e.$axios.get('/api/protpl.json')
          .then((data) => {
            const res = data.data
            // console.dir(res.tableData)
            $e.tableData = res.tableData
            // $e.data = res
            // if(!$e.radio){
              // 赋默认值
              // const list = res.tableData.filter(res => res.id == $e.radio)
              // $e.tableData = list
            // }

          })
        },
        handlePageSizeChange(val) {
          console.log(`每页 ${val} 条`);
        },
        handlePageCurrentChange(val) {
          console.log(`当前页: ${val}`);
        },
        handleEdit(scope){
          console.dir('编辑')
        },
        handleDelete(scope){
          console.log('删除')
        }
      }
  }

</script>

<style scoped>
.pro-img{
  width: 40px;
  height:40px;
}
.page-box{
  margin-top:25px;
  text-align: center
}
/* .table >>> tbody{
  height:600px;
} */
</style>
