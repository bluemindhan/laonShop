<template>
  <div>
    <HeroSection/>
  </div>
</template>

<script>
import HeroSection from '../../components/HeroSection.vue'
import {mapGetters, mapMutations} from "vuex";

export default {
  name: 'UserMainView',
  components: {
    HeroSection,
  },
  props: {
  },
  data() {
    return {
    }
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
  },
  created() {
    console.log("UserMainView.vue..", this.accessToken);
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
  },
  beforeUnmount() {
  }
}
</script>
<style>
</style>


<script setup>

</script>
<style>
</style>