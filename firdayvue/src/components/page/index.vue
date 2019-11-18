<template>
    <div class="home">
       welcome,{{role}}
    </div>
</template>

<script>
    export default {
        name: 'home',
        data() {
            return {
              name: localStorage.getItem('ms_username'),
            }
        },
        components: {

        },
        computed: {
            role() {
                return this.name === 'admin' ? '超级管理员' : '普通用户';
            }
        },
        created(){

        },
        activated(){

        },
        deactivated(){

        },
        methods: {

        }
    }

</script>


<style scoped>
.home{
  padding:25px;
  font-size: 24px;
  text-align: center;
  min-height:200px;
  background: #fff;
}
</style>
