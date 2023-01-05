<template>
  <router-view />
</template>

<script>
import { mapGetters, mapMutations } from "vuex";

export default {
  name: "App",
  computed: {
    // appStore/accessToken
    ...mapGetters({
      "accessToken": "appStore/accessToken",
    }),
  },
  data() {
    return {};
  },
  methods: {
    ...mapMutations({
      "setAccessToken": "appStore/accessToken",
    }),
  },
  created() {
    console.log("App.vue..", this.accessToken);
    this.api.setAccessToken(this.accessToken);
  },
};
</script>


