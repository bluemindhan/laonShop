<template>
  <div>
    <GnbView/>
    <router-view></router-view>
  </div>
</template>

<script>
import GnbView from '@/components/GnbView.vue'
export default {
  name: 'HomeLayout',
  components: {
    GnbView,
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

