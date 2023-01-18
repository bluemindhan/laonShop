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
  <form class="space-y-8 divide-y divide-gray-200">
    <div class="space-y-8 divide-y divide-gray-200 p-20">
      <div class="pt-8">
        <div>
          <h3 class="text-lg font-medium leading-6 text-gray-900">회원정보수정</h3>
          <p class="mt-1 text-sm text-gray-500">여기는 회원정보수정 페이지 입니다.</p>
        </div>
        <div class="mt-6 grid grid-cols-1 gap-y-6 gap-x-4 sm:grid-cols-6">

          <div class="sm:col-span-3">
            <label for="name" class="block text-sm font-medium text-gray-700">이름</label>
            <div class="mt-1">
              <input 
              type="text" 
              name="name" 
              id="name" 
              autocomplete="name"
              :value="userInfo.name"
              class="block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" 
              required/>
            </div>
          </div>

          <!-- <div class="sm:col-span-4" v-if="isSeller">
            <label for="name" class="block text-sm font-medium text-gray-700">판매자명</label>
            <input
                v-model="userInfo.name"
                id="name"
                name="name"
                type="name"
                required=""
                class="block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                placeholder="판매자명"
            />
          </div> -->

          <div class="sm:col-span-4">
            <label for="email" class="block text-sm font-medium text-gray-700">이메일/아이디</label>
            <div class="mt-1">
              <input 
              id="email" 
              name="email" 
              type="email" 
              autocomplete="email" 
              class="block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" 
              :value="userInfo.email"
              required/>
            </div>
          </div>

          <!-- <div class="sm:col-span-4">
            <label for="password" class="block text-sm font-medium text-gray-700">비밀번호</label>
            <div class="mt-1">
              <input id="password" name="password" type="password" autocomplete="password" class="block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" />
            </div>
          </div> -->

          <div class="sm:col-span-3">
            <label for="gender" class="block text-sm font-medium text-gray-700">성별</label>
            <div class="mt-1">
              <select id="gender" name="gender" autocomplete="gender" class="block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm">
                <option value="">성별을 선택해주세요</option>
                <option value="남자">남자</option>
                <option value="여자">여자</option>
                <option :value="userInfo.gender" selected v-show="false">{{ userInfo.gender }}</option>
              </select>
            </div>
          </div>

          <div class="sm:col-span-4">
            <label for="birth" class="block text-sm font-medium text-gray-700">생년월일</label>
            <div class="mt-1">
              <input 
              type="date" 
              name="birth" 
              id="birth" 
              autocomplete="birth" 
              class="block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" 
              :value="userInfo.birth"
              required/>
            </div>
          </div>

          <div class="sm:col-span-4">
            <label for="phone" class="block text-sm font-medium text-gray-700">전화번호</label>
            <div class="mt-1">
              <input 
              type="text" 
              name="phone" 
              id="phone" 
              autocomplete="phone" 
              class="block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" 
              :value="userInfo.phone"
              placeholder="전화번호"
              maxlength="13"
              required/>
            </div>
          </div>
        
          <!-- <div class="sm:col-span-6">
            <label for="street-address" class="block text-sm font-medium text-gray-700">Street address</label>
            <div class="mt-1">
              <input type="text" name="street-address" id="street-address" autocomplete="street-address" class="block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" />
            </div>
          </div>

          <div class="sm:col-span-2">
            <label for="city" class="block text-sm font-medium text-gray-700">City</label>
            <div class="mt-1">
              <input type="text" name="city" id="city" autocomplete="address-level2" class="block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" />
            </div>
          </div>

          <div class="sm:col-span-2">
            <label for="region" class="block text-sm font-medium text-gray-700">State / Province</label>
            <div class="mt-1">
              <input type="text" name="region" id="region" autocomplete="address-level1" class="block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" />
            </div>
          </div>

          <div class="sm:col-span-2">
            <label for="postal-code" class="block text-sm font-medium text-gray-700">ZIP / Postal code</label>
            <div class="mt-1">
              <input type="text" name="postal-code" id="postal-code" autocomplete="postal-code" class="block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" />
            </div>
          </div> -->

          <div class="sm:col-span-4">
            <label for="password" class="block text-sm font-medium text-gray-700">비밀번호 확인</label>
            <div class="mt-1">
              <input 
              id="password" 
              name="password" 
              type="password" 
              autocomplete="password" 
              class="block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" 
              required/>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="pt-5 p-20">
      <div class="flex justify-center">
        <button type="button" 
        class="rounded-md border border-gray-300 bg-white py-2 px-4 text-sm font-medium text-gray-700 shadow-sm hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
        @click="$router.back()">취소</button>
        <button @click="save" type="button" class="ml-3 inline-flex justify-center rounded-md border border-transparent bg-indigo-600 py-2 px-4 text-sm font-medium text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
        >저장</button>
      </div>
    </div>
  </form>
</template>

<script>
import {mapGetters, mapMutations} from "vuex";
import GetProfileRequest from "@/service/request/GetProfileRequest";
import ResultCode from "@/service/ResultCode";

export default {
  name: "ProfileView",
  components: {},
  props: {},
  data() {
    return {
      userInfo: {},
      password: '',
      name: '',
      email: '',
      gender: '',
      birth: '',
      phone: '',
      // sellerName: "",
      // isSeller: false,
    };
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
    async getProfile() {
      console.log("getProfile");

      let req = new GetProfileRequest();
      req.accessToken = this.accessToken;

      try {
        let res = await this.api.getProfile(req);
        if (res.code == ResultCode.Success) {
          this.userInfo = res.userInfo;
          console.log(res);
        }
      } catch (e) {
        console.error(e);
      }
    },
   
   },
   beforeDestroy() {
      console.log("beforeDestroy");
   },
  created() {
    console.log("ProfileView.vue..", this.accessToken);
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
      this.getProfile();
    }  
  },
  mounted() {
    console.log("mounted");
  },
  beforeUnmount() {
    console.log("beforeUnmount");
  },
};
</script>

<style>

</style>