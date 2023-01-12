<template>
    <div class="mt-1" style="width: 1300px; ">
      <div class="bg-white">
      <div class="mx-auto max-w-7xl py-16 px-4 sm:px-6 lg:px-8 lg:pb-24">
        <div class="max-w-xl">
          <h1 class="text-2xl font-bold tracking-tight text-gray-900 sm:text-3xl">Product List</h1>
          <p class="mt-2 text-sm text-gray-500">Check all products and update details or delete products.</p>      </div>
        <div class="mt-16">
          <div class="space-y-20">
            <div v-for="product in productList" :key="product.productId">
              <table class="mt-4 w-full text-gray-500 sm:mt-6">
                <thead class="sr-only text-left text-sm text-gray-500 sm:not-sr-only">
                  <tr>
                    <th scope="col" class="py-3 pr-8 font-normal sm:w-2/5 lg:w-1/3">상품명</th>
                    <th scope="col" class="hidden w-1/5 py-3 pr-8 font-normal sm:table-cell">가격</th>
                    <th scope="col" class="hidden py-3 pr-8 font-normal sm:table-cell">등록날짜</th>
                    <th scope="col" class="hidden py-3 pr-8 font-normal sm:table-cell">판매수량</th>
                    <th scope="col" class="w-0 py-3 text-right font-normal"></th>
                  </tr>
                </thead>
                <tbody class="divide-y divide-gray-200 border-b border-gray-200 text-sm sm:border-t">
                  <tr v-for="product in productList" :key="product.productId">
                    <td class="py-6 pr-8">
                      <div class="flex items-center">
                        <img :src="product.image" :alt="product.image" class="mr-6 h-16 w-16 rounded object-cover object-center" />
                        <div>
                          <div class="font-medium text-gray-900">{{ product.prdtNo }}</div>
                          <div class="mt-1 sm:hidden">{{ product.prdtPrce }}</div>
                        </div>
                      </div>
                    </td>
                    <td class="hidden py-6 pr-8 sm:table-cell">{{ product.prdtPrce }} 원</td>
                    <td class="hidden py-6 pr-8 sm:table-cell">
                      <!-- {{ product.crtDt }} -->
                    </td>
                    <!-- <td class="hidden py-6 pr-8 sm:table-cell text-red-600">{{ product.count }} 개</td> -->
                    <td class="whitespace-nowrap py-6 text-right font-medium">
                      <router-link :to="`/seller/product/${ product.productId }`">
                        <a :href="product.href" class="text-indigo-600">
                          <span class="hidden lg:inline">수정</span><span class="sr-only"></span>
                        </a>
                      </router-link>
                       / 
                      <a :href="product.href" class="text-indigo-600">
                        <span class="hidden lg:inline">삭제</span><span class="sr-only"></span>
                      </a>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
      
      <nav class="flex items-center justify-between border-t border-gray-200 bg-white px-4 py-3 sm:px-6"
           aria-label="Pagination">
        <div class="hidden sm:block">
          <p class="text-sm text-gray-700">
            Showing
            {{ " " }}
            <span class="font-medium">{{ pageNo }}</span>
            of
            {{ " " }}
            <span class="font-medium">{{ totalPages }}</span>
            {{ " " }}
            pages
          </p>
        </div>
        <div class="flex flex-1 justify-between sm:justify-end">
          <button v-if="hasPrev" @click="prevPage"
                  class="relative inline-flex items-center rounded-md border border-gray-300 bg-white px-4 py-2 text-sm font-medium text-gray-700 hover:bg-gray-50">
            Previous
          </button>
          <button v-if="hasNext" @click="nextPage"
                  class="relative ml-3 inline-flex items-center rounded-md border border-gray-300 bg-white px-4 py-2 text-sm font-medium text-gray-700 hover:bg-gray-50">
            Next
          </button>
        </div>
      </nav>
    </div>
</template>
  
<script>
  import GetProductListRequest from "../../service/request/GetProductListRequest";
  import {mapGetters, mapMutations} from "vuex";
  import ResultCode from "@/service/ResultCode";

  export default {
    name: "SellerProductsView",
    components: {
    },
    props: {
    },
    data() {
      return {
        pageNo: 1,
        pageSize: 10,
        totalPages: 0,
        totalCount: 0,
        productList: [],
      };
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
      }),
      detail(prdtNo) {
        console.log("detail", prdtNo);
      },
      async nextPage() {
        this.pageNo++;
        await this.getProductList();
      },
      async prevPage() {
        this.pageNo--;
        await this.getProductList();
      },
      async getProductList() {
        let req = new GetProductListRequest();
        req.pageNo = this.pageNo;
        req.pageSize = this.pageSize;
        try {
          let res = await this.api.getProductList(req);
          this.totalCount = res.totalCount;
          this.pageNo = res.pageNo;
          this.pageSize = res.pageSize;
          this.totalPages = Math.ceil(this.totalCount / this.pageSize);
          this.productList = res.productList;
          if (res.code === ResultCode.Success) {
          this.productList = res.productList;
          console.log(res);
          }
        } catch (e) {
          console.error(e);
        }
      },
    },
    created() {
      if (this.accessToken == null || this.accessToken == "") {
      // accessToken이 없으면 로그인 페이지로 이동한다.
      this.$router.replace({name: "SignInView"});
      } else {
      // accessToken이 있으면 상품목록을 가져온다.
      this.api.setAccessToken(this.accessToken);
      this.getProductList();
      }
    },
    mounted() {
    },
    beforeUnmount() {
    },
  };
</script>

<style>

</style>
  