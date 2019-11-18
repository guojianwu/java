<template>
<el-dialog title="新增属性" :visible.sync="dialogFormVisible">
  <el-form :model="form">
    <el-form-item label="属性名称" :label-width="formLabelWidth">
      <el-input v-model="form.key"></el-input>
    </el-form-item>
    <el-form-item label="搜索关键字（唯一）" :label-width="formLabelWidth">
      <el-input v-model="form.keyWord"></el-input>
    </el-form-item>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible(dialogFormVisible)">取 消</el-button>
    <el-button type="primary" @click="handleSave(dialogFormVisible,defFrom)">确 定</el-button>
  </div>
</el-dialog>
</template>

<script>
  export default {
    name:"AddTplDialog",
    data() {
      return {
        form: {
          id:"",
          key: '',
          type: "text",
          preset:[],
          presetEdit: true,
          keyWord:""
        },
        formLabelWidth: '180px'
      };
    },
    props:["dialogFormVisible"],
    watch:{
      // dialogVisible(val){
      //   console.dir(val)
      //   this.$emit("dialogFormVisible", !val)
      // }
    },
    methods:{
      // init(){
        // console.dir(this.dialogFormVisible)
      // },
      dialogVisible(val){
        // console.dir(val)
        const data = {
          state:!val
        }
        this.$emit("resData", data)
      },
      handleSave(val,callback){
        this.form.id = Date.parse(new Date()).toString()

        const data = {
          state:!val,
          data:this.form
        }
        this.$emit("resData", data)
        callback()
      },
      defFrom(){
        this.form = {
          id:"",
          key: '',
          type: "text",
          preset:[],
          presetEdit: true,
          keyWord:""
        }
      }
    },
    mounted(){
      // this.init()
    }
  };
</script>

<style>

</style>
