<!--
  This example requires some changes to your config:

  ```
  // tailwind.config.js
  module.exports = {
    // ...
    plugins: [
      // ...
      require('@tailwindcss/forms'),
    ],
  }
  ```
-->
<template>
  <div class="mt-8 space-y-8 divide-y divide-gray-200">
    <div class="space-y-8 divide-y divide-gray-200">
      <div style="width: 800px">
        <div>
          <h3 class="text-lg font-medium leading-6 text-gray-900">상품 등록</h3>
        </div>
        <div class="mt-6 grid grid-cols-1 gap-y-6 gap-x-4 sm:grid-cols-6">
          <div class="sm:col-span-12">
            <label for="product" class="block text-sm font-medium text-gray-700">상품명</label>
            <div class="mt-1 flex rounded-md shadow-sm">
              <input type="text" name="product" id="product" v-model="productName" class="block w-full min-w-0 flex-1 rounded-md border-gray-300 focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" />
            </div>
          </div>

          <div class="sm:col-span-12">
            <label for="about" class="block text-sm font-medium text-gray-700">상품설명</label>
            <div class="mt-1">
              <textarea id="about" name="about" rows="3" v-model="productDesc" class="block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" />
            </div>
          </div>

          <div class="sm:col-span-12">
            <label for="price" class="block text-sm font-medium text-gray-700">상품가격</label>
            <div class="mt-1 flex rounded-md shadow-sm">
              <input type="text" name="price" id="price" v-model.number="productPrice" class="block w-full min-w-0 flex-1 rounded-md border-gray-300 focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" />
            </div>
          </div>

          <div class="sm:col-span-12">
            <label for="count" class="block text-sm font-medium text-gray-700">재고수량</label>
            <div class="mt-1 flex rounded-md shadow-sm">
              <input type="text" name="count" id="count" v-model.number="productCount" class="block w-full min-w-0 flex-1 rounded-md border-gray-300 focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" />
            </div>
          </div>

          <div class="sm:col-span-12">
            <label class="block text-sm font-medium text-gray-700">상품 이미지</label>
            <div class="grid grid-cols-4 gap-2">
              <div v-for="item in imgList" :key="item.name" class="w-32 h-32 bg-red-100">
                <img :src="item.src" :alt="item.name" class="w-32 h-32" />
              </div>
            </div>
            <div class="mt-1 flex justify-center rounded-md border-2 border-dashed border-gray-300 px-6 pt-5 pb-6">
              <div class="space-y-1 text-center">
                <svg class="mx-auto h-12 w-12 text-gray-400" stroke="currentColor" fill="none" viewBox="0 0 48 48" aria-hidden="true">
                  <path d="M28 8H12a4 4 0 00-4 4v20m32-12v8m0 0v8a4 4 0 01-4 4H12a4 4 0 01-4-4v-4m32-4l-3.172-3.172a4 4 0 00-5.656 0L28 28M8 32l9.172-9.172a4 4 0 015.656 0L28 28m0 0l4 4m4-24h8m-4-4v8m-12 4h.02" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" />
                </svg>
                <div class="flex text-sm text-gray-600">
                  <label for="file-upload" class="relative cursor-pointer rounded-md bg-white font-medium text-indigo-600 focus-within:outline-none focus-within:ring-2 focus-within:ring-indigo-500 focus-within:ring-offset-2 hover:text-indigo-500">
                    <span>Upload a file</span>
                    <input id="file-upload" name="file-upload" type="file" class="sr-only" @change="onFilePicked" />
                  </label>
                  <p class="pl-1">or drag and drop</p>
                </div>
                <p class="text-xs text-gray-500">PNG, JPG, GIF up to 10MB</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="pt-5">
      <div class="flex justify-end">
        <button type="button" class="rounded-md border border-gray-300 bg-white py-2 px-4 text-sm font-medium text-gray-700 shadow-sm hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2">Cancel</button>
        <button @click="save" class="ml-3 inline-flex justify-center rounded-md border border-transparent bg-indigo-600 py-2 px-4 text-sm font-medium text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2">Save</button>
      </div>
    </div>
  </div>
</template>

<script>
import AddFilesRequest from "../../service/request/AddFilesRequest";
import ResultCode from "../../service/ResultCode";
import AddProductRequest from "../../service/request/AddProductRequest";

export default {
  name: "SellerProductsNewView",
  components: {},
  props: {},
  data() {
    return {
      productName: "",
      productDesc: "",
      productPrice: 0,
      productCount: 0,
      imgList: [],
    };
  },
  computed: {},
  methods: {
    onFilePicked(e) {
      const files = e.target.files;
      let fileInfo = files[0];

      if (fileInfo !== undefined) {
        const fr = new FileReader();
        fr.readAsDataURL(fileInfo);
        const vm = this;
        fr.onload = () => {
          if (fileInfo.size > 1024 * 1024 * 1) {
            alert("파일은 1MB 까지만 지원합니다.");
            return;
          }
          let b64 = fr.result;
          let img = new Image();
          img.onload = function () {
            let item = {
              name: fileInfo.name,
              height: img.height,
              width: img.width,
              size: fileInfo.size,
              src: b64,
            };
            console.log(item);
            vm.imgList.push(item);
          };
          img.src = b64;
        };
      }
    },
    async save() {
      if (this.productName === "") {
        alert("상품명을 입력해주세요.");
        return;
      }

      if (this.productDesc === "") {
        alert("상품설명을 입력해주세요.");
        return;
      }

      if (this.productPrice <= 0) {
        alert("상품가격을 입력해주세요.");
        return;
      }

      if (this.productCount <= 0) {
        alert("재고수량을 입력해주세요.");
        return;
      }

      if (this.imgList.length === 0) {
        alert("상품 이미지를 등록해주세요.");
        return;
      }

      let req = new AddProductRequest();
      req.productName = this.productName;
      req.productDesc = this.productDesc;
      req.productPrice = this.productPrice;
      req.productCount = this.productCount;  // 재고수량 추가
      req.imageList = [];
      this.imgList.forEach((item) => {
        req.imageList.push(item.src);
      });

      try {
        let res = await this.api.addProduct(req);
        if (res.resultCode === ResultCode.SUCCESS) {
          alert("상품이 등록되었습니다.");
          this.$router.replace("/seller/products");
        } else {
          alert(res.resultMsg);
        }
      } catch (err) {
        console.error(err);
      }
    },
  },
  created() {},
  mounted() {},
  beforeUnmount() {},
};
</script>
<style></style>
