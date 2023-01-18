<template>
  <div>
    <GnbView/>
    <router-view></router-view>
    <Footer/>
  </div>
</template>

<script>
import GnbView from '@/components/GnbView.vue'
import Footer from '@/components/Footer.vue'
export default {
  name: 'HomeLayout',
  components: {
    GnbView,
    Footer,
  },
  props: {
  },
  data() {
    return {
    }
  },
  computed: {
  },
  methods: {
  },
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

