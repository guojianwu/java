import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [
    {
      path: '/',
      redirect: '/home'
    },
    {
      path: '/',
      component: resolve = > require(['../components/common/Home.vue'], resolve),
    meta
:
{
  title: '用户管理'
}
,
children: [
  // {
  //     path: '/dashboard',
  //     component: resolve => require(['../components/page/com/Dashboard.vue'], resolve),
  //     meta: { title: '系统首页' }
  // },
  // {
  //     path: '/icon',
  //     component: resolve => require(['../components/page/com/Icon.vue'], resolve),
  //     meta: { title: '自定义图标' }
  // },
  // {
  //     path: '/table',
  //     component: resolve => require(['../components/page/com/BaseTable.vue'], resolve),
  //     meta: { title: '基础表格' }
  // },
  // {
  //     path: '/tabs',
  //     component: resolve => require(['../components/page/com/Tabs.vue'], resolve),
  //     meta: { title: 'tab选项卡' }
  // },
  // {
  //     path: '/form',
  //     component: resolve => require(['../components/page/com/BaseForm.vue'], resolve),
  //     meta: { title: '基本表单' }
  // },
  // {
  //     // 富文本编辑器组件
  //     path: '/editor',
  //     component: resolve => require(['../components/page/com/VueEditor.vue'], resolve),
  //     meta: { title: '富文本编辑器' }
  // },
  // {
  //     // markdown组件
  //     path: '/markdown',
  //     component: resolve => require(['../components/page/com/Markdown.vue'], resolve),
  //     meta: { title: 'markdown编辑器' }
  // },
  // {
  //     // 图片上传组件
  //     path: '/upload',
  //     component: resolve => require(['../components/page/com/Upload.vue'], resolve),
  //     meta: { title: '文件上传' }
  // },
  // {
  //     // vue-schart组件
  //     path: '/charts',
  //     component: resolve => require(['../components/page/com/BaseCharts.vue'], resolve),
  //     meta: { title: 'schart图表' }
  // },
  // {
  //     // 拖拽列表组件
  //     path: '/drag',
  //     component: resolve => require(['../components/page/com/DragList.vue'], resolve),
  //     meta: { title: '拖拽列表' }
  // },
  // {
  //     // 拖拽Dialog组件
  //     path: '/dialog',
  //     component: resolve => require(['../components/page/com/DragDialog.vue'], resolve),
  //     meta: { title: '拖拽弹框' }
  // },
  // {
  //     // 权限页面
  //     path: '/permission',
  //     component: resolve => require(['../components/page/com/Permission.vue'], resolve),
  //     meta: { title: '权限测试', permission: true }
  // },

  {
    path: '/home',
    component: resolve = > require(['../components/page/index.vue'], resolve),
  meta
:
{
  title: '首页'
}
},

{
  path: '/userlist',
    component
:
  resolve =
>
  require(['../components/page/user/userList.vue'], resolve),

    meta
:
  {
    title: '用户列表'
  }
}
,
{
  path: '/useradd',
    component
:
  resolve =
>
  require(['../components/page/user/add.vue'], resolve),

    meta
:
  {
    title: '新增用户'
  }
}
,

{
  path: '/rolelist',
    component
:
  resolve =
>
  require(['../components/page/role/list.vue'], resolve),

    meta
:
  {
    title: '角色列表'
  }
}
,

{
  path: '/roleadd',
    component
:
  resolve =
>
  require(['../components/page/role/add.vue'], resolve),

    meta
:
  {
    title: '角色列表'
  }
}
,

{
  path: '/menulist',
    component
:
  resolve =
>
  require(['../components/page/menu/list.vue'], resolve),

    meta
:
  {
    title: '角色列表'
  }
}
,
{
  path: '/menuadd',
    component
:
  resolve =
>
  require(['../components/page/menu/add.vue'], resolve),

    meta
:
  {
    title: '角色列表'
  }
}
,


{
  path: '/404',
    component
:
  resolve =
>
  require(['../components/page/404.vue'], resolve),
    meta
:
  {
    title: '404'
  }
}
,
{
  path: '/403',
    component
:
  resolve =
>
  require(['../components/page/403.vue'], resolve),
    meta
:
  {
    title: '403'
  }
}
,




]
},
{
  path: '/login',
    component
:
  resolve =
>
  require(['../components/page/Login.vue'], resolve),
    meta
:
  {
    unRequiresAuth: true
  }
}
,
{
  path: '*',
    redirect
:
  '/404'
}
]
})
