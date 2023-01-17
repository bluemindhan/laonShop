<template>
  <div class="bg-white">
    <div class="pt-6 pb-16 sm:pb-24">
      <div class="mx-auto mt-8 max-w-2xl px-4 sm:px-6 lg:max-w-7xl lg:px-8">
        <div class="lg:grid lg:auto-rows-min lg:grid-cols-12 lg:gap-x-8">
          <div class="lg:col-span-5 lg:col-start-8">
            <div class="flex justify-between">
              <h1 class="text-xl font-medium text-gray-900">{{ itemVo.name }}</h1>
              <p class="text-xl font-medium text-gray-900">$ {{ itemVo.price }}</p>
            </div>
            <!-- Reviews -->
            <div class="mt-4">
              <h2 class="sr-only">Reviews</h2>
              <div class="flex items-center">
                <!-- <p class="text-sm text-gray-700">
                  좋아요 : {{ itemVo.likeCnt }} / 북마크 : {{ itemVo.bookCnt }}
                </p> -->
                <span class="isolate inline-flex rounded-md shadow-sm">
                  <button type="button" class="relative inline-flex items-center rounded-l-md border border-gray-300 bg-white px-4 py-2 text-sm font-medium text-gray-700 hover:bg-gray-50 focus:z-10 focus:border-indigo-500 focus:outline-none focus:ring-1 focus:ring-indigo-500">
                    <BookmarkIcon class="-ml-1 mr-2 h-5 w-5 text-gray-400" aria-hidden="true" />
                    bookmark
                  </button>
                  <button type="button" class="relative -ml-px inline-flex items-center rounded-r-md border border-gray-300 bg-white px-3 py-2 text-sm font-medium text-gray-700 hover:bg-gray-50 focus:z-10 focus:border-indigo-500 focus:outline-none focus:ring-1 focus:ring-indigo-500">{{ itemVo.bookCnt }}</button>
                </span>
              </div>
            </div>
          </div>

          <!-- Image gallery -->
          <div class="mt-8 lg:col-span-7 lg:col-start-1 lg:row-span-3 lg:row-start-1 lg:mt-0">
            <h2 class="sr-only">Images</h2>

            <div class="grid grid-cols-1 lg:grid-cols-2 lg:grid-rows-3 lg:gap-8">
              <img :src="itemVo.imageVoList[0].image" :class="['lg:col-span-2 lg:row-span-2']" />
            </div>
          </div>

          <div class="mt-8 lg:col-span-5">
            <form>
              <!-- Color picker -->
              <div>
                <h2 class="text-sm font-medium text-gray-900">Color</h2>

                <RadioGroup v-model="selectedColor" class="mt-2">
                  <RadioGroupLabel class="sr-only"> Choose a color </RadioGroupLabel>
                  <div class="flex items-center space-x-3">
                    <RadioGroupOption as="template" v-for="color in product.colors" :key="color.name" :value="color" v-slot="{ active, checked }">
                      <div :class="[color.selectedColor, active && checked ? 'ring ring-offset-1' : '', !active && checked ? 'ring-2' : '', '-m-0.5 relative p-0.5 rounded-full flex items-center justify-center cursor-pointer focus:outline-none']">
                        <RadioGroupLabel as="span" class="sr-only"> {{ color.name }} </RadioGroupLabel>
                        <span aria-hidden="true" :class="[color.bgColor, 'h-8 w-8 border border-black border-opacity-10 rounded-full']" />
                      </div>
                    </RadioGroupOption>
                  </div>
                </RadioGroup>
              </div>

              <!-- Size picker -->
              <div class="mt-8">
                <div class="flex items-center justify-between">
                  <h2 class="text-sm font-medium text-gray-900">Size</h2>
                </div>

                <RadioGroup class="mt-2">
                  <RadioGroupLabel class="sr-only"> Choose a size </RadioGroupLabel>
                  <div class="grid grid-cols-1 gap-1 2xl:grid-cols-4">
                  <!-- <div class="grid grid-cols-1 gap-1 sm:grid-cols-6"> -->
                    <RadioGroupOption as="template">
                      <div :class="['cursor-pointer focus:outline-none', 'ring-2 ring-offset-2 ring-indigo-500', 'bg-indigo-600 border-transparent text-white hover:bg-indigo-700', 'border rounded-md py-3 px-3 flex items-center justify-center text-sm font-medium uppercase sm:flex-1']">
                        <RadioGroupLabel as="span">One Size</RadioGroupLabel>
                      </div>
                    </RadioGroupOption>
                  </div>
                </RadioGroup>
              </div>

              <!-- <button type="submit" class="mt-8 flex w-full items-center justify-center rounded-md border border-transparent bg-indigo-600 py-3 px-8 text-base font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2">Add to cart</button> -->
              <div class="sm:flex-col1 mt-10 flex">
                <button type="submit" class="flex max-w-xs flex-1 items-center justify-center rounded-md border border-transparent bg-indigo-600 py-3 px-8 text-base font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 focus:ring-offset-gray-50 sm:w-full">Add to bag</button>

                <button 
                type="button" 
                @click="liked" 
                class="ml-4 flex items-center justify-center rounded-md py-3 px-3 text-gray-400 hover:bg-gray-100 hover:text-gray-500"
                v-if="isShow"
               >
                  <HeartIcon 
                  class="h-6 w-6 flex-shrink-0" 
                  aria-hidden="true" />
                  <span class="sr-only">찜</span>
                </button>

                <button 
                type="button" 
                @click="unliked" 
                class="ml-4 flex items-center justify-center rounded-md py-3 px-3 text-gray-400 hover:bg-gray-100 hover:text-gray-500"
                v-else
                >
                  <HeartIcon 
                  class="h-6 w-6 flex-shrink-0 fill-red-500 text-transparent" 
                  aria-hidden="true" 
                  />
                  <span class="sr-only">찜</span>
                </button>
              </div>
            </form>

            <!-- Product details -->
            <div class="mt-10">
              <h2 class="text-sm font-medium text-gray-900">상세정보</h2>

              <div class="prose prose-sm mt-4 text-gray-500" v-html="itemVo.itemDetail" />
            </div>
            <!-- Reviews -->
            <section aria-labelledby="reviews-heading" class="mt-16 sm:mt-24">
              <h2 id="reviews-heading" class="text-lg font-medium text-gray-900">상품 리뷰</h2>

              <div class="mt-6 space-y-10 divide-y divide-gray-200 border-t border-b border-gray-200 pb-10">
                <div class="pt-10 lg:grid lg:grid-cols-12 lg:gap-x-8">
                  <div class="lg:col-span-8 lg:col-start-5 xl:col-span-9 xl:col-start-4 xl:grid xl:grid-cols-3 xl:items-start xl:gap-x-8">
                    <div class="flex items-center xl:col-span-1">
                      <div class="flex items-center">
                        <!-- <StarIcon v-for="rating in [0, 1, 2, 3, 4]" :key="rating" :class="[review.rating > rating ? 'text-yellow-400' : 'text-gray-200', 'h-5 w-5 flex-shrink-0']" aria-hidden="true" /> -->
                      </div>
                      <!-- <p class="ml-3 text-sm text-gray-700">{{ review.rating }}<span class="sr-only"> out of 5 stars</span></p> -->
                    </div>

                    <div class="mt-4 lg:mt-6 xl:col-span-2 xl:mt-0">
                      <!-- <h3 class="text-sm font-medium text-gray-900">{{ itemVo.commentVoList[0].comment || 0 }}</h3> -->

                      <!-- <div class="mt-3 space-y-6 text-sm text-gray-500" v-html="itemVo.commentVoList[0].comment || 0 "/> -->
                    </div>
                  </div>

                  <!-- <div class="mt-6 flex items-center text-sm lg:col-span-4 lg:col-start-1 lg:row-start-1 lg:mt-0 lg:flex-col lg:items-start xl:col-span-3">
                    <p class="font-medium text-gray-900">{{ review.author }}</p>
                    <time :datetime="review.datetime" class="ml-4 border-l border-gray-200 pl-4 text-gray-500 lg:ml-0 lg:mt-2 lg:border-0 lg:pl-0">{{ review.date }}</time>
                  </div> -->
                </div>
              </div>
            </section>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ItemDetailRequest from '@/service/request/ItemDetailRequest.js';
import AddLikeRequest from '@/service/request/AddLikeRequest.js';
import GetLikeListRequest from '@/service/request/GetLikeListRequest.js';
import DeleteLikeRequest from '@/service/request/DeleteLikeRequest.js';
import {mapGetters, mapMutations} from "vuex";
import ResultCode from "@/service/ResultCode";

export default {
  name: 'UserProductDetailView',
  components: {
  },
  props: {
  },
  data() {
    return {
      id: this.$route.params.id,
      itemVo: {
        imageVoList: [],
        commentVoList: [],
      },
      wishList: [],
      isShow: true,
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
    },
  },
  methods: {
    ...mapMutations({
      setAccessToken: "appStore/accessToken",
      setRefreshToken: "appStore/refreshToken",
    }),
    async itemDetail() {
      let req = new ItemDetailRequest();
      req.productNum = this.id;
      console.log(req);
      try {
        let res = await this.api.itemDetail(req);
        if (res.code === ResultCode.Success) {
          this.itemVo = res.itemVo;
          console.log(res);
        }
      } catch (e) {
        console.error(e);
      }
    },
    
    async liked() {
      let req = new AddLikeRequest();
      req.prdNum = this.id;
      console.log(req);
      try {
        let res = await this.api.addLike(req);
        if (res.code === ResultCode.Success) {
          console.log(res);
          alert("상품을 찜했어요!")
          this.isShow = false;
        } else {
          alert(res.message);
        }
      } catch (e) {
        console.error(e);
      }
    },

    async unliked() {
      let req = new DeleteLikeRequest();
      req.prdNum = this.id;
      console.log(req);
      try {
        let res = await this.api.deleteLike(req);
        console.log(res);
        if (res.code === ResultCode.Success) {
          console.log(res);
          alert("찜 취소 완료!")
          this.isShow = true;
        } else {
          alert(res.message);
        }
      } catch (e) {
        console.error(e);
      }
    },

    async getLikeList() {
      let req = new GetLikeListRequest();
      req.accessToken = this.accessToken;
      console.log(req);
      try {
        let res = await this.api.getLikeList(req);
        if (res.code === ResultCode.Success) {
          this.wishList = res.wishList;
          console.log(res);

          for(let i = 0; i < this.wishList.length; i++) {
            if(this.wishList[i].productNum == this.id) {
              console.log(this.wishList[i].productNum);
              console.log(this.wishList[i].productNum==this.id? true : false);
              this.isShow = false;
            } 
          }
        }
      } catch (e) {
        console.error(e);
      }
    }
  },
  created() {
    console.log("ItemDetail.vue..", this.accessToken);
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
      this.itemDetail();
      this.getLikeList();
    }
  },
  mounted() {
  },
  beforeUnmount() {
  }
}
</script>

<script setup>
import { ref, watch } from 'vue'
import { StarIcon } from '@heroicons/vue/20/solid'
import { RadioGroup, RadioGroupLabel, RadioGroupOption } from '@headlessui/vue'
import { CurrencyDollarIcon, GlobeAmericasIcon } from '@heroicons/vue/24/outline'
import { BookmarkIcon } from '@heroicons/vue/20/solid'
import { HeartIcon } from '@heroicons/vue/24/outline'

const product = {
  name: 'Basic Tee',
  price: '$35',
  rating: 3.9,
  reviewCount: 512,
  href: '#',
  images: [
    {
      id: 1,
      imageSrc: 'https://tailwindui.com/img/ecommerce-images/product-page-01-featured-product-shot.jpg',
      imageAlt: "Back of women's Basic Tee in black.",
      primary: true,
    },
  ],
  colors: [
    { name: 'Black', bgColor: 'bg-gray-900', selectedColor: 'ring-gray-900' },
    { name: 'Heather Grey', bgColor: 'bg-gray-400', selectedColor: 'ring-gray-400' },
  ],
  sizes: [
    { name: 'XXS', inStock: true },
    { name: 'XS', inStock: true },
    { name: 'S', inStock: true },
    { name: 'M', inStock: true },
    { name: 'L', inStock: true },
    { name: 'XL', inStock: false },
  ],
  description: `
    <p>The Basic tee is an honest new take on a classic. The tee uses super soft, pre-shrunk cotton for true comfort and a dependable fit. They are hand cut and sewn locally, with a special dye technique that gives each tee it's own look.</p>
    <p>Looking to stock your closet? The Basic tee also comes in a 3-pack or 5-pack at a bundle discount.</p>
  `,
  details: [
    'Only the best materials',
    'Ethically and locally made',
    'Pre-washed and pre-shrunk',
    'Machine wash cold with similar colors',
  ],
}
const policies = [
  { name: 'International delivery', icon: GlobeAmericasIcon, description: 'Get your order in 2 years' },
  { name: 'Loyalty rewards', icon: CurrencyDollarIcon, description: "Don't look at other tees" },
]

const selectedColor = ref(product.colors[0])
const selectedSize = ref(product.sizes[2])
</script>

<style>

</style>