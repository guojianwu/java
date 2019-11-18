<template>
  <div id="app" v-cloak>
      <router-view></router-view>
  </div>
</template>

<style>
@import "./assets/css/main.css";
@import "./assets/fonts/iconfont.css";
@import "./assets/css/color-dark.css";     深色主题
/* @import "./assets/css/theme-green/color-green.css";   浅绿色主题 */
[v-cloak]{
  display: none !important;
}
body::-webkit-scrollbar {
  display: none;
}
::-webkit-scrollbar {
  width: 8px;
  height: 8px;
  background-color: #fff;
  /* display: none; */
}
/*定义滚动条轨道 内阴影+圆角*/

::-webkit-scrollbar-track {
  /* // -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
  // border-radius: 10px; */
  background-color: #fff;
}
/*定义滑块 内阴影+圆角*/

::-webkit-scrollbar-thumb {
  /* // border-radius: 10px;
  // -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,.3); */
  background-color: #a9a9a9;
}
</style>
