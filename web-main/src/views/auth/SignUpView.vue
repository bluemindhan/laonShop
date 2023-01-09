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
        <input type="hidden" name="remember" value="true"/>
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
                class="relative block w-full appearance-none rounded-none border border-gray-300 px-3 py-2 text-gray-900 placeholder-gray-500 focus:z-10 focus:border-indigo-500 focus:outline-none focus:ring-indigo-500 sm:text-sm"
                :class="{ 'rounded-b-md': !isSeller }"
                placeholder="비밀번호"
            />
          </div>
          <div>
            <label for="phone" class="sr-only">전화번호</label>
            <input
              v-model="phone"
                id="phone"
                name="phone"
                type="phone"
                autocomplete="phone"
                required=""
                class="relative block w-full appearance-none rounded-none border border-gray-300 px-3 py-2 text-gray-900 placeholder-gray-500 focus:z-10 focus:border-indigo-500 focus:outline-none focus:ring-indigo-500 sm:text-sm"
                :class="{ 'rounded-b-md': !isSeller }"
                placeholder="전화번호"
            />
          </div>
          <div>
            <label for="birth" class="sr-only">생년월일</label>
            <input
              v-model="birth"
                id="birth"
                name="birth"
                type="date"
                autocomplete="birth"
                required=""
                class="relative block w-full appearance-none rounded-none border border-gray-300 px-3 py-2 text-gray-900 placeholder-gray-500 focus:z-10 focus:border-indigo-500 focus:outline-none focus:ring-indigo-500 sm:text-sm"
                :class="{ 'rounded-b-md': !isSeller }"
                placeholder="생년월일"
            />
          </div>
          <!-- <div>
            <label for="gender" class="sr-only">성별</label>
            <input
              v-model="gender"
                id="gender"
                name="gender"
                type="gender"
                autocomplete="gender"
                required=""
                class="relative block w-full appearance-none rounded-none border border-gray-300 px-3 py-2 text-gray-900 placeholder-gray-500 focus:z-10 focus:border-indigo-500 focus:outline-none focus:ring-indigo-500 sm:text-sm"
                :class="{ 'rounded-b-md': !isSeller }"
                placeholder="성별"
            />
          </div> -->
          <!-- <div>
            <label for="gender" class="sr-only">성별</label>
            <select name="gender" id="gender" v-model="gender" class="relative block w-full appearance-none rounded-none border border-gray-300 px-3 py-2 text-gray-900 placeholder-gray-500 focus:z-10 focus:border-indigo-500 focus:outline-none focus:ring-indigo-500 sm:text-sm">
              <option selected :value="{name: ''}">성별</option>
              <option :value="{name: '남자'}">남자</option>
              <option :value="{name: '여자'}">여자</option>
            </select>
          </div> -->
          <div v-if="isSeller">
            <label for="sellerName" class="sr-only">판매자명</label>
            <input
                v-model="sellerName"
                id="sellerName"
                name="sellerName"
                type="sellerName"
                required=""
                class="relative block w-full appearance-none rounded-none rounded-b-md border border-gray-300 px-3 py-2 text-gray-900 placeholder-gray-500 focus:z-10 focus:border-indigo-500 focus:outline-none focus:ring-indigo-500 sm:text-sm"
                placeholder="판매자명"
            />
          </div>
        </div>

        <div class="flex items-center justify-between">
          <div class="flex items-center">
            <input v-model="isSeller" id="remember-me" name="remember-me" type="checkbox" class="h-4 w-4 rounded border-gray-300 text-indigo-600 focus:ring-indigo-500"/>
            <label for="remember-me" class="ml-2 block text-sm text-gray-900">Seller</label>
          </div>
          <div>
            <router-link to="/auth/signin" class="text-sm text-gray-900">Sign in</router-link>
          </div>
        </div>

        <div>
          <button @click="signup" type="button" class="group relative flex w-full justify-center rounded-md border border-transparent bg-indigo-600 py-2 px-4 text-sm font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2">
            <span class="absolute inset-y-0 left-0 flex items-center pl-3">
              <LockClosedIcon class="h-5 w-5 text-indigo-500 group-hover:text-indigo-400" aria-hidden="true"/>
            </span>
            Sign up
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import {mapGetters, mapMutations} from "vuex";
import {LockClosedIcon} from "@heroicons/vue/20/solid";
import SignUpRequest from "@/service/request/SignUpRequest";
import ResultCode from "@/service/ResultCode";
import {UserType} from "@/service/vo/UserType";

export default {
  name: "SignUpView",
  components: {
    LockClosedIcon,
  },
  props: {},
  data() {
    return {
      email: "",
      password: "",
      phone: "",
      birth: "",
      // gender: "",
      sellerName: "",
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
    async signup() {

      if (this.isSeller) {
        if (this.sellerName == "") {
          alert("판매자명을 입력해주세요.");
          return;
        }
      }

      if (this.email == "") {
        alert("이메일을 입력해주세요.");
        return;
      }

      if (this.password == "") {
        alert("비밀번호를 입력해주세요.");
        return;
      }

      if (this.phone == "") {
        alert("전화번호를 입력해주세요.");
        return;
      }

      if (this.birth == "") {
        alert("생년월일을 입력해주세요.");
        return;
      }

      // if (this.gender == "") {
      //   alert("성별을 입력해주세요.");
      //   return;
      // }

      const req = new SignUpRequest();
      req.birth = this.birth;
      req.email = this.email;
      // req.gender = this.gender;
      req.password = this.password;
      req.phone = this.phone;
      
      if (this.isSeller) {
        req.userType = 2;
        req.name = this.sellerName;
      } else {
        req.userType = 1;
      }
      try {
        let res = await this.api.signUp(req);
        if (res.code == ResultCode.Success) {
          this.setAccessToken(res.accessToken);
          this.setRefreshToken(res.refreshToken);
          const userInfo = res.userInfo;
          this.setUserInfo(userInfo);

          if (userInfo.userType === UserType.User) {
            // 일반 사용자
            this.$router.replace({name: "UserMainView"});
          } else if (userInfo.userType === UserType.Seller) {
            // 판매자
            this.$router.replace({name: "SellerMainView"});
          } else {
            // error
          }
        } else {
          alert(res.message);
          this.$router.replace({name: "SignInView"});
        }
      } catch (e) {
        console.error(e);
      }
    },
  },
  created() {
  },
  mounted() {
  },
  beforeUnmount() {
  },
};
</script>
<style></style>
