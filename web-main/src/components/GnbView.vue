<template>
  <Disclosure as="nav" class="bg-white shadow" v-slot="{ open }">
    <div class="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
      <div class="flex h-16 justify-between">
        <div class="flex">
          <div class="-ml-2 mr-2 flex items-center md:hidden">
            <!-- Mobile menu button -->
            <DisclosureButton class="inline-flex items-center justify-center rounded-md p-2 text-gray-400 hover:bg-gray-100 hover:text-gray-500 focus:outline-none focus:ring-2 focus:ring-inset focus:ring-indigo-500">
              <span class="sr-only">Open main menu</span>
              <Bars3Icon v-if="!open" class="block h-6 w-6" aria-hidden="true" />
              <XMarkIcon v-else class="block h-6 w-6" aria-hidden="true" />
            </DisclosureButton>
          </div>
          <div class="flex flex-shrink-0 items-center">
            <img class="block h-8 w-auto lg:hidden" src="https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=600" alt="Your Company" />
            <img @click="home" class="hidden h-8 w-auto lg:block" src="https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=600" alt="Your Company" />
          </div>
          <div class="hidden md:ml-6 md:flex md:space-x-8">
            <!-- Current: "border-indigo-500 text-gray-900", Default: "border-transparent text-gray-500 hover:border-gray-300 hover:text-gray-700" -->
            <!-- <a href="#" class="inline-flex items-center border-b-2 border-indigo-500 px-1 pt-1 text-sm font-medium text-gray-900">Dashboard</a>
            <a href="#" class="inline-flex items-center border-b-2 border-transparent px-1 pt-1 text-sm font-medium text-gray-500 hover:border-gray-300 hover:text-gray-700">Team</a>
            <a href="#" class="inline-flex items-center border-b-2 border-transparent px-1 pt-1 text-sm font-medium text-gray-500 hover:border-gray-300 hover:text-gray-700">Projects</a>
            <a href="#" class="inline-flex items-center border-b-2 border-transparent px-1 pt-1 text-sm font-medium text-gray-500 hover:border-gray-300 hover:text-gray-700">Calendar</a> -->
          </div>
        </div>
        <div class="flex items-center">
          <div class="flex-shrink-0">
            <button
              v-if="isLogin"
              v-show="isShow"
              type="button"
              @click="cart"
              class="relative inline-flex items-center rounded-md border border-transparent bg-indigo-600 px-4 py-2 text-sm font-medium text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
            >
              <span>cart</span>
            </button>
            <button
              v-if="isLogin"
              @click="logout"
              type="button"
              class="relative inline-flex items-center rounded-md border border-transparent bg-indigo-600 ml-4 px-4 py-2 text-sm font-medium text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
            >
              <span>Log out</span>
            </button>
            <button
              v-if="isLogin == false"
              @click="signin"
              type="button"
              class="relative inline-flex items-center rounded-md border border-transparent bg-indigo-600 px-4 py-2 text-sm font-medium text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
            >
              <span>Sign in</span>
            </button>
            <button
              v-if="isLogin == false"
              @click="signup"
              type="button"
              class="relative ml-2 inline-flex items-center rounded-md border border-transparent bg-indigo-600 px-4 py-2 text-sm font-medium text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
            >
              <span>Sign up</span>
            </button>
          </div>
          <div class="hidden md:ml-4 md:flex md:flex-shrink-0 md:items-center"
                v-if="isLogin">
            <button type="button" class="rounded-full bg-white p-1 text-gray-400 hover:text-gray-500 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2">
              <span class="sr-only">View notifications</span>
              <BellIcon class="h-6 w-6" aria-hidden="true" />
            </button>

            <!-- Profile dropdown -->
            <Menu as="div" 
            class="relative ml-3"
            v-if="isLogin">
              <div>
                <MenuButton class="flex rounded-full bg-white text-sm focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2">
                  <span class="sr-only">Open user menu</span>
                  <img class="h-8 w-8 rounded-full" src="https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80" alt="" />
                </MenuButton>
              </div>
              <transition
                enter-active-class="transition ease-out duration-200"
                enter-from-class="transform opacity-0 scale-95"
                enter-to-class="transform opacity-100 scale-100"
                leave-active-class="transition ease-in duration-75"
                leave-from-class="transform opacity-100 scale-100"
                leave-to-class="transform opacity-0 scale-95"
              >
                <MenuItems class="absolute right-0 z-10 mt-2 w-48 origin-top-right rounded-md bg-white py-1 shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none">
                  <MenuItem v-slot="{ active }">
                    <a @click="profile" :class="[active ? 'bg-gray-100' : '', 'block px-4 py-2 text-sm text-gray-700']">Your Profile</a>
                  </MenuItem>
                  <MenuItem v-slot="{ active }">
                    <a @click="profile" :class="[active ? 'bg-gray-100' : '', 'block px-4 py-2 text-sm text-gray-700']">Settings</a>
                  </MenuItem>
                  <MenuItem v-slot="{ active }">
                    <a @click="logout" :class="[active ? 'bg-gray-100' : '', 'block px-4 py-2 text-sm text-gray-700']">Sign out</a>
                  </MenuItem>
                </MenuItems>
              </transition>
            </Menu>
          </div>
        </div>
      </div>
    </div>
  </Disclosure>
</template>

<script setup>
import { MagnifyingGlassIcon } from '@heroicons/vue/20/solid'
</script>


<script>
import { mapGetters, mapMutations } from "vuex";
import { Disclosure, DisclosureButton, DisclosurePanel, Menu, MenuButton, MenuItem, MenuItems } from "@headlessui/vue";
import { Bars3Icon, BellIcon, XMarkIcon } from "@heroicons/vue/24/outline";
import { PlusIcon } from "@heroicons/vue/20/solid";
import GetProfileRequest from "@/service/request/GetProfileRequest";
import ResultCode from "@/service/ResultCode";

export default {
  name: "GnbView",
  components: {
    Disclosure,
    DisclosureButton,
    DisclosurePanel,
    Menu,
    MenuButton,
    MenuItem,
    MenuItems,
    Bars3Icon,
    BellIcon,
    XMarkIcon,
    PlusIcon,
  },
  props: {},
  data() {
    return {
      navigation: [
        // { name: "Dashboard", href: "#", current: true },
        // { name: "Team", href: "#", current: false },
        // { name: "Projects", href: "#", current: false },
        // { name: "Calendar", href: "#", current: false },
      ],
      userInfo: {
      },
      isShow: false,
      url: "",
    };
  },
  watch: {
    accessToken() {
      console.log("accessToken", this.accessToken);
    },
    refreshToken() {
      console.log("refreshToken", this.refreshToken);
    }
  },
  computed: {
    ...mapGetters({
      accessToken: "appStore/accessToken",
      refreshToken: "appStore/refreshToken",
    }),
    isLogin() {
      return this.accessToken ? true : false;
    },
  },
  methods: {
    ...mapMutations({
      setAccessToken: "appStore/accessToken",
      setRefreshToken: "appStore/refreshToken",
      setUserInfo: "appStore/userInfo",
    }),
    home() {
      console.log("home");
      this.$router.replace(this.url);
    },
    signin() {
      console.log("signin");
      // replace /signin
      this.$router.replace({ name: "SignInView" });
    },
    signup() {
      console.log("signup");
      this.$router.replace({ name: "SignUpView" });
    },
    logout() {
      console.log("logout");
      this.setAccessToken(null);
      this.setRefreshToken(null);
      this.$router.replace({ name: "HomeView" });
    },
    cart() {
      console.log("cart");
      this.$router.replace({ name: "UserCartView" });
    },
    async getProfile() {
      console.log("getProfile");

      let req = new GetProfileRequest();
      req.accessToken = this.accessToken;

      try {
        let res = await this.api.getProfile(req);
        if (res.code == ResultCode.Success) {
          const userInfo = res.userInfo;
          this.setUserInfo(userInfo);
          
          if (userInfo.userType == 1) {
            // 일반 사용자
            this.isShow = true;
            this.url = "/user";
          } else if (userInfo.userType == 2) {
            // 판매자
            this.isShow = false;
            this.url = "/seller";
          } else {
            console.log(userInfo);
          }
        }
      } catch (e) {
        console.error(e);
      } 
    },
    profile() {
      this.$router.replace({ name : "ProfileView" });
    }
  },
  created() {
    this.getProfile();
  },
  mounted() {},
  beforeUnmount() {},
};
</script>
<style></style>
