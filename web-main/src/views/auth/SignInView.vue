<template>
  <!--
    This example requires updating your template:

    ```
    <html class="h-full bg-gray-50">
    <body class="h-full">
    ```
  -->
  <div class="flex min-h-full items-center justify-center py-12 px-4 sm:px-6 lg:px-8">
    <div class="w-full max-w-md space-y-8">
      <div>
        <img class="mx-auto h-12 w-auto" src="https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=600" alt="Your Company"/>
        <h2 class="mt-6 text-center text-3xl font-bold tracking-tight text-gray-900">Laon Shop</h2>
        <p class="mt-2 text-center text-sm text-gray-600"></p>
      </div>
      <form class="mt-8 space-y-6" action="#" method="POST">
        <input type="hidden" name="remember" value="true" />
        <div class="-space-y-px rounded-md shadow-sm">
          <div>
            <label for="email-address" class="sr-only">이메일</label>
            <input
              v-model="email"
              id="email-address"
              name="email"
              type="email"
              autocomplete="email"
              required=""
              class="relative block w-full appearance-none rounded-none rounded-t-md border border-gray-300 px-3 py-2 text-gray-900 placeholder-gray-500 focus:z-10 focus:border-indigo-500 focus:outline-none focus:ring-indigo-500 sm:text-sm"
              placeholder="이메일"
            />
          </div>
          <div>
            <label for="password" class="sr-only">비밀번호</label>
            <input
              v-model="password"
              id="password"
              name="password"
              type="password"
              autocomplete="current-password"
              required=""
              class="relative block w-full appearance-none rounded-none rounded-b-md border border-gray-300 px-3 py-2 text-gray-900 placeholder-gray-500 focus:z-10 focus:border-indigo-500 focus:outline-none focus:ring-indigo-500 sm:text-sm"
              placeholder="비밀번호"
              @keyup.enter="signin"
            />
          </div>
        </div>

        <div class="flex items-center justify-between">
          <div class="flex items-center">
            <input v-model="isSeller" id="remember-me" name="remember-me" type="checkbox" class="h-4 w-4 rounded border-gray-300 text-indigo-600 focus:ring-indigo-500"/>
            <label for="remember-me" class="ml-2 block text-sm text-gray-900">Seller</label>
          </div>
          <div>
            <router-link to="/auth/signup" class="text-sm text-gray-900">Sign up</router-link>
          </div>
        </div>

        <div>
          <button @click="signin" type="button" class="group relative flex w-full justify-center rounded-md border border-transparent bg-indigo-600 py-2 px-4 text-sm font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2">
            <span class="absolute inset-y-0 left-0 flex items-center pl-3">
              <LockClosedIcon class="h-5 w-5 text-indigo-500 group-hover:text-indigo-400" aria-hidden="true"/>
            </span>
            Sign in
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<!-- <div class="flex justify-center items-center">
    <h1 class="text-3xl font-bold">Sign In</h1>
    <input type="text" v-model="email" />
    <input type="password" v-model="password" />
    <button @click="click">Sign In</button>
  </div> -->

<script>
import {mapGetters, mapMutations} from "vuex";
import {LockClosedIcon} from "@heroicons/vue/20/solid";
import SignInRequest from "@/service/request/SignInRequest";
import ResultCode from "@/service/ResultCode";
import {UserType} from "@/service/vo/UserType";

export default {
  name: "SignInView",
  components: { LockClosedIcon },
  props: {},
  data() {
    return {
      email: "",
      password: "",
      isSeller: false,
    };
  },
  computed: {
    ...mapGetters({
      accessToken: "appStore/accessToken",
      refreshToken: "appStore/refreshToken",
    }),
  },
  methods: {
    ...mapMutations({
      setAccessToken: "appStore/accessToken",
      setRefreshToken: "appStore/refreshToken",
      setUserInfo: "appStore/userInfo",
    }),
    async signin() {
      console.log("signin");

      if (this.email == "" || this.password == "") {
        alert("이메일과 비밀번호를 입력해주세요.");
        return;
      }

      let req = new SignInRequest();
      req.email = this.email;
      req.password = this.password;
      if (this.isSeller) {
        req.userType = 2;
      } else {
        req.userType = 1;
      }

      try {
        let res = await this.api.signIn(req);
        if (res.code == ResultCode.Success) {
          this.setAccessToken(res.accessToken);
          this.setRefreshToken(res.refreshToken);
          const userInfo = res.userInfo;
          this.setUserInfo(userInfo);

          if (userInfo.userType === UserType.User) {
            // 일반 사용자
            this.$router.replace("/user");
          } else if (userInfo.userType === UserType.Seller) {
            // 판매자
            this.$router.replace("/seller");
          } else {
            console.log(userInfo);
          }
        } else {
          console.error(res.message);
          alert(res.message);
        }
      } catch (err) {
        console.log(err);
      }
    },
    beforeDestroy() {
      console.log("beforeDestroy");
    },
  },
  created() {
    console.log("created");
  },
  mounted() {
    console.log("mounted");
  },
  beforeUnmount() {
    console.log("beforeUnmount");
  },
};
</script>
<style></style>
