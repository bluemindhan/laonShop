<template>
  <div class="bg-white mt-2">

  <!-- Global notification live region, render this permanently at the end of the document -->
    <div aria-live="assertive" class="pointer-events-none fixed inset-0 flex items-end px-4 py-6 sm:items-start sm:p-6">
      <div class="flex w-full flex-col items-center space-y-4 sm:items-end">
        <!-- Notification panel, dynamically insert this into the live region when it needs to be displayed -->
        <transition enter-active-class="transform ease-out duration-300 transition" enter-from-class="translate-y-2 opacity-0 sm:translate-y-0 sm:translate-x-2" enter-to-class="translate-y-0 opacity-100 sm:translate-x-0" leave-active-class="transition ease-in duration-100" leave-from-class="opacity-100" leave-to-class="opacity-0">
          <div v-if="show" class="pointer-events-auto w-full max-w-sm overflow-hidden rounded-lg bg-white shadow-lg ring-1 ring-black ring-opacity-5">
            <div class="p-4">
              <div class="flex items-start">
                <div class="flex-shrink-0">
                  <CheckCircleIcon class="h-6 w-6 text-green-400" aria-hidden="true" />
                </div>
                <div class="ml-3 w-0 flex-1 pt-0.5">
                  <p class="text-sm font-medium text-gray-900">찜하기 성공!</p>
                  <!-- <p class="mt-1 text-sm text-gray-500">Anyone with a link can now view this file.</p> -->
                </div>
                <div class="ml-4 flex flex-shrink-0">
                  <button type="button" @click="show = false" class="inline-flex rounded-md bg-white text-gray-400 hover:text-gray-500 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2">
                    <span class="sr-only">Close</span>
                    <XMarkIcon class="h-5 w-5" aria-hidden="true" />
                  </button>
                </div>
              </div>
            </div>
          </div>
        </transition>
      </div>
    </div>

    <!-- Global notification live region, render this permanently at the end of the document -->
    <div aria-live="assertive" class="pointer-events-none fixed inset-0 flex items-end px-4 py-6 sm:items-start sm:p-6">
      <div class="flex w-full flex-col items-center space-y-4 sm:items-end">
        <!-- Notification panel, dynamically insert this into the live region when it needs to be displayed -->
        <transition enter-active-class="transform ease-out duration-300 transition" enter-from-class="translate-y-2 opacity-0 sm:translate-y-0 sm:translate-x-2" enter-to-class="translate-y-0 opacity-100 sm:translate-x-0" leave-active-class="transition ease-in duration-100" leave-from-class="opacity-100" leave-to-class="opacity-0">
          <div v-if="cancel" class="pointer-events-auto w-full max-w-sm overflow-hidden rounded-lg bg-white shadow-lg ring-1 ring-black ring-opacity-5">
            <div class="p-4">
              <div class="flex items-start">
                <div class="flex-shrink-0">
                  <CheckCircleIcon class="h-6 w-6 text-green-400" aria-hidden="true" />
                </div>
                <div class="ml-3 w-0 flex-1 pt-0.5">
                  <p class="text-sm font-medium text-gray-900">찜하기 취소</p>
                  <!-- <p class="mt-1 text-sm text-gray-500">Anyone with a link can now view this file.</p> -->
                </div>
                <div class="ml-4 flex flex-shrink-0">
                  <button type="button" @click="cancel = false" class="inline-flex rounded-md bg-white text-gray-400 hover:text-gray-500 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2">
                    <span class="sr-only">Close</span>
                    <XMarkIcon class="h-5 w-5" aria-hidden="true" />
                  </button>
                </div>
              </div>
            </div>
          </div>
        </transition>
      </div>
    </div>

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
                <!-- <span class="isolate inline-flex rounded-md shadow-sm">
                  <button type="button" class="relative inline-flex items-center rounded-l-md border border-gray-300 bg-white px-4 py-2 text-sm font-medium text-gray-700 hover:bg-gray-50 focus:z-10 focus:border-indigo-500 focus:outline-none focus:ring-1 focus:ring-indigo-500">
                    <BookmarkIcon class="-ml-1 mr-2 h-5 w-5 text-gray-400" aria-hidden="true" />
                    bookmark
                  </button>
                  <button type="button" class="relative -ml-px inline-flex items-center rounded-r-md border border-gray-300 bg-white px-3 py-2 text-sm font-medium text-gray-700 hover:bg-gray-50 focus:z-10 focus:border-indigo-500 focus:outline-none focus:ring-1 focus:ring-indigo-500">{{ itemVo.bookCnt }}</button>
                </span> -->
              </div>
            </div>
          </div>

          <!-- Image gallery -->
          <div class="mt-8 lg:col-span-7 lg:col-start-1 lg:row-span-3 lg:row-start-1 lg:mt-0">
            <h2 class="sr-only">Images</h2>

            <div 
            class="grid grid-cols-1 lg:grid-cols-2 lg:grid-rows-3 lg:gap-8"
            v-for="item in itemVo.imageVoList" :key="item.image">
              <img :src="item.image" :class="['lg:col-span-2 lg:row-span-2']" />
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
              <div>
                <div class="border-t border-gray-200">
                 <!-- 리뷰 작성 -->
                 <!-- <div class="flex items-start space-x-4  mt-4">
                  <div class="flex-shrink-0">
                    <img class="inline-block h-10 w-10 rounded-full" src="https://images.unsplash.com/photo-1550525811-e5869dd03032?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80" alt="" />
                  </div>
                  <div class="min-w-0 flex-1">
                    <form action="#" class="relative">
                      <div class="overflow-hidden rounded-lg border border-gray-300 shadow-sm focus-within:border-indigo-500 focus-within:ring-1 focus-within:ring-indigo-500">
                        <label for="comment" class="sr-only">Add your comment</label>
                        <textarea rows="3" name="comment" id="comment" class="block w-full resize-none border-0 py-3 focus:ring-0 sm:text-sm" placeholder="상품의 후기를 작성해주세요" /> -->

                        <!-- Spacer element to match the height of the toolbar -->
                        <!-- <div class="py-2" aria-hidden="true"> -->
                          <!-- Matches height of button in toolbar (1px border + 36px content height) -->
                          <!-- <div class="py-px">
                            <div class="h-9" />
                          </div>
                        </div>
                      </div>

                      <div class="absolute inset-x-0 bottom-0 flex justify-between py-2 pl-3 pr-2">
                        <div class="flex items-center space-x-5">
                          <div class="flex items-center">
                            <Listbox as="div" v-model="selected">
                              <ListboxLabel class="sr-only"> Your mood </ListboxLabel>
                              <div class="relative">
                                <ListboxButton class="relative -m-2.5 flex h-10 w-10 items-center justify-center rounded-full text-gray-400 hover:text-gray-500">
                                  <span class="flex items-center justify-center">
                                    <span v-if="selected.value === null">
                                      <FaceSmileIcon class="h-5 w-5 flex-shrink-0" aria-hidden="true" />
                                      <span class="sr-only"> Add your mood </span>
                                    </span>
                                    <span v-if="!(selected.value === null)">
                                      <span :class="[selected.bgColor, 'flex h-8 w-8 items-center justify-center rounded-full']">
                                        <component :is="selected.icon" class="h-5 w-5 flex-shrink-0 text-white" aria-hidden="true" />
                                      </span>
                                      <span class="sr-only">{{ selected.name }}</span>
                                    </span>
                                  </span>
                                </ListboxButton>

                                <transition leave-active-class="transition ease-in duration-100" leave-from-class="opacity-100" leave-to-class="opacity-0">
                                  <ListboxOptions class="absolute z-10 mt-1 -ml-6 w-60 rounded-lg bg-white py-3 text-base shadow ring-1 ring-black ring-opacity-5 focus:outline-none sm:ml-auto sm:w-64 sm:text-sm">
                                    <ListboxOption as="template" v-for="mood in moods" :key="mood.value" :value="mood" v-slot="{ active }">
                                      <li :class="[active ? 'bg-gray-100' : 'bg-white', 'relative cursor-default select-none py-2 px-3']">
                                        <div class="flex items-center">
                                          <div :class="[mood.bgColor, 'w-8 h-8 rounded-full flex items-center justify-center']">
                                            <component :is="mood.icon" :class="[mood.iconColor, 'flex-shrink-0 h-5 w-5']" aria-hidden="true" />
                                          </div>
                                          <span class="ml-3 block truncate font-medium">{{ mood.name }}</span>
                                        </div>
                                      </li>
                                    </ListboxOption>
                                  </ListboxOptions>
                                </transition>
                              </div>
                            </Listbox>
                          </div>
                        </div>
                        <div class="flex-shrink-0">
                          <button type="submit" class="inline-flex items-center rounded-md border border-transparent bg-indigo-600 px-4 py-2 text-sm font-medium text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2">리뷰 작성</button>
                        </div>
                      </div>
                    </form>
                  </div>
                </div> -->
                </div>
              </div>
              <div class="mt-6 space-y-10 divide-y divide-gray-200 border-t border-b border-gray-200 pb-10">
                <div class="pt-10 lg:grid lg:grid-cols-12 lg:gap-x-8">
                  <div class="lg:col-span-8 lg:col-start-5 xl:col-span-9 xl:grid xl:items-start xl:gap-x-8">
                    <div class="mt-4 lg:mt-6 xl:col-span-2 xl:mt-0 ml-6"
                    v-for="item in itemVo.commentVoList" :key="item.comment">
                      <h3 class="text-sm font-medium text-gray-900">{{ item.comment || 0 }}</h3>
                    </div>
                  </div>
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
      show: false,
      cancel: false,
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
          this.isShow = false;
          this.show = true
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
          this.isShow = true;
          this.cancel = true
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
import { CheckCircleIcon } from '@heroicons/vue/24/outline'
import { XMarkIcon } from '@heroicons/vue/20/solid'
import {
  FaceFrownIcon,
  FaceSmileIcon,
  FireIcon,
  HandThumbUpIcon,
  PaperClipIcon,
} from '@heroicons/vue/20/solid'
import { Listbox, ListboxButton, ListboxLabel, ListboxOption, ListboxOptions } from '@headlessui/vue'


const moods = [
  { name: 'Excited', value: 'excited', icon: FireIcon, iconColor: 'text-white', bgColor: 'bg-red-500' },
  { name: 'Loved', value: 'loved', icon: HeartIcon, iconColor: 'text-white', bgColor: 'bg-pink-400' },
  { name: 'Happy', value: 'happy', icon: FaceSmileIcon, iconColor: 'text-white', bgColor: 'bg-green-400' },
  { name: 'Sad', value: 'sad', icon: FaceFrownIcon, iconColor: 'text-white', bgColor: 'bg-yellow-400' },
  { name: 'Thumbsy', value: 'thumbsy', icon: HandThumbUpIcon, iconColor: 'text-white', bgColor: 'bg-blue-500' },
  { name: 'I feel nothing', value: null, icon: XMarkIcon, iconColor: 'text-gray-400', bgColor: 'bg-transparent' },
]

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
const selected = ref(moods[5])
</script>

<style>

</style>