<template>
  <div>搜索组件</div>
</template>

<script>
export default {
  name:"searchTemplate",
  data(){
    return {

    }
  },
  props:["params"],
  mounted(){
    this.init()
  },
  methods:{
    init(){
      console.dir(this.params)
    }
  }

}
</script>

<style scope>

</style>
