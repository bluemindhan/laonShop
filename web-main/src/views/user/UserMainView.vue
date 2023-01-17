<template>
  <div class="bg-white mt-2">
    <div class="mx-auto max-w-7xl overflow-hidden sm:px-6 lg:px-8">
      <div>
        
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
              <div @click="$router.push(`/user/items/${ product.productId }`)">
                <span aria-hidden="true" class="absolute inset-0" />
                {{ product.name }}
              </div>
            </h3>
            <p class="mt-4 text-base font-medium text-gray-900">{{ product.price }}</p>
            <!-- 장바구니 / 구매하기 버튼 -->
            <span class="isolate inline-flex rounded-md shadow-sm mt-4">
              <router-link :to="`/user/cart`">
                <button type="button" class="relative inline-flex items-center rounded-l-md border border-gray-300 bg-white px-4 py-2 text-sm font-medium text-gray-700 hover:bg-gray-50 focus:z-10 focus:border-indigo-500 focus:outline-none focus:ring-1 focus:ring-indigo-500">장바구니</button>
              </router-link>
              <button type="button" class="relative -ml-px inline-flex items-center rounded-r-md border border-gray-300 bg-white px-4 py-2 text-sm font-medium text-gray-700 hover:bg-gray-50 focus:z-10 focus:border-indigo-500 focus:outline-none focus:ring-1 focus:ring-indigo-500">구매하기</button>
            </span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import GetItemsRequest from "@/service/request/GetItemsRequest.js";
import {mapGetters, mapMutations} from "vuex";
import ResultCode from "@/service/ResultCode";

export default {
  name: 'UserMainView',
  components: {
  },
  props: {
  },
  data() {
    return {
      products: [],
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
    async getItemsList() {
      let req = new GetItemsRequest();
      try {
        let res = await this.api.getItemsList(req);
        if (res.code === ResultCode.Success) {
          this.products = res.products;
          console.log(res);
        }
      } catch (e) {
        console.error(e);
      }
    },
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
      this.getItemsList();
    }  
  },
  mounted() {
  },
  beforeUnmount() {
  }
}
</script>

<script setup>

</script>
<style>
</style>