<template>
  <div class="bg-white">
    <div class="pt-6 pb-16 sm:pb-24">
      <div class="mx-auto mt-8 max-w-2xl px-4 sm:px-6 lg:max-w-7xl lg:px-8">
        <div class="lg:grid lg:auto-rows-min lg:grid-cols-12 lg:gap-x-8">
          <div class="lg:col-span-5 lg:col-start-8">
            <div class="flex justify-between">
              <h1 class="text-xl font-medium text-gray-900">{{ itemVo.name }}</h1>
              <p class="text-xl font-medium text-gray-900">{{ itemVo.price }}</p>
            </div>
            <!-- Reviews -->
            <div class="mt-4">
              <h2 class="sr-only">Reviews</h2>
              <div class="flex items-center">
                <p class="text-sm text-gray-700">
                  {{ product.rating }}
                  <span class="sr-only"> out of 5 stars</span>
                </p>
                <div class="ml-1 flex items-center">
                  <StarIcon v-for="rating in [0, 1, 2, 3, 4]" :key="rating" :class="[product.rating > rating ? 'text-yellow-400' : 'text-gray-200', 'h-5 w-5 flex-shrink-0']" aria-hidden="true" />
                </div>
                <div aria-hidden="true" class="ml-4 text-sm text-gray-300">·</div>
                <div class="ml-4 flex">
                  <a href="#" class="text-sm font-medium text-indigo-600 hover:text-indigo-500">See all {{ product.reviewCount }} reviews</a>
                </div>
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
                  <div class="grid grid-cols-3 gap-3 sm:grid-cols-6">
                    <RadioGroupOption as="template">
                      <div :class="['cursor-pointer focus:outline-none', 'ring-2 ring-offset-2 ring-indigo-500', 'bg-indigo-600 border-transparent text-white hover:bg-indigo-700', 'border rounded-md py-3 px-3 flex items-center justify-center text-sm font-medium uppercase sm:flex-1']">
                        <RadioGroupLabel as="span">One Size</RadioGroupLabel>
                      </div>
                    </RadioGroupOption>
                  </div>
                </RadioGroup>
              </div>

              <button type="submit" class="mt-8 flex w-full items-center justify-center rounded-md border border-transparent bg-indigo-600 py-3 px-8 text-base font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2">Add to cart</button>
            </form>

            <!-- Product details -->
            <div class="mt-10">
              <h2 class="text-sm font-medium text-gray-900">상세정보</h2>

              <div class="prose prose-sm mt-4 text-gray-500" v-html="itemVo.itemDetail" />
            </div>

          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ItemDetailRequest from '@/service/request/ItemDetailRequest.js';
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
    }
  },
  mounted() {
  },
  beforeUnmount() {
  }
}
</script>

<script setup>
import { ref } from 'vue'
import { StarIcon } from '@heroicons/vue/20/solid'
import { RadioGroup, RadioGroupLabel, RadioGroupOption } from '@headlessui/vue'
import { CurrencyDollarIcon, GlobeAmericasIcon } from '@heroicons/vue/24/outline'

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