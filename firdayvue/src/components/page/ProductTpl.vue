<template>
    <section class="main">
        <div class="crumbs">
            <el-breadcrumb separator="/template">
                <el-breadcrumb-item>商品属性管理</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="drag-box type">
              <el-radio-group v-model.trim="radio" @change="handleChangeTpl(radio)" >
                <el-radio-button
                  v-for="(item, index) in tabList"
                  :label="item.id"
                  size="medium"
                  :key="index">{{item.name}}
                </el-radio-button>
              </el-radio-group>
            </div>
            <el-table
              :data="tableData"
              style="width: 100%">
              <el-table-column
                label="属性名称"
                v-slot="scope"
                width="180">
                <template>
                  <el-input
                    v-model="scope.row.key"
                    :disabled="!scope.row.presetEdit"
                    placeholder="请输入属性名称"
                    class="params-input" :ref="'params-input-' + scope.row.id"
                    @change.native="handleBlur(scope)" @keyup.enter.native="handleEnter(scope)">
                  </el-input>
                </template>
              </el-table-column>

              <el-table-column
                label="属性值类型"
                width="100"
                v-slot="scope">
                <template v-if="scope.row.presetEdit">
                  <el-select v-model="scope.row.type" placeholder="请选择属性值" @change="handleBlur(scope)">
                    <el-option
                      v-for="(item, index) in typeList"
                      :label="item.typeName"
                      :value="item.id"
                      :key="index"
                      ></el-option>
                  </el-select>
                </template>
                <span class="disblock-text" v-else>不可编辑</span>
              </el-table-column>

              <el-table-column
                label="接口模板"
                width="180"
                v-slot="scope">
                <template v-if="scope.row.presetEdit">
                  <el-select v-model="scope.row.api" placeholder="请选择接口" @change="handleBlurApi(scope)">
                    <el-option
                      v-for="(item,index) in apis"
                      :label="item.apiName"
                      :value="item.id"
                      :key="index"
                      ></el-option>
                  </el-select>
                </template>
                <span class="disblock-text" v-else>不可编辑</span>
              </el-table-column>

              <el-table-column
              label="预设值"
              v-slot="scope">
                <div>{{ apiListDeal(scope,apis) }}</div>
              </el-table-column>

              <el-table-column
                label="搜索关键字"
                width="100"
                v-slot="scope">
                <span class="disblock-text">{{scope.row.keyWord}}</span>
              </el-table-column>

              <el-table-column
                label="操作"
                width="190">
                <template v-slot="scope">
                  <el-button
                    size="small"
                    type="danger"
                    v-if="scope.row.presetEdit"
                    @click="handleDelete(scope)">删除</el-button>
                </template>
              </el-table-column>
            </el-table>
            <div class="add-btn">
              <el-button
                size="medium"
                type="primary"
                @click="handleAdd()">新增</el-button>
            </div>
        </div>
        <template>
        <add-tpl-dialog :dialogFormVisible="dialogFormVisible" @resData="dialogVisible"></add-tpl-dialog>
        </template>
    </section>
</template>

<script>
  import draggable from 'vuedraggable'
  import AddTplDialog from '@/components/common/AddTplDialog.vue'
  export default {
      name: 'draglist',
      data() {
          return {
            // edit: false,
            radio: '',
            tabList: [],
            typeList:[],
            // apiList:[],
            apis:[],
            tableData: [],
            data: {},
            dialogFormVisible: false
          }
      },
      components:{
          draggable,
          AddTplDialog,
      },
      mounted(){
        this.init()
      },
      methods: {
        init(){
          const $e = this
          $e.$axios.get('/api/template.json')
          .then((data) => {
            const res = data.data
            $e.data = res
            if(!$e.radio){
              // 赋默认值
              $e.tabList = res.tabList
              $e.typeList = res.typeList
              $e.apis = res.apis
              $e.radio = $e.tabList[0].id
              const list = res.tableData.filter(res => res.id == $e.radio)
              $e.tableData = list[0].tpl
            }

          })
        },
        handleChangeTpl(params) {
          const $e = this
          const res = $e.data.tableData
          const list = res.filter(res => res.id == params)
          $e.tableData = list[0].tpl
        },
        // handleSave(scope) {
        //   let index = scope.$index
        //   const row = scope.row
        //   // row.edit = !row.edit
        //   this.edit = !this.edit
        //   // console.dir(scope)
        //   // 提交修改
        // },
        // handleEdit(scope) {
        //   let index = scope.$index
        //   const row = scope.row
        //   // console.dir(index)
        //   // row.edit = !row.edit
        //   this.edit = !this.edit
        // },
        handleBlur(scope){
          let index = scope.$index
          const row = scope.row
          let id = scope.row.id

          // let oldKey = row.key
          // 提交保存并提示
          if(row.key != ''){
            this.$message.success('保存成功');
          }else{
            this.$message.error('内容不能为空,保存失败');
          }

        },
        handleBlurApi(scope){
          // 改变接口模板
          this.$message.success('保存成功');
        },
        handleEnter(scope){
          // 回车失焦
          const id = scope.row.id

          this.$refs["params-input-" + id].$el.querySelector('input').blur()
        },
        handleAdd(){
          const $e = this
          $e.dialogFormVisible = true
        },
        handleDelete(scope) {
          let index = scope.$index
          const row = scope.row
          let id = scope.row.id
          // 提示并提交修改
          this.$confirm('此操作将永久删除该属性, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            // 过滤相同id的选择
            this.tableData = this.tableData.filter(t => t.id != id)
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });
          });
        },
        dialogVisible(res){
          // 接收子组件参数并赋值
          this.dialogFormVisible = res.state
          if(!!res.data){
            // 插值
            this.tableData.push(res.data)
            // console.dir(res.data)
          }
        },
        apiListDeal(scope, apis){
          let apiList = '';
          const apiId = scope.row.api
          apis.forEach(ele => {
            if(apiId == ele.id){
              apiList = ele.preset.join(',')
            }
          });
          return apiList
        },
      }
  }

</script>

<style scoped>
    .drag-box{
        display: flex;
        user-select: none;
    }
    .drag-box-item {
        flex: 1;
        max-width: 330px;
        min-width: 300px;
        background-color: #eff1f5;
        margin-right: 16px;
        border-radius: 6px;
        border: 1px #e1e4e8 solid;
    }
    .item-title{
        padding: 8px 8px 8px 12px;
        font-size: 14px;
        line-height: 1.5;
        color: #24292e;
        font-weight: 600;
    }
    .item-ul{
        padding: 0 8px 8px;
        height: 500px;
        overflow-y: scroll;
    }
    .item-ul::-webkit-scrollbar{
        width: 0;
    }
    .drag-list {
        border: 1px #e1e4e8 solid;
        padding: 10px;
        margin: 5px 0 10px;
        list-style: none;
        background-color: #fff;
        border-radius: 6px;
        cursor: pointer;
        -webkit-transition: border .3s ease-in;
        transition: border .3s ease-in;
    }
    .drag-list:hover {
        border: 1px solid #20a0ff;
    }
    .drag-title {
        font-weight: 400;
        line-height: 25px;
        margin: 10px 0;
        font-size: 22px;
        color: #1f2f3d;
    }
    .ghost-style{
        display: block;
        color: transparent;
        border-style: dashed
    }
    .type{
      margin-bottom:15px;
    }
    /* .el-button{
      margin-left:10px;
    } */
    .el-input.is-disabled .el-input__inner{
      color:#999;
      background: #000
    }
    .params-input >>> input{
      color:#999;
      background: #f3f3f3
    }
    .params-input >>> input:focus{
      color:#000;
      background: #fff
    }
    .add-btn{
      margin-top:15px;
      text-align: center;
    }
    .disblock-text{
      color:#999;
    }
</style>
