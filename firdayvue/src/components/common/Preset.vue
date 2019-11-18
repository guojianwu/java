<template>
  <div :class="'el-input--' + size">
    <div class="tabs">
      <span
        class="tab"
        v-for="(item, index) in scope.row.preset"
        @click="delTag(scope, index)"
        :key="index">{{item}}<i class="close el-icon-close"></i></span>
      <el-input
        v-if="scope.row.presetEdit"
        placeholder="请输入内容，回车保存"
        v-model.trim="tags"
        class="input__inner"
        @keyup.enter.native="handleEnter(scope, clearVal)"
        clearable>
      </el-input>
      <!-- <input type="text" value="" class="input__inner" placeholder="请输入内容，回车保存"> -->
    </div>

  </div>
</template>

<script>
    export default {
      name:"Preset",
      data() {
        return {
            // tagsList: []
            tags:""
        }
      },
      props:{
        scope: Object,
        size: String,
      },
      methods: {
        init(){
          // console.dir(this.scope)
          // console.dir(this.scope)
        },
        handleEnter(scope, callback){
          const tags = this.tags
          if(tags != ""){
            scope.row.preset.push(tags)
            callback()
          }else{
            this.$message.error('内容不能为空');
          }
        },
        clearVal(){
          this.$message.success('保存成功');
          this.tags = ""
        },
        delTag(scope, index){
          // 提示并提交修改
          this.$confirm('此操作将永久删除该预设, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            // 过滤相同id的选择
            scope.row.preset.splice(index, 1)
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

        }
      },
      mounted(){
        this.init()
      }
    }

</script>


<style scoped>
.tabs{
  /* // padding:5px; */
  border:1px solid #dcdcdc;
  border-radius: 3px;
}
.tab{
  position: relative;
  overflow: hidden;
  display: inline-block;
  padding: 1px 10px;
  margin: 2px;
  vertical-align: middle;
  color: #999;
  border: 1px solid #dcdcdc;
  background: #f6f6f6;
}
.tab:hover{
  cursor:pointer;
  color:#F56C6C;
  border:1px solid #F56C6C;
}
.tab .close{
  position: absolute;
  top:-20px;
  right:0;
  width:0;
  height:0;
  border-width:0px 16px 16px 0px;
  border-style:solid;
  border-color:transparent #F56C6C transparent transparent ;
}
.tab .close:before{
  color: #fff;
  font-size: 12px;
  position: absolute;
  right: -18px;
  top: -1px;
  transform: scale(0.7);
  transition:all 1s ease-in-out 0s;
}
.tab:hover .close{
  top:0;
}
.input__inner >>> input{
  border:none;
}

</style>
