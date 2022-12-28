import { createRouter, createWebHashHistory } from 'vue-router';
import HomeLayout from '../components/HomeLayout.vue';
import HomeView from '../views/home/HomeView.vue';
import AuthLayout from '../components/AuthLayout.vue';
import SignInView from '../views/auth/SignInView.vue';
import SignUpView from '../views/auth/SignUpView.vue';
const routes = [
  {
    path: '/',
    name: 'HomeLayout',
    component: HomeLayout,
    redirect: '/home',
    children: [
      {
        path: '/home',
        name: 'HomeView',
        component: HomeView,
      },
    ],
  },
  {
    path: '/auth',
    name: 'AuthLayout',
    component: AuthLayout,
    redirect: '/auth/signin',
    children: [
      {
        path: 'signin',
        name: 'SignInView',
        component: SignInView,
      },
      {
        path: 'signup',
        name: 'SignUpView',
        component: SignUpView,
      },
    ],
  },
];
const router = createRouter( {
  history: createWebHashHistory(),
  routes,
})
export default router;
