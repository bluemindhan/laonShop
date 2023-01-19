<template>
  <div aria-live="assertive" class="pointer-events-none fixed inset-0 flex items-end px-4 py-6 sm:items-start sm:p-6">
      <div class="flex w-full flex-col items-center space-y-4 sm:items-end">
        <!-- Notification panel, dynamically insert this into the live region when it needs to be displayed -->
        <transition enter-active-class="transform ease-out duration-300 transition" enter-from-class="translate-y-2 opacity-0 sm:translate-y-0 sm:translate-x-2" enter-to-class="translate-y-0 opacity-100 sm:translate-x-0" leave-active-class="transition ease-in duration-100" leave-from-class="opacity-100" leave-to-class="opacity-0">
          <div v-if="del" class="pointer-events-auto w-full max-w-sm overflow-hidden rounded-lg bg-white shadow-lg ring-1 ring-black ring-opacity-5">
            <div class="p-4">
              <div class="flex items-start">
                <div class="flex-shrink-0">
                  <CheckCircleIcon class="h-6 w-6 text-green-400" aria-hidden="true" />
                </div>
                <div class="ml-3 w-0 flex-1 pt-0.5">
                  <p class="text-sm font-medium text-gray-900">상품 삭제 성공</p>
                  <!-- <p class="mt-1 text-sm text-gray-500">Anyone with a link can now view this file.</p> -->
                </div>
                <div class="ml-4 flex flex-shrink-0">
                  <button type="button" @click="del = false" class="inline-flex rounded-md bg-white text-gray-400 hover:text-gray-500 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2">
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

    <div class="mt-1" style="width: 1300px; ">
      <div class="bg-white">
      <div class="mx-auto max-w-7xl py-16 px-4 sm:px-6 lg:px-8 lg:pb-24">
        <div class="max-w-xl">
          <h1 class="text-2xl font-bold tracking-tight text-gray-900 sm:text-3xl">상품 목록</h1>
        </div>
        <div class="mt-16">
          <div class="space-y-20">
              <table class="mt-4 w-full text-gray-500 sm:mt-6">
                <thead class="sr-only text-left text-sm text-gray-500 sm:not-sr-only">
                  <tr>
                    <th scope="col" class="py-3 pr-8 font-normal sm:w-2/5 lg:w-1/3">상품명</th>
                    <th scope="col" class="hidden w-1/5 py-3 pr-8 font-normal sm:table-cell">가격</th>
                    <th scope="col" class="hidden py-3 pr-8 font-normal sm:table-cell">등록날짜</th>
                    <th scope="col" class="hidden py-3 pr-8 font-normal sm:table-cell">수정날짜</th>
                    <th scope="col" class="w-0 py-3 text-right font-normal"></th>
                  </tr>
                </thead>
                <tbody class="divide-y divide-gray-200 border-b border-gray-200 text-sm sm:border-t">
                  <tr v-for="product in productList" :key="product.productId">
                    <td class="py-6 pr-8">
                      <div class="flex items-center">
                        <div v-for="item in productList.imageList" :key="item.image">
                          <img :src="product.image" :alt="item.image" class="mr-6 h-16 w-16 rounded object-cover object-center" />
                        </div>
                        <div>
                          <div class="font-medium text-gray-900">{{ product.prdtNm }} 
                            <input type="hidden" :value="product.prdtNo">
                          </div>
                        </div>
                      </div>
                    </td>
                    <td class="hidden py-6 pr-8 sm:table-cell">{{ product.prdtPrce }} 원</td>
                    <td class="hidden py-6 pr-8 sm:table-cell">{{ product.crtDt }}</td>
                    <td class="hidden py-6 pr-8 sm:table-cell">{{ product.updDt }}</td>
                    <td class="whitespace-nowrap py-6 text-right font-medium">
                      <router-link :to="`/seller/product/${ product.prdtNo }`">
                        <button type="button" @click="update" 
                        class="ml-4 flex items-center justify-center rounded-md py-3 px-3 text-purple-400 hover:bg-purple-100 hover:text-purple-500"
                        >수정
                        </button>
                      </router-link>
                      <button type="button" @click="deleteProduct" 
                        class="ml-4 flex items-center justify-center rounded-md py-3 px-3 text-purple-400 hover:bg-purple-100 hover:text-purple-500"
                      >삭제
                      </button>
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
</template>
  
<script>
  import GetProductListRequest from "../../service/request/GetProductListRequest";
  import {mapGetters, mapMutations} from "vuex";
  import ResultCode from "@/service/ResultCode";
  import DeleteProductRequest from '../../service/request/DeleteProductRequest';

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
        productList: {
          imageList: [],
        },
        del: false,
      };
    },
    filters: {
      comma (val) {
        return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
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
      }),
      detail(prdtNo) {
        console.log("detail", prdtNo);
      },
      async nextPage() {
        this.pageNo++;
        await this.ProductList();
      },
      async prevPage() {
        this.pageNo--;
        await this.ProductList();
      },
      async ProductList() {
        let req = new GetProductListRequest();
        req.pageNo = this.pageNo;
        req.pageSize = this.pageSize;
        console.log(req);
        try {
          let res = await this.api.getProductsList(req);
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
          console.log(e);
        }
      },
      async deleteProduct() {
        let req = new DeleteProductRequest();
        req.productNum = this.productList.prdtNm;
        console.log(req);
        try {
          let res = await this.api.deleteProduct(req);
          console.log(res);
          if (res.code === ResultCode.Success) {
            console.log(res);
            this.del = true;
          } else {
            alert(res.message);
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
      this.ProductList();
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
  