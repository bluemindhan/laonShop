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
 <nav class="flex justify-center mt-20" aria-label="Breadcrumb">
    <ol role="list" class="flex items-center space-x-4">
      <li>
        <div>
          <a href="#" class="text-gray-400 hover:text-gray-500">
            <HomeIcon class="h-5 w-5 flex-shrink-0" aria-hidden="true" />
            <span class="sr-only">Home</span>
          </a>
        </div>
      </li>
      <li v-for="page in pages" :key="page.name">
        <div class="flex items-center">
          <svg class="h-5 w-5 flex-shrink-0 text-gray-300" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 20 20" aria-hidden="true">
            <path d="M5.555 17.776l8-16 .894.448-8 16-.894-.448z" />
          </svg>
          <router-link :to="page.href" class="ml-4 text-sm font-medium text-gray-500 hover:text-gray-700" :aria-current="page.current ? 'page' : undefined">{{ page.name }}</router-link>
        </div>
      </li>
    </ol>
  </nav>
  <form class="space-y-8 divide-y divide-gray-200 mx-40">
    <div class="space-y-8 divide-y divide-gray-200 p-20 mx-40">
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
              required>
            </div>
          </div>
          
          <div class="sm:col-span-4">
            <label for="email" class="block text-sm font-medium text-gray-700">이메일</label>
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
                <option selected v-show="false">{{ userInfo.gender }}</option>
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
        <button @click="updateProfile" type="submit" class="ml-3 inline-flex justify-center rounded-md border border-transparent bg-indigo-600 py-2 px-4 text-sm font-medium text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
        >저장</button>
      </div>
    </div>
  </form>
</template>

<script>
import {mapGetters, mapMutations} from "vuex";
import GetProfileRequest from "@/service/request/GetProfileRequest.js";
import UpdateProfileRequest from "@/service/request/UpdateProfileRequest.js";
import ResultCode from "@/service/ResultCode";

export default {
  name: "ProfileView",
  components: {},
  props: {},
  data() {
    return {
      userInfo: {},
      // password: '',
      // name: '',
      // email: '',
      // gender: '',
      // birth: '',
      // phone: '',
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
    async updateProfile() {
      if (document.getElementById("name").value == "") {
        alert("이름을 입력해주세요.");
        return;
      }

      if (document.getElementById("email").value == "") {
        alert("이메일을 입력해주세요.");
        return;
      }

      if (document.getElementById("password").value == "") {
        alert("비밀번호를 입력해주세요.");
        return;
      }

      if (document.getElementById("phone").value == "") {
        alert("전화번호를 입력해주세요.");
        return;
      }

      if (document.getElementById("birth").value == "") {
        alert("생년월일을 입력해주세요.");
        return;
      }

      if (document.getElementById("gender").value == "") {
        alert("성별을 선택해주세요.");
        return;
      }
      
      let req = new UpdateProfileRequest();
      let target = document.getElementById("gender");

      req.birth = document.getElementById("birth").value;
      req.email = document.getElementById("email").value;
      req.gender = target.options[target.selectedIndex].value;
      req.name = document.getElementById("name").value;
      req.password = document.getElementById("password").value;
      req.phone = document.getElementById("phone").value;
      console.log(req);
      
      try {
        let res = await this.api.updateProfile(req);
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
    this.$router.push("/profile");
  },
  beforeUnmount() {
    console.log("beforeUnmount");
  },
};
</script>

<script setup>
import { HomeIcon } from '@heroicons/vue/20/solid'

const pages = [
  { name: '회원정보수정', href: '/profile', current: false },
  // { name: '회원탈퇴', href: '/withdraw', current: true },
  { name: '비밀번호 수정', href: '#', current: false },
  { name: '회원탈퇴', href: '/delete', current: false },
]
</script>
<style>

</style>