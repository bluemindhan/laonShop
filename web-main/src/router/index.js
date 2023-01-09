import { createRouter, createWebHashHistory } from 'vue-router';
import HomeLayout from '../components/HomeLayout.vue';
import HomeView from '../views/home/HomeView.vue';
import AuthLayout from '../components/AuthLayout.vue';
import SignInView from '../views/auth/SignInView.vue';
import SignUpView from '../views/auth/SignUpView.vue';
import SellerLayout from '../components/SellerLayout.vue';
import SellerMainView from '../views/seller/SellerMainView.vue';
import SellerProductsView from '../views/seller/SellerProductsView.vue';
import SellerOrdersView from '../views/seller/SellerOrdersView.vue';
import SellerReportsView from '../views/seller/SellerReportsView.vue';
import SellerProductsNewView from '../views/seller/SellerProductsNewView.vue';
import SellerSettlementsView from '../views/seller/SellerSettlementsView.vue';
import UserLayout from '../components/UserLayout.vue';
import UserMainView from '../views/user/UserMainView.vue';
import UserProductDetailView from '../views/user/UserProductDetailView.vue';
import UserCartView from '../views/user/UserCartView.vue';

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
  {
    path: '/seller',
    name: 'SellerLayout',
    component: SellerLayout,
    redirect: '/seller',
    children: [
      {
        path: '/seller',  // Dashboard
        name: 'SellerMainView',
        component: SellerMainView,
      },
      {
        path: '/seller/products',  // 상품목록
        name: 'SellerProductsView',
        component: SellerProductsView,
      },
      {
        path: '/seller/orders',  // 주문내역
        name: 'SellerOrdersView',
        component: SellerOrdersView,
      },
      {
        path: '/seller/reports',  // 리포트
        name: 'SellerReportsView',
        component: SellerReportsView,
      },
      {
        path: '/seller/products/new',  // 상품등록
        name: 'SellerProductsNewView',
        component: SellerProductsNewView,
      },
      {
        path: '/seller/settlements',  // 정산
        name: 'SellerSettlementsView',
        component: SellerSettlementsView,
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
        path: '/user/product/:id',
        name: 'UserProductDetailView',
        component: UserProductDetailView,
      },
      {
        path: '/user/cart',
        name: 'UserCartView',
        component: UserCartView,
      },
    ],
  },
];
const router = createRouter( {
  history: createWebHashHistory(),
  routes,
})
export default router;
