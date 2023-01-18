<!--
  This example requires some changes to your config:
  
  ```
  // tailwind.config.js
  module.exports = {
    // ...
    plugins: [
      // ...
      require('@tailwindcss/forms'),
    ],
  }
  ```
-->
<template>
  <div class="bg-white">
    <div class="mx-auto max-w-4xl py-16 px-4 sm:py-24 sm:px-6 lg:px-8">
      <h1 class="text-3xl font-bold tracking-tight text-gray-900">Shopping Cart</h1>

      <form class="mt-12">
        <div>
          <h2 class="sr-only">Items in your shopping cart</h2>

          <ul role="list" class="divide-y divide-gray-200 border-t border-b border-gray-200">
            <li v-for="(vo, productIdx) in volist" :key="vo.productNum" class="flex py-6 sm:py-10">
              <div class="flex-shrink-0">
                <img :src="vo.image" class="h-24 w-24 rounded-lg object-cover object-center sm:h-32 sm:w-32" />
              </div>

              <div class="relative ml-4 flex flex-1 flex-col justify-between sm:ml-6">
                <div>
                  <div class="flex justify-between sm:grid sm:grid-cols-2">
                    <div class="pr-6">
                      <h3 class="text-sm">
                        <a  class="font-medium text-gray-700 hover:text-gray-800">{{ vo.productName }}</a>
                      </h3>
                      <!-- <p class="mt-1 text-sm text-gray-500">{{ product.color }}</p>
                      <p v-if="product.size" class="mt-1 text-sm text-gray-500">{{ product.size }}</p> -->
                    </div>

                    <p class="text-right text-sm font-medium text-gray-900">{{ vo.price }} 원</p>
                  </div>

                  <div class="mt-4 flex items-center sm:absolute sm:top-0 sm:left-1/2 sm:mt-0 sm:block">
                    <label :for="`quantity-${productIdx}`" class="sr-only">Quantity, {{ vo.productName }}</label>
                    <select :id="`quantity-${productIdx}`" :name="`quantity-${productIdx}`" :value="vo.cnt" class="block max-w-full rounded-md border border-gray-300 py-1.5 text-left text-base font-medium leading-5 text-gray-700 shadow-sm focus:border-indigo-500 focus:outline-none focus:ring-1 focus:ring-indigo-500 sm:text-sm">
                      <option value="1">1</option>
                      <option value="2">2</option>
                      <option value="3">3</option>
                      <option value="4">4</option>
                      <option value="5">5</option>
                      <option value="6">6</option>
                      <option value="7">7</option>
                      <option value="8">8</option>
                      <option value="9">9</option>
                      <option value="10">10</option>
                    </select>

                    <button type="button" @click="deleteCart" :value="vo.productNum" class="ml-4 text-sm font-medium text-indigo-600 hover:text-indigo-500 sm:ml-0 sm:mt-3">
                      <span>Remove</span>
                    </button>
                  </div>
                </div>

                
              </div>
            </li>
          </ul>
        </div>

        <!-- Order summary -->
        <div class="mt-10 sm:ml-32 sm:pl-6">
          <div class="rounded-lg bg-gray-50 px-4 py-6 sm:p-6 lg:p-8">
            <h2 class="sr-only">Order summary</h2>

            <div class="flow-root">
              <dl class="-my-4 divide-y divide-gray-200 text-sm">
                <div class="flex items-center justify-between py-4">
                  <dt class="text-base font-medium text-gray-900">Order total</dt>
                  <dd class="text-base font-medium text-gray-900">$112.32</dd>
                </div>
              </dl>
            </div>
          </div>
          <div class="mt-10">
            <button type="submit" class="w-full rounded-md border border-transparent bg-indigo-600 py-3 px-4 text-base font-medium text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 focus:ring-offset-gray-50">Checkout</button>
          </div>

          <div class="mt-6 text-center text-sm text-gray-500">
            <p>
              or
              <a href="#" class="font-medium text-indigo-600 hover:text-indigo-500">
                Continue Shopping
                <span aria-hidden="true"> &rarr;</span>
              </a>
            </p>
          </div>
        </div>
      </form>
    </div>
  </div>
  
</template>
<script>
import GetCartRequest from "@/service/request/GetCartRequest.js";
import {mapGetters, mapMutations} from "vuex";
import ResultCode from "@/service/ResultCode";
import DeleteCartRequest from "@/service/request/DeleteCartRequest.js";

export default {
  name: 'UserCartView',
  components: {
  },
  props: {
  },
  data() {
    return {
      id: this.$route.params.id,
      volist : [],
    }
  },
  computed: {
    ...mapGetters({
      accessToken: "appStore/accessToken",
      refreshToken: "appStore/refreshToken",
    }),
  },
  watch: {
    accessToken: function (val) {
      console.log("accessToken changed..", val);
      this.api.setAccessToken(val);
    },
    refreshToken: function (val) {
      console.log("refreshToken changed..", val);
      this.api.setRefreshToken(val);
    }
  },
  methods: {
    ...mapMutations({
      setAccessToken: "appStore/accessToken",
      setRefreshToken: "appStore/refreshToken",
      setUserInfo: "appStore/userInfo",
    }),
    async getCartList() {
      let req = new GetCartRequest();

      try {
        let res = await this.api.getCartList(req);
        if (res.code === ResultCode.Success) {
          this.volist = res.volist;
          console.log(res);
        }
      } catch (e) {
        console.error(e);
      }
    },
    async deleteCart() {
      let req = new DeleteCartRequest();
      req.productNum = this.id;
      console.log(req);
      try {
        let res = await this.api.deleteCart(req);
        if (res.code === ResultCode.Success) {
          console.log(res);
        } else {
          alert(res.message);
        }
      } catch (e) {
        console.error(e);
      }
    }
  },
  created() {
    console.log("UserMainView.vue..", this.accessToken);
    if (this.accessToken == null || this.accessToken == "") {
      /**
       * accessToken이 없으면 로그인 페이지로 이동한다.
       */
      this.$router.replace({name: "SignInView"});
    } else {
      /**
       * accessToken이 있으면 프로필 정보를 가져온다.
       */
      this.api.setAccessToken(this.accessToken);
      this.getCartList();
    }  
  },
  mounted() {
  },
  beforeUnmount() {
  }
}
</script>
<script setup>
import { CheckIcon, ClockIcon, QuestionMarkCircleIcon, XMarkIcon } from '@heroicons/vue/20/solid'

// const products = [
//   {
//     id: 1,
//     name: 'Basic Tee',
//     href: '#',
//     price: '$32.00',
//     color: 'Sienna',
//     inStock: true,
//     size: 'Large',
//     imageSrc: 'https://tailwindui.com/img/ecommerce-images/shopping-cart-page-01-product-01.jpg',
//     imageAlt: "Front of men's Basic Tee in sienna.",
//   },
//   {
//     id: 2,
//     name: 'Basic Tee',
//     href: '#',
//     price: '$32.00',
//     color: 'Black',
//     inStock: false,
//     leadTime: '3–4 weeks',
//     size: 'Large',
//     imageSrc: 'https://tailwindui.com/img/ecommerce-images/shopping-cart-page-01-product-02.jpg',
//     imageAlt: "Front of men's Basic Tee in black.",
//   },
//   {
//     id: 3,
//     name: 'Nomad Tumbler',
//     href: '#',
//     price: '$35.00',
//     color: 'White',
//     inStock: true,
//     imageSrc: 'https://tailwindui.com/img/ecommerce-images/shopping-cart-page-01-product-03.jpg',
//     imageAlt: 'Insulated bottle with white base and black snap lid.',
//   },
// ]
</script>
<style>
</style>