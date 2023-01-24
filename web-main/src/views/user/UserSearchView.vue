<template>
<div>
  <div class="bg-gray-50">
    <!-- Mobile filter dialog -->
    <TransitionRoot as="template" :show="open">
      <Dialog as="div" class="relative z-40 sm:hidden" @close="open = false">
        <TransitionChild as="template" enter="transition-opacity ease-linear duration-300" enter-from="opacity-0" enter-to="opacity-100" leave="transition-opacity ease-linear duration-300" leave-from="opacity-100" leave-to="opacity-0">
          <div class="fixed inset-0 bg-black bg-opacity-25" />
        </TransitionChild>

        <div class="fixed inset-0 z-40 flex">
          <TransitionChild as="template" enter="transition ease-in-out duration-300 transform" enter-from="translate-x-full" enter-to="translate-x-0" leave="transition ease-in-out duration-300 transform" leave-from="translate-x-0" leave-to="translate-x-full">
            <DialogPanel class="relative ml-auto flex h-full w-full max-w-xs flex-col overflow-y-auto bg-white py-4 pb-6 shadow-xl">
              <div class="flex items-center justify-between px-4">
                <h2 class="text-lg font-medium text-gray-900">Filters</h2>
                <button type="button" class="-mr-2 flex h-10 w-10 items-center justify-center rounded-md bg-white p-2 text-gray-400 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-indigo-500" @click="open = false">
                  <span class="sr-only">Close menu</span>
                  <XMarkIcon class="h-6 w-6" aria-hidden="true" />
                </button>
              </div>

              <!-- Filters -->
              <form class="mt-4">
                <Disclosure as="div" v-for="section in filters" :key="section.name" class="border-t border-gray-200 px-4 py-6" v-slot="{ open }">
                  <h3 class="-mx-2 -my-3 flow-root">
                    <DisclosureButton class="flex w-full items-center justify-between bg-white px-2 py-3 text-sm text-gray-400">
                      <span class="font-medium text-gray-900">{{ section.name }}</span>
                      <span class="ml-6 flex items-center">
                        <ChevronDownIcon :class="[open ? '-rotate-180' : 'rotate-0', 'h-5 w-5 transform']" aria-hidden="true" />
                      </span>
                    </DisclosureButton>
                  </h3>
                  <DisclosurePanel class="pt-6">
                    <div class="space-y-6">
                      <div v-for="(option, optionIdx) in section.options" :key="option.value" class="flex items-center">
                        <input :id="`filter-mobile-${section.id}-${optionIdx}`" :name="`${section.id}[]`" :value="option.value" type="checkbox" class="h-4 w-4 rounded border-gray-300 text-indigo-600 focus:ring-indigo-500" />
                        <label :for="`filter-mobile-${section.id}-${optionIdx}`" class="ml-3 text-sm text-gray-500">{{ option.label }}</label>
                      </div>
                    </div>
                  </DisclosurePanel>
                </Disclosure>
              </form>
            </DialogPanel>
          </TransitionChild>
        </div>
      </Dialog>
    </TransitionRoot>

    <div class="mx-auto max-w-3xl px-4 text-center sm:px-6 lg:max-w-7xl lg:px-8">
      <div class="py-24">
        <h1 class="text-4xl font-bold tracking-tight text-gray-900">New Arrivals</h1>
        <p v-if="isShow" class="mx-auto mt-4 max-w-3xl text-base text-gray-500"> "{{ searchWord }}" 에 대한 {{ totalCount || 0 }}건의 검색 결과 입니다.</p>
      </div>

      <section aria-labelledby="filter-heading" class="border-t border-gray-200 py-6">
        <h2 id="filter-heading" class="sr-only">Product filters</h2>

        <div class="flex items-center justify-between">
          <!-- <Menu as="div" class="relative inline-block text-left">
            <div>
              <MenuButton class="group inline-flex justify-center text-sm font-medium text-gray-700 hover:text-gray-900">
                정렬
                <ChevronDownIcon class="-mr-1 ml-1 h-5 w-5 flex-shrink-0 text-gray-400 group-hover:text-gray-500" aria-hidden="true" />
              </MenuButton>
            </div>

            <transition enter-active-class="transition ease-out duration-100" enter-from-class="transform opacity-0 scale-95" enter-to-class="transform opacity-100 scale-100" leave-active-class="transition ease-in duration-75" leave-from-class="transform opacity-100 scale-100" leave-to-class="transform opacity-0 scale-95">
              <MenuItems class="absolute left-0 z-10 mt-2 w-40 origin-top-left rounded-md bg-white shadow-2xl ring-1 ring-black ring-opacity-5 focus:outline-none">
                <div class="py-1">
                  <MenuItem v-for="option in sortOptions" :key="option" v-slot="{ active }">
                    <a :href="option.href" :class="[active ? 'bg-gray-100' : '', 'block px-4 py-2 text-sm font-medium text-gray-900']">{{ option.name }}</a>
                  </MenuItem>
                </div>
              </MenuItems>
            </transition>
          </Menu> -->

          <div class="flex flex-1 items-center justify-center px-2 lg:ml-6 lg:justify-end mr-40 flex">
          <div class="w-full max-w-lg lg:max-w-xs">
            <label for="search" class="sr-only">검색</label>
            <div class="relative">
              <div class="pointer-events-none absolute inset-y-0 left-0 flex items-center pl-3">
                <MagnifyingGlassIcon class="h-5 w-5 text-gray-400" aria-hidden="true"/>
              </div>
              <input id="keyWord" name="keyWord" class="block w-full rounded-md border border-gray-300 bg-white py-2 pl-10 pr-3 leading-5 placeholder-gray-500 focus:border-indigo-500 focus:placeholder-gray-400 focus:outline-none focus:ring-1 focus:ring-indigo-500 sm:text-sm"
              placeholder="검색할 상품을 입력하세요" 
              type="text"
              required
              v-model="keyWord"
              @keyup.enter="search"
              />
            </div>

          </div>
            <button type="button" @click="search" class="inline-flex items-center rounded-md border border-gray-300 bg-white px-4 py-2 text-sm font-medium text-gray-700 shadow-sm hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 ml-3"
            >검색</button>
          </div>

          <button type="button" class="inline-block text-sm font-medium text-gray-700 hover:text-gray-900 sm:hidden" @click="open = true">Filters</button>

          <PopoverGroup class="hidden sm:flex sm:items-baseline sm:space-x-8 mx-8">
            <Popover as="div" v-for="(section, sectionIdx) in sort" :key="section.name" :id="`desktop-menu-${sectionIdx}`" class="relative inline-block text-left">
              <div>
                <PopoverButton class="group inline-flex items-center justify-center text-sm font-medium text-gray-700 hover:text-gray-900">
                  <span>{{ section.name }}</span>
                  <!-- <span v-if="sectionIdx === 0" class="ml-1.5 rounded bg-gray-200 py-0.5 px-1.5 text-xs font-semibold tabular-nums text-gray-700">1</span> -->
                  <ChevronDownIcon class="-mr-1 ml-1 h-5 w-5 flex-shrink-0 text-gray-400 group-hover:text-gray-500" aria-hidden="true" />
                </PopoverButton>
              </div>

              <transition enter-active-class="transition ease-out duration-100" enter-from-class="transform opacity-0 scale-95" enter-to-class="transform opacity-100 scale-100" leave-active-class="transition ease-in duration-75" leave-from-class="transform opacity-100 scale-100" leave-to-class="transform opacity-0 scale-95">
                <PopoverPanel class="absolute right-0 z-10 mt-2 origin-top-right rounded-md bg-white p-4 shadow-2xl ring-1 ring-black ring-opacity-5 focus:outline-none">
                  <form class="space-y-4">
                    <div v-for="(option, optionIdx) in section.options" :key="option.value" class="flex items-center">
                      <input :id="`filter-${section.id}-${optionIdx}`" :name="`${section.id}[]`" v-model="sortValue"  :value="option.value" type="radio" class="h-4 w-4 rounded border-gray-300 text-indigo-600 focus:ring-indigo-500" />
                      <label :for="`filter-${section.id}-${optionIdx}`" class="ml-3 whitespace-nowrap pr-6 text-sm font-medium text-gray-900">{{ option.label }}</label>
                    </div>
                  </form>
                </PopoverPanel>
              </transition>
            </Popover>
          </PopoverGroup>

          <PopoverGroup class="hidden sm:flex sm:items-baseline sm:space-x-8">
            <Popover as="div" v-for="(section, sectionIdx) in filters" :key="section.name" :id="`desktop-menu-${sectionIdx}`" class="relative inline-block text-left">
              <div>
                <PopoverButton class="group inline-flex items-center justify-center text-sm font-medium text-gray-700 hover:text-gray-900">
                  <span>{{ section.name }}</span>
                  <!-- <span v-if="sectionIdx === 0" class="ml-1.5 rounded bg-gray-200 py-0.5 px-1.5 text-xs font-semibold tabular-nums text-gray-700">1</span> -->
                  <ChevronDownIcon class="-mr-1 ml-1 h-5 w-5 flex-shrink-0 text-gray-400 group-hover:text-gray-500" aria-hidden="true" />
                </PopoverButton>
              </div>

              <transition enter-active-class="transition ease-out duration-100" enter-from-class="transform opacity-0 scale-95" enter-to-class="transform opacity-100 scale-100" leave-active-class="transition ease-in duration-75" leave-from-class="transform opacity-100 scale-100" leave-to-class="transform opacity-0 scale-95">
                <PopoverPanel class="absolute right-0 z-10 mt-2 origin-top-right rounded-md bg-white p-4 shadow-2xl ring-1 ring-black ring-opacity-5 focus:outline-none">
                  <form class="space-y-4">
                    <div v-for="(option, optionIdx) in section.options" :key="option.value" class="flex items-center">
                      <input :id="`filter-${section.id}-${optionIdx}`" :name="`${section.id}[]`" v-model="checkedValues"  :value="option.value" type="checkbox" class="h-4 w-4 rounded border-gray-300 text-indigo-600 focus:ring-indigo-500" />
                      <label :for="`filter-${section.id}-${optionIdx}`" class="ml-3 whitespace-nowrap pr-6 text-sm font-medium text-gray-900">{{ option.label }}</label>
                    </div>
                  </form>
                </PopoverPanel>
              </transition>
            </Popover>
          </PopoverGroup>
        </div>
      </section>
    </div>
  </div>
  <div class="bg-white mt-2">
    <div class="mx-auto max-w-7xl overflow-hidden sm:px-6 lg:px-8">
      <div>
        <!-- 검색 오류 페이지 -->
        <div v-if="errorMsg" class="min-h-full bg-white py-16 px-6 sm:py-24 md:grid md:place-items-center lg:px-8">
          <div class="mx-auto max-w-max">
            <main class="sm:flex">
              <div class="sm:ml-6">
                <div class="sm:pl-6">
                  <h1 class="text-4xl font-bold tracking-tight text-gray-900 sm:text-3xl">찾으시는 상품이 없습니다.</h1>
                  <p class="mt-1 text-base text-gray-500">다른 상품을 검색해주세요.</p>
                </div>
                <div class="mt-10 flex space-x-3 sm:border-l sm:border-transparent sm:pl-6">
                  <button @click="home"  type="button" class="inline-flex items-center rounded-md border border-transparent bg-indigo-600 px-4 py-2 text-sm font-medium text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2">홈으로</button>
                  <button @click="items" type="button" class="inline-flex items-center rounded-md border border-transparent bg-indigo-100 px-4 py-2 text-sm font-medium text-indigo-700 hover:bg-indigo-200 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2">전체상품 목록으로</button>
                </div>
              </div>
            </main>
          </div>
        </div>
      </div>
      <div class="-mx-px grid grid-cols-2 border-l border-gray-200 sm:mx-0 md:grid-cols-3 lg:grid-cols-4">
        <div v-for="product in products" :key="product.productId" class="group relative border-r border-b border-gray-200 p-4 sm:p-6">
          <div class="aspect-w-1 aspect-h-1 overflow-hidden rounded-lg bg-gray-200 group-hover:opacity-75">
            <img :src="product.image" :alt="product.image" class="h-full w-full object-cover object-center" />
          </div>
          <div class="pt-10 pb-4 text-center">
            <h3 class="text-sm font-medium text-gray-900">
              <!-- <router-link :to="`/user/items/${ product.productId }`">
                <span aria-hidden="true" class="absolute inset-0" />
                {{ product.name }}
              </router-link> -->
            <div class="space-x-1 text-sm text-gray-500 text-right mb-4">
              <HeartIcon 
                  class="h-6 w-6 flex-shrink-0 inline-block" 
                  aria-hidden="true" />
              <span class="ml-4">{{ product.likeCnt }}</span>
            </div>
              <div @click="$router.push(`/user/items/${ product.productId }`)">
                <span aria-hidden="true" class="absolute inset-0" />
                {{ product.name }}
              </div>
            </h3>
            <p class="mt-4 text-base font-medium text-gray-900">￦{{ Number(product.price).toLocaleString() }}</p>
            <!-- 장바구니 / 구매하기 버튼 -->
            <span class="isolate inline-flex rounded-md shadow-sm mt-4">
              <button type="button" @click="addCart(product.productId)" class="relative inline-flex items-center rounded-l-md border border-gray-300 bg-white px-4 py-2 text-sm font-medium text-gray-700 hover:bg-gray-50 focus:z-10 focus:border-indigo-500 focus:outline-none focus:ring-1 focus:ring-indigo-500">장바구니</button>
              <button type="button" class="relative -ml-px inline-flex items-center rounded-r-md border border-gray-300 bg-white px-4 py-2 text-sm font-medium text-gray-700 hover:bg-gray-50 focus:z-10 focus:border-indigo-500 focus:outline-none focus:ring-1 focus:ring-indigo-500">구매하기</button>
            </span>
          </div>
        </div>
      </div>
      <!-- 페이징 -->
      <div class="flex items-center justify-between border-t border-gray-200 bg-white px-4 py-3 sm:px-6">
        <div class="flex flex-1 justify-between sm:hidden">
          <a href="#" class="relative inline-flex items-center rounded-md border border-gray-300 bg-white px-4 py-2 text-sm font-medium text-gray-700 hover:bg-gray-50">Previous</a>
          <a href="#" class="relative ml-3 inline-flex items-center rounded-md border border-gray-300 bg-white px-4 py-2 text-sm font-medium text-gray-700 hover:bg-gray-50">Next</a>
        </div>
        <div class="hidden sm:flex sm:flex-1 sm:items-center sm:justify-between">
          <div>
            <p class="text-sm text-gray-700">
              {{ ' ' }}
              <span class="font-medium">{{ pageNo }}</span>
              /
              {{ ' ' }}
              <span class="font-medium">{{ totalPages }}</span>
              {{ ' ' }}
              페이지
            </p>
          </div>
          <div v-if="list" class="flex flex-1 justify-between sm:justify-end">
            <button v-if="hasPrev" @click="prevPage" class="relative inline-flex items-center rounded-md border border-gray-300 bg-white px-4 py-2 text-sm font-medium text-gray-700 hover:bg-gray-50">이전</button>
            <button v-if="hasNext" @click="nextPage" class="relative ml-3 inline-flex items-center rounded-md border border-gray-300 bg-white px-4 py-2 text-sm font-medium text-gray-700 hover:bg-gray-50">다음</button>
          </div>
          <div v-else class="flex flex-1 justify-between sm:justify-end">
            <button v-if="hasPrev" @click="searchPrevPage" class="relative inline-flex items-center rounded-md border border-gray-300 bg-white px-4 py-2 text-sm font-medium text-gray-700 hover:bg-gray-50">이전</button>
            <button v-if="hasNext" @click="searchNextPage" class="relative ml-3 inline-flex items-center rounded-md border border-gray-300 bg-white px-4 py-2 text-sm font-medium text-gray-700 hover:bg-gray-50">다음</button>
          </div>
        </div>
      </div>
    </div>
  </div>

  
  </div>
  <!-- {{ checkedValues }}
  {{ keyWord }}
  {{ sortValue }} -->
  <!-- <span class="inline-flex items-center rounded-full bg-gray-100 px-2.5 py-0.5 text-xs font-medium text-gray-800 mr-3">{{ sortValue }}</span>
  <div v-for="check in checkedValues" :key="check.target" class="inline-block">
    <span class="inline-flex items-center rounded-full bg-gray-100 px-2.5 py-0.5 text-xs font-medium text-gray-800 mr-3">{{ check }}</span>
  </div> -->

</template>

<script>
import GetItemsRequest from "@/service/request/GetItemsRequest.js";
import GetSearchRequest from "@/service/request/GetSearchRequest.js";
import AddCartRequest from "@/service/request/AddCartRequest.js";
import {mapGetters, mapMutations} from "vuex";
import ResultCode from "@/service/ResultCode";

export default {
  name: 'UserSearchView',
  components: {
  },
  props: {
  },
  data() {
    return {
      pageNo: 1,
      pageSize: 8,
      totalPages: 0,
      totalCount: 0,
      cateCode: 0,
      products: [],
      cartVo: [],
      categoryList: [],
      checkedValues: [],
      sortValue: '',
      keyWord: '',
      filter: '',
      page: 1,
      searchWord: '',
      isShow : false,  // 검색창
      errorMsg: false, // 에러페이지
      list: true,      // 페이징
    }
  },
  computed: {
    hasNext() {
      return this.pageNo < this.totalPages;
    },
    hasPrev() {
      return this.pageNo > 1;
    },
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
    home() {
      this.$router.replace("/user");
    },
    items() {
      this.$router.go();
      // this.$router.push("/user/search");
    },
    async nextPage() {
      this.pageNo++;
      await this.getItemsList();
    },
    async prevPage() {
      this.pageNo--;
      await this.getItemsList();
    },
    async searchNextPage() {
      this.pageNo++;
      await this.search();
    },
    async searchPrevPage() {
      this.pageNo--;
      await this.search();
    },
    async getItemsList() {
      let req = new GetItemsRequest();
      req.pageNo = this.pageNo;
      req.pageSize = this.pageSize;
      if(this.$route.query.cateCode == null || this.$route.query.cateCode == "") {
        req.cateCode = 0;
      } else {
        req.cateCode = this.$route.query.cateCode;
      }
      try {
        let res = await this.api.getItemsList(req);
        if (res.code === ResultCode.Success) {
          this.list = true;
          this.totalCount = res.totalCount;
          this.pageNo = res.pageNo;
          this.pageSize = res.pageSize;
          this.totalPages = Math.ceil(this.totalCount / this.pageSize);
          this.categoryList = res.categoryList;
          this.products = res.products;
          this.errorMsg = false;
          console.log(res);
        } else { // 메인에서 찾는 상품이 없을 때
          this.products = null;
          this.pageNo = 1;
          this.pageSize = 1;
          this.totalPages = 1;
          this.categoryList = null;
          this.searchWord = null;
          this.errorMsg = true;
        }
      } catch (e) {
        console.error(e);
      }
    },
    async addCart(val) {
      let req = new AddCartRequest();
      req.cnt += 1;
      req.productNum = parseInt(val);
      console.log(req);
      try {
        let res = await this.api.addCart(req);
        if (res.code === ResultCode.Success) {
          console.log(res);
          this.cartVo = res.cartVo;
          this.$router.replace({name: "UserCartView"});
        } else {
          alert(res.message);
        }
      } catch (e) {
        console.error(e);
      }
    },
    async search() {
      let req = new GetSearchRequest();
      req.cateCode = this.checkedValues;
      req.keyWord = this.keyWord;
      req.page = this.pageNo;
      req.filter = this.sortValue;
      this.isShow = true;
      console.log(req);
      try {
        let res = await this.api.search(req);
        if (res.code === ResultCode.Success) {
          this.list = false;
          this.totalCount = res.totalCount;
          this.products = res.products;
          this.pageNo = res.pageNo;
          this.pageSize = res.pageSize;
          this.totalPages = Math.ceil(this.totalCount / this.pageSize);
          this.categoryList = res.categoryList;
          this.errorMsg = false;
          this.searchWord = this.keyWord;
          if( this.searchWord == "") { // 검색어가 없을 때
            alert("검색어를 입력해주세요.");
            this.products = null;
            this.pageNo = 1;
            this.pageSize = 1;
            this.totalPages = 1;
            this.totalCount = 0;
            this.categoryList = null;
            this.searchWord = null;
            this.errorMsg = true;
          }       
          console.log(res);
        } else { // 찾는 상품이 없을 때
          this.totalCount = 0;
          this.searchWord = this.keyWord;
          this.errorMsg = true;
        }
      } catch (e) {
        console.error(e);
      }
    },
  },
  beforeDestroy(){  
  },
  created() {
    console.log("UserSearchView.vue..", this.accessToken);
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
      this.getItemsList();
      // this.search();
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
import {
  Dialog,
  DialogPanel,
  Disclosure,
  DisclosureButton,
  DisclosurePanel,
  Menu,
  MenuButton,
  MenuItem,
  MenuItems,
  Popover,
  PopoverButton,
  PopoverGroup,
  PopoverPanel,
  TransitionChild,
  TransitionRoot,
} from '@headlessui/vue'

import { XMarkIcon } from '@heroicons/vue/24/outline'
import { ChevronDownIcon } from '@heroicons/vue/20/solid'
import { MagnifyingGlassIcon } from '@heroicons/vue/20/solid'
import { HeartIcon } from '@heroicons/vue/24/outline'
import { ChevronLeftIcon, ChevronRightIcon } from '@heroicons/vue/20/solid'

// const sortOptions = [
//   { name: '좋아요 높은순', href: '#' },
//   { name: '좋아요 낮은순', href: '#' },
//   { name: '이름 오름차순', href: '#' },
//   { name: '이름 내림차순', href: '#' },
//   { name: '가격 높은순', href: '#' },
//   { name: '가격 낮은순', href: '#' },
// ]

const sort = [
  {
    id: 'sort',
    name: '정렬',
    options: [
      { value: '', label: '선택 안함' },
      { value: 'likeDESC', label: '좋아요 높은순' },
      { value: 'likeASC', label: '좋아요 낮은순' },
      { value: 'nameASC', label: '이름 오름차순' },
      { value: 'nameDESC', label: '이름 내림차순' },
      { value: 'priceDESC', label: '가격 높은순' },
      { value: 'priceASC', label: '가격 낮은순' },
    ],
  },
]
const filters = [
  {
    id: 'digital',
    name: '디지털',
    options: [
      { value: '101', label: '핸드폰' },
      { value: '102', label: '태블릿' },
      { value: '103', label: '스마트워치' },
      { value: '104', label: '블루투스 이어폰' },
      { value: '105', label: '악세서리' },
    ],
  },
  {
    id: 'brand',
    name: '제조사',
    options: [
      { value: '201', label: '삼성' },
      { value: '202', label: '애플' },
      { value: '203', label: '기타' },
    ],
  },
  {
    id: 'computer',
    name: '컴퓨터',
    options: [
      { value: '301', label: '모니터' },
      { value: '302', label: '노트북' },
    ],
  },
]

const open = ref(false)
</script>

<style>

</style>