<!--<template>-->
<!--  <div class="bg-white">-->
<!--    <div class="mx-auto max-w-7xl py-16 px-4 sm:py-24 sm:px-6 lg:px-8">-->
<!--      <div class="text-center">-->
<!--        <p class="mt-1 text-4xl font-bold tracking-tight text-gray-900 sm:text-5xl lg:text-6xl">Laon Shop</p>-->
<!--        &lt;!&ndash; [ko-KR] Laon Shop 에 오신것을 환영합니다. Springboot + VueJs로 만드는 쇼핑몰입니다. &ndash;&gt;-->
<!--        &lt;!&ndash; [en-US] Welcome to Laon Shop. This is a shopping mall made with Springboot + VueJs. &ndash;&gt;-->
<!--        -->
<!--        <p class="mx-auto mt-12 max-w-xl text-xl text-gray-500">Welcome to Laon Shop. This is a shopping mall made with Springboot + VueJs.</p>-->
<!--      </div>-->
<!--    </div>-->
<!--  </div>-->
<!--</template>-->
<template>
  <div class="relative bg-gray-50 px-6 pt-16 pb-20 lg:px-8 lg:pt-24 lg:pb-28">
    <div class="absolute inset-0">
      <div class="h-1/3 bg-white sm:h-2/3" />
    </div>
    <div class="relative mx-auto max-w-7xl">
      <div class="text-center">
        <h2 class="text-3xl font-bold tracking-tight text-gray-900 sm:text-4xl">LaonShop에 오신 것을 환영합니다</h2>
        <p class="mx-auto mt-3 max-w-2xl text-xl text-gray-500 sm:mt-4">라온스토어 좋아하는 전자제품을 구입하는 가장 좋은 방법!</p>
      </div>
      <div class="bg-white">
        <div class="mx-auto max-w-2xl py-16 px-4 sm:py-24 sm:px-6 lg:max-w-7xl lg:px-8">
          <div class="flex items-center justify-between space-x-4">
            <h2 class="text-lg font-medium text-gray-900">Customers also viewed</h2>
            <a href="#" class="whitespace-nowrap text-sm font-medium text-indigo-600 hover:text-indigo-500">
              View all
              <span aria-hidden="true"> &rarr;</span>
            </a>
          </div>
          <div class="mt-6 grid grid-cols-1 gap-x-8 gap-y-8 sm:grid-cols-2 sm:gap-y-10 lg:grid-cols-6">
            <div v-for="product in products" :key="product.id" class="group relative">
              <div class="aspect-w-4 aspect-h-3 overflow-hidden rounded-lg bg-gray-100">
                <img :src="product.imageSrc" :alt="product.imageAlt" class="object-cover object-center" />
                <div class="flex items-end p-4 opacity-0 group-hover:opacity-100" aria-hidden="true">
                  <div class="w-full rounded-md bg-white bg-opacity-75 py-2 px-4 text-center text-sm font-medium text-gray-900 backdrop-blur backdrop-filter">View Product</div>
                </div>
              </div>
              <div class="mt-4 flex items-center justify-between space-x-8 text-base font-medium text-gray-900">
                <h3>
                  <a href="#">
                    <span aria-hidden="true" class="absolute inset-0" />
                    {{ product.name }}
                  </a>
                </h3>
                <p>{{ product.price }}</p>
              </div>
              <p class="mt-1 text-sm text-gray-500">{{ product.category }}</p>
            </div>
          </div>
        </div>
      </div>
      <div style="margin-top: 20px">
        <span class="mx-auto mt-3  text-xl font-bold sm:text-1xl" >인기 TOP 3 상품. </span>
        <span class="mx-auto mt-3  text-xl text-gray-600 font-bold sm:text-1xl">따끈따근한 제품 이야기</span>
      </div>
      <div class="mx-auto mt-12 grid max-w-lg gap-5 lg:max-w-none lg:grid-cols-3">
        <div v-for="post in volist" :key="post.itemId" class="flex flex-col overflow-hidden rounded-lg shadow-lg">
          <div class="flex-shrink-0">
            <img class="h-48 w-full object-cover" :src="post.imageVoList[0].image" alt="" />
          </div>
          <div class="flex flex-1 flex-col justify-between bg-white p-6">
            <div class="flex-1">
              <p class="text-sm font-medium text-indigo-600">
                {{post.cateName}}
              </p>
              <div class="mt-2 block">
                <p class="text-xl font-semibold text-gray-900">{{ post.name }}</p>
                <p class="mt-3 text-base text-gray-500">{{ post.itemDetail }}</p>
              </div>
            </div>
            <div class="mt-6 flex items-center">
              <div class="flex-shrink-0">
                  <span class="sr-only">{{ post.price }}</span>
              </div>
              <div class="ml-3">
                <p class="text-sm font-medium text-gray-900">
                  {{ post.sellerId }}
                </p>
                <div class="flex space-x-1 text-sm text-gray-500">
                  <span>{{ post.likeCnt }} like</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
  const products = [
  {
    id: 1,
    name: 'Computer',
    category: '',
    href: '#',
    price: '',
    imageSrc: 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/store-card-14-16-mac-nav-202301?wid=400&hei=260&fmt=png-alpha&.v=1670959891635',
    imageAlt:
    'Payment application dashboard screenshot with transaction table, financial highlights, and main clients on colorful purple background.',
  },
    {
    id: 2,
    name: 'Phone',
    category: '',
    href: '#',
    price: '',
    imageSrc: 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/store-card-13-iphone-nav-202209?wid=400&hei=260&fmt=png-alpha&.v=1661027785804',
    imageAlt:
    'Payment application dashboard screenshot with transaction table, financial highlights, and main clients on colorful purple background.',
  },
    {
    id: 3,
    name: 'Tablet',
    category: '',
    href: '#',
    price: '',
    imageSrc: 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/store-card-13-ipad-nav-202210?wid=400&hei=260&fmt=png-alpha&.v=1664912135437',
    imageAlt:
    'Payment application dashboard screenshot with transaction table, financial highlights, and main clients on colorful purple background.',
  }, {
    id: 4,
    name: 'Watch',
    category: '',
    href: '#',
    price: '',
    imageSrc: 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/store-card-13-watch-nav-202209_GEO_KR?wid=400&hei=260&fmt=png-alpha&.v=1661796453921',
    imageAlt:
    'Payment application dashboard screenshot with transaction table, financial highlights, and main clients on colorful purple background.',
  },{
    id: 5,
    name: '악세서리',
    category: '',
    href: '#',
    price: '',
    imageSrc: 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/store-card-13-accessories-nav-202209?wid=400&hei=260&fmt=png-alpha&.v=1660677092974',
    imageAlt:
    'Payment application dashboard screenshot with transaction table, financial highlights, and main clients on colorful purple background.',
  },{
    id: 6,
    name: 'Airpods',
    category: '',
    href: '#',
    price: '',
    imageSrc: 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/store-card-13-airpods-nav-202209?wid=400&hei=260&fmt=png-alpha&.v=1660676485885',
    imageAlt:
    'Payment application dashboard screenshot with transaction table, financial highlights, and main clients on colorful purple background.',
  },
  // More products...
  ]
</script>
<script>
import {mapGetters} from "vuex";
import GetItemsRequest from "@/service/request/GetItemsRequest";
import ResultCode from "@/service/ResultCode";

export default {
  name: "HomeView",
  components: {
  },
  props: {
  },
  data() {
    return {
      volist: [],
    }
  },
  computed: {

  },
  methods: {
    async getMainPage() {
      try {
        let res = await this.api.mainPage();
        if (res.code === ResultCode.Success) {
          this.volist = res.volist;
          console.log(res);
        }
      } catch (e) {
        console.error(e);
      }
    },
  },
  created() {
    this.getMainPage();
  },
  mounted() {

  },
  beforeUnmount() {

  },
};
</script>

<style>
</style>  
