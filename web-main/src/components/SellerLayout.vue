<template>
  <div class="h-full flex flex-col">
    <GnbView/>
    <div class="flex h-full">
      <div class="h-full w-64 flex min-h-0 flex-col bg-gray-800 shadow">
        <div class="flex flex-1 flex-col overflow-y-auto pt-5 pb-4">
          <nav class="mt-5 flex-1 space-y-1 bg-gray-800 px-2" aria-label="Sidebar">
            <router-link v-for="item in navigation" :key="item.name" :to="item.href"
                         :class="[item.current ? 'bg-gray-900 text-white' : 'text-gray-300 hover:bg-gray-700 hover:text-white', 'group flex items-center px-2 py-2 text-sm font-medium rounded-md']">
              <component :is="item.icon"
                         :class="[item.current ? 'text-gray-300' : 'text-gray-400 group-hover:text-gray-300', 'mr-3 flex-shrink-0 h-6 w-6']"
                         aria-hidden="true"/>
              <span class="flex-1">{{ item.name }}</span>
              <span v-if="item.count"
                    :class="[item.current ? 'bg-gray-800' : 'bg-gray-900 group-hover:bg-gray-800', 'ml-3 inline-block py-0.5 px-3 text-xs font-medium rounded-full']">{{
                  item.count
                }}</span>
            </router-link>
          </nav>
        </div>
      </div>
      <div class="w-full px-4 sm:px-6 lg:px-8">
        <!-- We've used 3xl here, but feel free to try other max-widths based on your needs -->
        <div class="">
          <router-view></router-view>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import GnbView from '@/components/GnbView.vue'
import {CreditCardIcon, ChartBarIcon, FolderIcon, HomeIcon, InboxIcon, ShoppingBagIcon} from "@heroicons/vue/24/solid";

export default {
  name: 'SellerLayout',
  components: {
    GnbView,
    CreditCardIcon,
    ChartBarIcon,
    FolderIcon,
    HomeIcon,
    InboxIcon,
    ShoppingBagIcon,
  },
  props: {},
  data() {
    return {
      navigation: [
        {name: '상품 목록', icon: ShoppingBagIcon, href: '/seller/products', count: 3, current: false},
        {name: '상품 등록', icon: ShoppingBagIcon, href: '/seller/products/new', count: 3, current: false},
      ]
    }
  },
  watch: {
    $route(to, from) {
      this.navigation.forEach((item) => {
        item.current = item.href === to.path
      })
    }
  },
  computed: {},
  methods: {},
  created() {
  },
  mounted() {
  },
  beforeUnmount() {
  }
}
</script>
<style>
</style>