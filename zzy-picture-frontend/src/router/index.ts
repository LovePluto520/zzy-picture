import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import HomePage from "@/pages/HomePage.vue";
import UserManagePage from "@/pages/admin/UserManagePage.vue";
import UserRegisterPage from "@/pages/user/UserRegisterPage.vue";
import UserLoginPage from "@/pages/user/UserLoginPage.vue";
import ACCESS_ENUM from "@/access/accessEnum.ts";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: '主页',
      component: HomePage,
      meta:{
        hideInMenu: false,
      },
    },
    {
      path: '/user/login',
      name: '用户登录',
      component: UserLoginPage,
      meta:{
        hideInMenu: true,
      },
    },
    {
      path: '/user/register',
      name: '用户注册',
      component: UserRegisterPage,
      meta:{
        hideInMenu: true,
      },
    },
    {
      path: '/admin/userManage',
      name: 'adminUserManage',
      component: UserManagePage,
      meta: {
        access: ACCESS_ENUM.ADMIN,
      },
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue'),
      meta: {
        hideInMenu: false,
      },
    }
  ],

})

export default router
