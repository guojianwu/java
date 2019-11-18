<template>
  <div class="el-pagination">
    <el-pagination
      background
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-size="pageSize"
      :layout="layout"
      :total="total"
      :pager-count="pagerCount"
      :small='small'
    ></el-pagination>
  </div>
</template>
<script>
export default {

  props: {
    small:{
      type:Boolean,
      default:false
    },
    pagerCount:{
      type:Number,
      default:7
    },
     layout:{
    type:String,
    default:"sizes,total, prev, pager, next, jumper"
  },
    currentPage: {
      type: Number,
      default: 1
    },
    pageSize: {
      type: Number,
      default: 20
    },
    total: {
      type: Number,
      default: 0
    }
  },
  data() {
    return {
      // currentPage: 1
    };
  },
  methods: {
    handleSizeChange(e) {
      this.$emit("eventSizeChange", e);
    },
    handleCurrentChange(e) {
      this.$emit("eventCurrentChange", e);
    }
  }
};
</script>
<style lang="scss" scoped>
.el-pagination {
  // position: fixed;
  // right: 50px;
  // bottom: 30px;
  text-align: right;
  margin: 8px 0;
}
</style>
