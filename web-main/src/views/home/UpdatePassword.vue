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
            <h3 class="text-lg font-medium leading-6 text-gray-900">비밀번호 수정</h3>
            <p class="mt-1 text-sm text-gray-500">여기는 비밀번호 수정 페이지 입니다.</p>
        </div>
            <div class="sm:col-span-4 my-5">
            <label for="password" class="block text-sm font-medium text-gray-700">기존 비밀번호 확인</label>
            <div class="mt-1">
                <input 
                id="password" 
                name="password" 
                type="password"
                autocomplete="password" 
                v-model="password"
                class="block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" 
                required/>
            </div>
            </div>

            <div class="sm:col-span-4 my-5">
            <label for="newPassword" class="block text-sm font-medium text-gray-700">새 비밀번호</label>
            <div class="mt-1">
                <input 
                id="newPassword" 
                name="newPassword" 
                type="password"
                autocomplete="newPassword"
                v-model="newPassword"
                class="block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" 
                :class="{'input-danger': passwordHasError }"
                maxlength="16"
                required/>
            </div>
            <p 
                v-show="valid.newPassword"
                class="input-error">
                영문자, 숫자, 특수문자를 조합하여 최소 8자리를 입력해주세요.
            </p>
            </div>
            
            <div class="sm:col-span-4 my-5">
            <label for="confirmPassword" class="block text-sm font-medium text-gray-700">새 비밀번호 확인</label>
            <div class="mt-1">
                <input 
                id="confirmPassword" 
                name="confirmPassword" 
                type="password"
                autocomplete="confirmPassword" 
                v-model="confirmPassword"
                @blur="passwordCheckValid"
                class="block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" 
                required/>
            </div>
              <p 
                v-if="!passwordCheckFlag"
                class="input-error">
                비밀번호가 동일하지 않습니다.
              </p>
            </div>
        </div>
        </div>

        <div class="pt-5 p-20">
        <div class="flex justify-center">
            <button type="button" 
            class="rounded-md border border-gray-300 bg-white py-2 px-4 text-sm font-medium text-gray-700 shadow-sm hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
            @click="$router.back()">취소</button>
            <button @click="updateUser" type="submit" class="ml-3 inline-flex justify-center rounded-md border border-transparent bg-indigo-600 py-2 px-4 text-sm font-medium text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
            >수정</button>
        </div>
    </div>
    </form>
</template>
 
<script>
import {mapGetters, mapMutations} from "vuex";
import UpdatePasswordRequest from "@/service/request/UpdatePasswordRequest.js";
import ResultCode from "@/service/ResultCode";

 
 export default {
   name: "UpdatePassword",
   components: {},
   props: {},
   data() {
     return {
        confirmPassword: '',
        equal: true,
        newPassword: '',
        password: '',
        passwordHasError: false,
        passwordCheckFlag: true,
        valid: {
            newPassword: false,
         },
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
     },
    'newPassword': function() {
      this.checkPassword();
    },
   },
    methods: {
     ...mapMutations({
       setAccessToken: "appStore/accessToken",
       setRefreshToken: "appStore/refreshToken",
       setUserInfo: "appStore/userInfo",
     }),
     async updateUser() {
        if (this.password == "") {
        alert("기존 비밀번호 입력해주세요.");
        return;
         }
      if (this.newPassword == "") {
        alert("새 비밀번호 입력해주세요.");
        return;
        }  
      if (this.valid.newPassword == true) {
        alert('영문자, 숫자, 특수문자를 조합하여 최소 8자리를 입력해주세요.');
        return;
      }
      if (this.confirmPassword == "") {
        alert("새 비밀번호 확인 해주세요.");
        return;
        }
      if (this.passwordCheckFlag == false) {
        alert('비밀번호가 동일하지 않습니다.');
        return;
      }

        const req = new UpdatePasswordRequest();
        req.confirmPassword = this.confirmPassword;
        req.equal = this.equal;
        req.newPassword = this.newPassword;
        req.password = this.password;

        try {
        let res = await this.api.updateUser(req);
        if (res.code == ResultCode.Success) {
            console.log(res);
            alert("비밀번호가 변경되었습니다.");
            this.$router.replace({ name: "UserMainView" });
        } else {
          alert(res.message);
        }
      } catch (e) {
        console.error(e);
      }
     },
     checkPassword() {
      // 비밀번호 형식 검사(영문, 숫자, 특수문자)
      const validatePassword = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/

       if (!validatePassword.test(this.newPassword) || !this.newPassword) {
        this.valid.newPassword = true
        this.passwordHasError = true
        return
      } this.valid.newPassword = false
        this.passwordHasError = false
    },
    passwordCheckValid () {
      if (this.newPassword === this.confirmPassword) {
        this.passwordCheckFlag = true
        return
      } else {
        this.passwordCheckFlag = false
      }
    },
    
    },
    beforeDestroy() {
       console.log("beforeDestroy");
    },
   created() {
     console.log("UpdateView.vue..", this.accessToken);
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
 
 <script setup>
 import { HomeIcon } from '@heroicons/vue/20/solid'
 
 const pages = [
   { name: '회원정보수정', href: '/profile', current: false },
   { name: '비밀번호 수정', href: '/update', current: false },
   { name: '회원탈퇴', href: '/delete', current: false },
 ]
 </script>
 <style>
 .input-error {
  line-height: 16px;
  font-size: 11px;
  color: red;
}
.title-danger {
  color: red;
}
.input-danger {
  border-bottom: 1px solid red !important;
}
 </style>