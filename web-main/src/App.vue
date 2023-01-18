<template>
  <router-view/>
</template>

<script>
import {mapGetters, mapMutations} from "vuex";
import GetProfileRequest from "@/service/request/GetProfileRequest";
import ResultCode from "@/service/ResultCode";
import {UserType} from "@/service/vo/UserType";


export default {
  name: "App",
  computed: {
    // appStore/accessToken
    ...mapGetters({
      accessToken: "appStore/accessToken",
      refreshToken: "appStore/refreshToken",
    }),
  },
  data() {
    return {};
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
    // async loadProfile() {

    //   let req = new GetProfileRequest();

    //   try {
    //     let res = await this.api.getProfile(req);
    //     if (res.code === ResultCode.Success) {
    //       const userInfo = res.userInfo;
    //       this.setUserInfo(userInfo);

    //       if (userInfo.userType === UserType.User) {
    //         this.$router.replace({name: "UserMainView"});
    //       } else if (userInfo.userType === UserType.Seller) {
    //         this.$router.replace({name: "SellerMainView"});
    //       } else {
    //         this.$router.replace({name: 'SignUpView'});
    //       }
    //     } else {
    //       this.$router.replace({name: "SignInView"});
    //     }
    //   } catch (err) {
    //     this.$router.replace({name: "SignInView"});
    //   }
    // }
  },
  created() {
    console.log("App.vue..", this.accessToken);
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
      // this.loadProfile();
    }
  },
};
</script>


