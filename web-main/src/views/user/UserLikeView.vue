<template>
  <div class="px-4 sm:px-6 lg:px-8 p-10 mx-40">
    <div class="sm:flex sm:items-center">
      <div class="sm:flex-auto">
        <h1 class="text-xl font-semibold text-gray-900">내가 찜한 목록</h1>
        <p class="mt-2 text-sm text-gray-700">내가 찜한 상품을 확인하세요</p>
      </div>
    </div>
    <div class="mt-8 flex flex-col">
      <div class="-my-2 -mx-4 sm:-mx-6 lg:-mx-8">
        <div class="inline-block min-w-full py-2 align-middle">
          <div class="shadow-sm ring-1 ring-black ring-opacity-5">
            <table class="min-w-full border-separate" style="border-spacing: 0">
              <thead class="bg-gray-50">
                <tr>
                  <th scope="col" class="sticky top-0 z-10 border-b border-gray-300 bg-gray-50 bg-opacity-75 py-3.5 pl-4 pr-3 text-left text-sm font-semibold text-gray-900 backdrop-blur backdrop-filter sm:pl-6 lg:pl-8">NO</th>
                  <th scope="col" class="sticky top-0 z-10 border-b border-gray-300 bg-gray-50 bg-opacity-75 py-3.5 pl-4 pr-3 text-left text-sm font-semibold text-gray-900 backdrop-blur backdrop-filter sm:pl-6 lg:pl-8">상품이름</th>
                  <th scope="col" class="sticky top-0 z-10 hidden border-b border-gray-300 bg-gray-50 bg-opacity-75 px-3 py-3.5 text-left text-sm font-semibold text-gray-900 backdrop-blur backdrop-filter sm:table-cell">찜한 날짜</th>
                  <th scope="col" class="sticky top-0 z-10 border-b border-gray-300 bg-gray-50 bg-opacity-75 py-3.5 pr-4 pl-3 backdrop-blur backdrop-filter sm:pr-6 lg:pr-8">
                    <span class="sr-only">삭제</span>
                  </th>
                </tr>
              </thead>
              <tbody class="bg-white">
                <tr v-for="(wish, wishIdx) in wishList" :key="wish.createDate" >
                  <td :class="[wishIdx !== wish.length - 1 ? 'border-b border-gray-200' : '', 'whitespace-nowrap py-4 pl-4 pr-3 text-sm font-medium text-gray-900 sm:pl-6 lg:pl-8']">{{ wishIdx + 1 }}</td>
                  <td :class="[wishIdx !== wish.length - 1 ? 'border-b border-gray-200' : '', 'whitespace-nowrap py-4 pl-4 pr-3 text-sm font-medium text-gray-900 sm:pl-6 lg:pl-8']" @click="$router.push(`/user/items/${ wish.productNum }`)">{{ wish.productName }}</td>
                  <td :class="[wishIdx !== wish.length - 1 ? 'border-b border-gray-200' : '', 'whitespace-nowrap px-3 py-4 text-sm text-gray-500 hidden sm:table-cell']">{{ wish.createDate }}</td>
                  <td :class="[wishIdx !== wish.length - 1 ? 'border-b border-gray-200' : '', 'relative whitespace-nowrap py-4 pr-4 pl-3 text-right text-sm font-medium sm:pr-6 lg:pr-8']">
                    <button type="button" @click="unliked(wish.productNum)" class="text-indigo-600 hover:text-indigo-900"
                      >삭제<span class="sr-only">, {{ wish.productName }}</span></button
                    >
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import GetLikeListRequest from '@/service/request/GetLikeListRequest.js';
import DeleteLikeRequest from '@/service/request/DeleteLikeRequest.js';
import {mapGetters, mapMutations} from "vuex";
import ResultCode from "@/service/ResultCode";
import HeroSection from '@/components/HeroSection.vue'

export default {
  name: 'UserLikeView',
  components: {
    HeroSection,
  },
  props: {
  },
  data() {
    return {
      wishList: [],
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
    },
  },
  methods: {
    ...mapMutations({
      setAccessToken: "appStore/accessToken",
      setRefreshToken: "appStore/refreshToken",
    }),

    async getLikeList() {
      let req = new GetLikeListRequest();
      req.accessToken = this.accessToken;
      console.log(req);
      try {
        let res = await this.api.getLikeList(req);
        if (res.code === ResultCode.Success) {
          this.wishList = res.wishList;
          console.log(res);
        }
      } catch (e) {
        console.error(e);
      }
    },
    async unliked(val) {
      let req = new DeleteLikeRequest();
      req.prdNum = val;
      console.log(req);
      try {
        let res = await this.api.deleteLike(req);

        if (res.code === ResultCode.Success) {
          console.log(res);
          this.$router.go();
        } else {
          alert(res.message);
        }
      } catch (e) {
        console.error(e);
      }
    },
  },
  created() {
    console.log("ItemDetail.vue..", this.accessToken);
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
      this.getLikeList();
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