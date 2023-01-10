<template>
    <div class="mt-1" style="width: 1300px; ">
      <div class="bg-white">
      <div class="mx-auto max-w-7xl py-16 px-4 sm:px-6 lg:px-8 lg:pb-24">
        <div class="max-w-xl">
          <h1 class="text-2xl font-bold tracking-tight text-gray-900 sm:text-3xl">Product List</h1>
          <p class="mt-2 text-sm text-gray-500">Check all products and update details or delete products.</p>      </div>
        <div class="mt-16">
          <div class="space-y-20">
            <div v-for="order in orders" :key="order.number">
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
                  <tr v-for="product in order.products" :key="product.id">
                    <td class="py-6 pr-8">
                      <div class="flex items-center">
                        <img :src="product.imageSrc" :alt="product.imageAlt" class="mr-6 h-16 w-16 rounded object-cover object-center" />
                        <div>
                          <div class="font-medium text-gray-900">{{ product.name }}</div>
                          <div class="mt-1 sm:hidden">{{ product.price }}</div>
                        </div>
                      </div>
                    </td>
                    <td class="hidden py-6 pr-8 sm:table-cell">{{ product.price }} 원</td>
                    <td class="hidden py-6 pr-8 sm:table-cell">{{ product.status }}</td>
                    <td class="hidden py-6 pr-8 sm:table-cell text-red-600">{{ product.count }} 개</td>
                    <td class="whitespace-nowrap py-6 text-right font-medium">
                      <a :href="product.href" class="text-indigo-600">
                        <span class="hidden lg:inline">수정</span><span class="sr-only"></span>
                      </a>
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
  
  <script setup>
  const orders = [
    {
      number: 'WU88191111',
      date: 'January 22, 2021',
      datetime: '2021-01-22',
      invoiceHref: '#',
      products: [
        {
          id: 1,
          name: 'iPhone 13',
          href: '#',
          price: '1,090,000',
          status: 'Jan 25, 2021',
          count: '43',
          imageSrc: 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/iphone-13-finish-select-202207-6-1inch-pink?wid=2560&hei=1440&fmt=p-jpg&qlt=80&.v=1657641867367',
          imageAlt: 'Detail of mechanical pencil tip with machined black steel shaft and chrome lead tip.',
        },
        {
          id: 2,
          name: 'AirPods(3세대)',
          href: '#',
          price: '259,000',
          status: 'Mar 25, 2022',
          count: '25',
          imageSrc: 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/MME73?wid=572&hei=572&fmt=jpeg&qlt=95&.v=1632861342000',
          imageAlt: 'Detail of mechanical pencil tip with machined black steel shaft and chrome lead tip.',
        },
        // More products...
      ],
    },
    // More orders...
  ]
  </script>
  
  <script>
  import GetProductListRequest from "../../service/request/GetProductListRequest";
  
  export default {
    name: "SellerProductsView",
    components: {},
    props: {},
    data() {
      return {
        pageNo: 1,
        pageSize: 10,
        totalPages: 0,
        totalCount: 0,
        products: [],
      };
    },
    computed: {
      hasNext() {
        return this.pageNo < this.totalPages;
      },
      hasPrev() {
        return this.pageNo > 1;
      },
    },
    methods: {
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
          this.products = res.productList;
          console.log(res);
        } catch (e) {
          console.error(e);
        }
      },
    },
    created() {
      this.getProductList();
    },
    mounted() {
    },
    beforeUnmount() {
    },
  };
  </script>
  <style></style>
  