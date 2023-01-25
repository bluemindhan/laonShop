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
              placeholder="laonShop@laonShop.co.kr"
              :class="{ 'input-danger': emailHasError }"
            />
            <p 
              v-show="valid.email"
              class="input-error">
               이메일 주소를 정확히 입력해주세요.
            </p>
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
                :class="{ 'rounded-b-md': !isSeller, 'input-danger': passwordHasError }"
                maxlength="16"
                placeholder="비밀번호"
            />
            <p 
              v-show="valid.password"
              class="input-error">
               영문자, 숫자, 특수문자를 조합하여 최소 8자리를 입력해주세요.
            </p>
          </div>
          <div>
            <label for="passwordCheck" class="sr-only">비밀번호 확인</label>
            <input
              v-model="passwordCheck"
              type="password"
              autocomplete="current-password"
              required=""
              class="relative block w-full appearance-none rounded-none border border-gray-300 px-3 py-2 text-gray-900 placeholder-gray-500 focus:z-10 focus:border-indigo-500 focus:outline-none focus:ring-indigo-500 sm:text-sm"
              :class="{ 'rounded-b-md': !isSeller}"
              maxlength="16"
              placeholder="비밀번호 확인"
              @blur="passwordCheckValid"
            />
            <p 
              v-if="!passwordCheckFlag"
              class="input-error">
               비밀번호가 동일하지 않습니다.
            </p>
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
              maxlength="13"
              @keyup="getPhoneMask(phone)"
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
          <div>
            <label for="gender" class="sr-only">성별</label>
            <select name="gender" id="gender" v-model="gender" class="relative block w-full appearance-none rounded-none border border-gray-300 px-3 py-2 text-gray-900 placeholder-gray-500 focus:z-10 focus:border-indigo-500 focus:outline-none focus:ring-indigo-500 sm:text-sm">
              <option 
              v-for="(item, index) in selectList"
              :key="index"
              :value="item.value">
              {{ item.name }} 
              </option>
            </select>
          </div>
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
      gender: "",
      selectList: [
        { name: "성별을 선택해주세요.", value: "" },
        { name: "남자", value: "남자" },
        { name: "여자", value: "여자" },
      ],
      sellerName: "",
      isSeller: false,
      valid: {
        email: false,
        password: false,
      },
      emailHasError: false,
      passwordHasError: false,
      passwordCheck: '',
      passwordCheckFlag: true,
    };
  },
  computed: {
    ...mapGetters({
      accessToken: "appStore/accessToken",
      refreshToken: "appStore/refreshToken",
    }),
  },
  watch: {
    'email': function() {
      this.checkEmail()
    },
    'password': function() {
      this.checkPassword()
    },
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

      if (this.valid.email == true) {
        alert('이메일 형식이 아닙니다.');
        return;
      }

      if (this.password == "") {
        alert("비밀번호를 입력해주세요.");
        return;
      }
      if (this.valid.password == true) {
        alert('영문자, 숫자, 특수문자를 조합하여 최소 8자리를 입력해주세요.');
        return;
      }

      if (!this.passwordCheckFlag) {
        alert('비밀번호가 동일하지 않습니다.');
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

      if (this.gender == "") {
        alert("성별을 선택해주세요.");
        return;
      }

      const req = new SignUpRequest();
      req.birth = this.birth;
      req.email = this.email;
      req.gender = this.gender;
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
            alert("회원가입을 축하합니다.");
            this.$router.replace({name: "UserMainView"});
          } else if (userInfo.userType === UserType.Seller) {
            // 판매자
            alert("회원가입을 축하합니다.");
            this.$router.replace({name: "SellerProductsView"});
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
    checkEmail() {
      // 이메일 형식 검사
    const validateEmail = /^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\.[A-Za-z0-9\\-]+/

     if (!validateEmail.test(this.email) || !this.email) {
        this.valid.email = true
        this.emailHasError = true
        return
      } this.valid.email = false
        this.emailHasError = false
    },
    checkPassword() {
      // 비밀번호 형식 검사(영문, 숫자, 특수문자)
      const validatePassword = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/

       if (!validatePassword.test(this.password) || !this.password) {
        this.valid.password = true
        this.passwordHasError = true
        return
      } this.valid.password = false
        this.passwordHasError = false
    },
    passwordCheckValid () {
      if (this.password === this.passwordCheck) {
        this.passwordCheckFlag = true
        return
      } else {
        this.passwordCheckFlag = false
      }
    },
    getPhoneMask(val) {
      let res = this.getMask(val)
      this.phone = res;
    },
    getMask( phoneNumber ) {
        if(!phoneNumber) return phoneNumber
        phoneNumber = phoneNumber.replace(/[^0-9]/g, '')
    
        let res = ''
        if(phoneNumber.length < 3) {
            res = phoneNumber
        }
        else {
          if(phoneNumber.substr(0, 2) =='02') {
      
              if(phoneNumber.length <= 5) {//02-123-5678
                  res = phoneNumber.substr(0, 2) + '-' + phoneNumber.substr(2, 3)
              }
              else if(phoneNumber.length > 5 && phoneNumber.length <= 9) {//02-123-5678
                  res = phoneNumber.substr(0, 2) + '-' + phoneNumber.substr(2, 3) + '-' + phoneNumber.substr(5)
              }
              else if(phoneNumber.length > 9) {//02-1234-5678
                  res = phoneNumber.substr(0, 2) + '-' + phoneNumber.substr(2, 4) + '-' + phoneNumber.substr(6)
              }
      
          } else {
              if(phoneNumber.length < 8) {
                  res = phoneNumber
              }
              else if(phoneNumber.length == 8)
              {
                  res = phoneNumber.substr(0, 4) + '-' + phoneNumber.substr(4)
              }
              else if(phoneNumber.length == 9)
              {
                  res = phoneNumber.substr(0, 3) + '-' + phoneNumber.substr(3, 3) + '-' + phoneNumber.substr(6)
              }
              else if(phoneNumber.length == 10)
              {
                  res = phoneNumber.substr(0, 3) + '-' + phoneNumber.substr(3, 3) + '-' + phoneNumber.substr(6)
              }
              else if(phoneNumber.length > 10) { //010-1234-5678
                  res = phoneNumber.substr(0, 3) + '-' + phoneNumber.substr(3, 4) + '-' + phoneNumber.substr(7)
              }
          }
        }
        return res
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
