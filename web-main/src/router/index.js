import { createRouter, createWebHashHistory } from 'vue-router';
import HomeLayout from '../components/HomeLayout.vue';
import HomeView from '../views/home/HomeView.vue';
import AuthLayout from '../components/AuthLayout.vue';
import SignInView from '../views/auth/SignInView.vue';
import SignUpView from '../views/auth/SignUpView.vue';
import SellerLayout from '../components/SellerLayout.vue';
import SellerProductsView from '../views/seller/SellerProductsView.vue';
import SellerProductsNewView from '../views/seller/SellerProductsNewView.vue';
import SellerProductDetailView from '../views/seller/SellerProductDetailView.vue';
import UserLayout from '../components/UserLayout.vue';
import UserMainView from '../views/user/UserMainView.vue';
import UserProductDetailView from '../views/user/UserProductDetailView.vue';
import UserCartView from '../views/user/UserCartView.vue';
import UserCheckoutView from '../views/user/UserCheckoutView.vue';
import ProfileView from '../views/home/ProfileView.vue';

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
      {
        path: '/profile',
        name: 'ProfileView',
        component: ProfileView,
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
  {
    path: '/seller',
    name: 'SellerLayout',
    component: SellerLayout,
    redirect: '/seller/products',
    children: [
      {
        path: '/seller/products',  // 상품목록
        name: 'SellerProductsView',
        component: SellerProductsView,
      },
      {
        path: '/seller/product/:id',  // 상품상세수정
        name: 'SellerProductDetailView',
        component: SellerProductDetailView,
      },
      {
        path: '/seller/products/new',  // 상품등록
        name: 'SellerProductsNewView',
        component: SellerProductsNewView,
      },
    ],
  },
  {
    path: '/user',
    name: 'UserLayout',
    component: UserLayout,
    redirect: '/user',
    children: [
      {
        path: '/user',
        name: 'UserMainView',
        component: UserMainView,
      },
      {
        path: '/user/items/:id',
        name: 'UserProductDetailView',
        component: UserProductDetailView,
      },
      {
        path: '/user/cart',
        name: 'UserCartView',
        component: UserCartView,
      },
      {
        path: '/user/checkout',
        name: 'UserCheckoutView',
        component: UserCheckoutView,
      }
    ],
  },
];
const router = createRouter( {
  history: createWebHashHistory(),
  routes,
})
export default router;
