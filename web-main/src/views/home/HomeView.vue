<template>
  <div class="relative bg-white">
    <!-- Background image and overlap -->
    <div aria-hidden="true" class="absolute inset-0 hidden sm:flex sm:flex-col">
      <div class="relative w-full flex-1 bg-gray-800">
        <div class="absolute inset-0 overflow-hidden">
          <img src="https://tailwindui.com/img/ecommerce-images/home-page-01-feature-section-01.jpg" alt="" class="h-full w-full object-cover object-center" />
        </div>
        <div class="absolute inset-0 bg-gray-900 opacity-50" />
      </div>
      <div class="h-32 w-full bg-white md:h-40 lg:h-48" />
    </div>

    <div class="relative mx-auto max-w-3xl px-4 pb-96 text-center sm:px-6 sm:pb-0 lg:px-8">
      <!-- Background image and overlap -->
      <div aria-hidden="true" class="absolute inset-0 flex flex-col sm:hidden">
        <div class="relative w-full flex-1 bg-gray-800">
          <div class="absolute inset-0 overflow-hidden">
            <img src="https://tailwindui.com/img/ecommerce-images/home-page-04-hero-full-width.jpg" alt="" class="h-full w-full object-cover object-center" />
          </div>
          <div class="absolute inset-0 bg-gray-900 opacity-50" />
        </div>
        <div class="h-48 w-full bg-white" />
      </div>
      <div class="relative py-32">
        <h1 class="text-4xl font-bold tracking-tight text-white sm:text-5xl md:text-6xl">Loan Electronics shop</h1>
        <div class="mt-4 sm:mt-6">
          <a href="#" class="inline-block rounded-md border border-transparent bg-indigo-600 py-3 px-8 font-medium text-white hover:bg-indigo-700">Shop Collection</a>
        </div>
      </div>
    </div>

    <section aria-labelledby="collection-heading" class="relative -mt-96 sm:mt-0">
      <h2 id="collection-heading" class="sr-only">Collections</h2>
      <div class="mx-auto grid max-w-md grid-cols-1 gap-y-6 px-4 sm:max-w-7xl sm:grid-cols-3 sm:gap-y-0 sm:gap-x-6 sm:px-6 lg:gap-x-8 lg:px-8">
        <div v-for="collection in collections" :key="collection.name" class="group relative h-96 rounded-lg bg-white shadow-xl sm:aspect-w-4 sm:aspect-h-5 sm:h-auto">
          <div>
            <div aria-hidden="true" class="absolute inset-0 overflow-hidden rounded-lg">
              <div class="absolute inset-0 overflow-hidden group-hover:opacity-75">
                <img :src="collection.imageSrc" :alt="collection.imageAlt" class="h-full w-full object-cover object-center" />
              </div>
              <div class="absolute inset-0 bg-gradient-to-b from-transparent to-black opacity-50" />
            </div>
            <div class="absolute inset-0 flex items-end rounded-lg p-6">
              <div>
                <p aria-hidden="true" class="text-sm text-white">Shop the collection</p>
                <h3 class="mt-1 font-semibold text-white">
                  <a :href="collection.href">
                    <span class="absolute inset-0" />
                    {{ collection.name }}
                  </a>
                </h3>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>


  <div class="relative bg-white px-6 pt-16 pb-20 lg:px-8 lg:pt-24 lg:pb-28">
    <div class="relative mx-auto max-w-7xl">
      <div class="text-center">
        <h2 class="text-3xl font-bold tracking-tight text-gray-900 sm:text-4xl">LaonShop에 오신 것을 환영합니다</h2>
        <p class="mx-auto mt-3 max-w-2xl text-xl text-gray-500 sm:mt-4">라온스토어 좋아하는 전자제품을 구입하는 가장 좋은 방법!</p>
      </div>
      <div class="bg-white">
        <div class="mx-auto max-w-2xl py-16 px-4 sm:py-24 sm:px-6 lg:max-w-7xl lg:px-8">
          <div class="flex items-center justify-between space-x-4">
            <h2 class="text-lg font-medium text-gray-900">Customers also viewed</h2>
            <a href="/user" class="whitespace-nowrap text-sm font-medium text-indigo-600 hover:text-indigo-500">
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
const collections = [
  {
    name: "Computer",
    href: '#',
    imageSrc: 'https://cdn.pixabay.com/photo/2015/01/08/18/25/desk-593327__480.jpg',
    imageAlt: 'Woman wearing an off-white cotton t-shirt.',
  },
  {
    name: "Headphones",
    href: '#',
    imageSrc: 'https://cdn.pixabay.com/photo/2018/09/17/14/27/headphones-3683983__480.jpg',
    imageAlt: 'Man wearing a charcoal gray cotton t-shirt.',
  },
  {
    name: 'Desk Accessories',
    href: '#',
    imageSrc: 'https://tailwindui.com/img/ecommerce-images/home-page-04-collection-03.jpg',
    imageAlt: 'Person sitting at a wooden desk with paper note organizer, pencil and tablet.',
  },
]
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
    name: '블루투스 이어폰',
    category: '',
    href: '#',
    price: '',
    imageSrc: 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/store-card-13-airpods-nav-202209?wid=400&hei=260&fmt=png-alpha&.v=1660676485885',
    imageAlt:
    'Payment application dashboard screenshot with transaction table, financial highlights, and main clients on colorful purple background.',
  },
  {
    id: 6,
    name: '악세서리',
    category: '',
    href: '#',
    price: '',
    imageSrc: 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/store-card-13-accessories-nav-202209?wid=400&hei=260&fmt=png-alpha&.v=1660677092974',
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
