<template>
  <div class="alert">
    <div class="conver-box"  @click="onShow(true)" :style="{height:height+'px'}">
      <img :src="image?image+'?x-oss-process=image/resize,h_200,w_200':(showDefaultImg?'/static/imgs/default_img.png':'')" class="conver">
    </div>
    <div class="alert-wrap" v-if="show">
      <div class="mask" @click="onShow(false)"></div>
      <div class="mycontent">
        <img src="../../../static/imgs/close.png" class="close" @click="onShow(false)">
        <img :src="image?image:(showDefaultImg?'/static/imgs/default_img.png':'')" class="alert-image" />
      </div>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    height:{
      type:String,
      default:"30"
    },
    image: {
      type: String,
      default: ''
    },
    showDefaultImg:{
      type:Boolean,
      default:false
    }
  },
  data() {
    return {
      show: false
    }
  },
  methods: {
    onShow(is) {
      if(this.image || this.showDefaultImg){
        this.show = is || false;
      }

    }
  }
}
</script>
<style lang="scss" scoped>
.alert {
  .conver-box {
    height: 100px;
    cursor: -webkit-zoom-in;
  }
  .conver {
    max-width: 100%;
    max-height: 100%;

  }
  .alert-wrap {
    position: fixed;
    z-index: 5;
    top: 0;
    left: 0;
    .mask {
      position: fixed;
      top: 0;
      left: 0;
      background: #000;
      opacity: 0.6;
      width: 100vw;
      height: 100vh;
    }
    .mycontent {
      position: fixed;
      top: 50%;
      left: 50%; // height: 75vh;
      // width: 75vw;

      z-index: 6;
      transform: translate(-50%, -50%);
      // height: 80vh;
      // overflow: hidden;
      .close {
        position: absolute;
        right: -50px;
        top: -50px;
        cursor: pointer;
        border-radius: 50%;
      }
      .alert-image {
        // position: absolute;
        // top: 50%;
        // left: 50%;
        // transform: translate(-50%, -50%);
        max-width: 70vw;
        max-height: 70vh;
      }
    }
  }
}
</style>
