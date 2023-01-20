<template>
  <div class="mt-10">
    <div>
      <h1 class="text-2xl font-bold tracking-tight text-gray-900 sm:text-3xl">상품 등록</h1>
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
            <label class="block text-sm font-medium text-gray-700">상품 이미지</label>
            <div class="grid grid-cols-4 gap-2">
              <div v-for="item in imgList" :key="item.name" class="w-32 h-32 bg-red-100">
                <img :src="item.src" :alt="item.name" class="w-32 h-32" @click="removeImage" :name="item.name"/>
              </div>
            </div>
            <div class="mt-1 flex justify-center rounded-md border-2 border-dashed border-gray-300 px-6 pt-5 pb-6">
              <div class="space-y-1 text-center">
                <svg class="mx-auto h-12 w-12 text-gray-400" stroke="currentColor" fill="none" viewBox="0 0 48 48" aria-hidden="true">
                  <path d="M28 8H12a4 4 0 00-4 4v20m32-12v8m0 0v8a4 4 0 01-4 4H12a4 4 0 01-4-4v-4m32-4l-3.172-3.172a4 4 0 00-5.656 0L28 28M8 32l9.172-9.172a4 4 0 015.656 0L28 28m0 0l4 4m4-24h8m-4-4v8m-12 4h.02" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" />
                </svg>
                <div class="flex text-sm text-gray-600">
                  <span class="pl-1 pr-1">Click to</span>
                  <label for="file-upload" class="relative cursor-pointer rounded-md bg-white font-medium text-indigo-600 focus-within:outline-none focus-within:ring-2 focus-within:ring-indigo-500 focus-within:ring-offset-2 hover:text-indigo-500">
                    <span>Upload a file</span>
                    <input id="file-upload" name="file-upload" type="file" class="sr-only" v-if="uploadReady" @change="onFilePicked" />
                  </label>
                </div>
                <p class="text-xs text-gray-500">PNG, JPG, GIF up to 10MB</p>
              </div>
            </div>
          </div>
        </div>
    </div>

    <div class="pt-5">
      <div class="flex justify-end">
        <button @click="$router.back()" type="button" class="rounded-md border border-gray-300 bg-white py-2 px-4 text-sm font-medium text-gray-700 shadow-sm hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2">Cancel</button>
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
      imgList: [],
      uploadReady: true,
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
    removeImage(e) {
      const name = e.target.getAttribute('name');
      this.imgList = this.imgList.filter(data => data.name != name);
      // 렌더링 되고 마지막에 실행이 되도록 $nextTick()을 사용한다.
      this.$nextTick(() => {
        this.uploadReady = true;
      })
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

      if (this.imgList.length === 0) {
        alert("상품 이미지를 등록해주세요.");
        return;
      }

      let req = new AddProductRequest();
      req.productName = this.productName;
      req.productDesc = this.productDesc;
      req.productPrice = this.productPrice;
      req.imageList = [];
      this.imgList.forEach((item) => {
        req.imageList.push(item.src);
      });

      try {
        let res = await this.api.addProduct(req);
        if (res.resultCode === ResultCode.SUCCESS) {
          alert("상품 등록 성공");
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
<style>
</style>
