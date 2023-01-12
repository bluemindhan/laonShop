<!--<template>-->
<!--  <div class="bg-white">-->
<!--    <div class="mx-auto max-w-7xl py-16 px-4 sm:py-24 sm:px-6 lg:px-8">-->
<!--      <div class="text-center">-->
<!--        <p class="mt-1 text-4xl font-bold tracking-tight text-gray-900 sm:text-5xl lg:text-6xl">Laon Shop</p>-->
<!--        &lt;!&ndash; [ko-KR] Laon Shop 에 오신것을 환영합니다. Springboot + VueJs로 만드는 쇼핑몰입니다. &ndash;&gt;-->
<!--        &lt;!&ndash; [en-US] Welcome to Laon Shop. This is a shopping mall made with Springboot + VueJs. &ndash;&gt;-->
<!--        -->
<!--        <p class="mx-auto mt-12 max-w-xl text-xl text-gray-500">Welcome to Laon Shop. This is a shopping mall made with Springboot + VueJs.</p>-->
<!--      </div>-->
<!--    </div>-->
<!--  </div>-->
<!--</template>-->
<template>
  <div class="relative bg-gray-50 px-6 pt-16 pb-20 lg:px-8 lg:pt-24 lg:pb-28">
    <div class="absolute inset-0">
      <div class="h-1/3 bg-white sm:h-2/3" />
    </div>
    <div class="relative mx-auto max-w-7xl">
      <div class="text-center">
        <h2 class="text-3xl font-bold tracking-tight text-gray-900 sm:text-4xl">LaonShop에 오신 것을 환영합니다</h2>
        <p class="mx-auto mt-3 max-w-2xl text-xl text-gray-500 sm:mt-4">편리하게 쇼핑몰을 즐기시기 바랍니다.</p>
      </div>
      <div class="mx-auto mt-12 grid max-w-lg gap-5 lg:max-w-none lg:grid-cols-3">
        <div v-for="post in volist" :key="post.itemId" class="flex flex-col overflow-hidden rounded-lg shadow-lg">
          <div>{{post.name}}</div>

        </div>
      </div>
    </div>
<!--          <div class="flex-shrink-0">-->
<!--            <img class="h-48 w-full object-cover" :src="post.imageUrl" alt="" />-->
<!--          </div>-->
<!--          <div class="flex flex-1 flex-col justify-between bg-white p-6">-->
<!--            <div class="flex-1">-->
<!--              <p class="text-sm font-medium text-indigo-600">-->
<!--                <a class="hover:underline">{{ post.name }}</a>-->
<!--              </p>-->
<!--              <a class="mt-2 block">-->
<!--                <p class="text-xl font-semibold text-gray-900">{{ post.itemDetail }}</p>-->
<!--                <p class="mt-3 text-base text-gray-500">{{ post.price}}</p>-->
<!--              </a>-->
<!--            </div>-->
<!--            <div class="mt-6 flex items-center">-->
<!--              <div class="flex-shrink-0">-->
<!--                <a :href="post.author.href">-->
<!--                  <span class="sr-only">{{ post.author.name }}</span>-->
<!--                  <img class="h-10 w-10 rounded-full" :src="post.author.imageUrl" alt="" />-->
<!--                </a>-->
<!--              </div>-->
<!--              <div class="ml-3">-->
<!--                <p class="text-sm font-medium text-gray-900">-->
<!--                  <a :href="post.author.href" class="hover:underline">{{ post.author.name }}</a>-->
<!--                </p>-->
<!--                <div class="flex space-x-1 text-sm text-gray-500">-->
<!--                  <time :datetime="post.datetime">{{ post.date }}</time>-->
<!--                  <span aria-hidden="true">&middot;</span>-->
<!--                  <span>{{ post.readingTime }} read</span>-->
<!--                </div>-->
<!--              </div>-->
<!--            </div>-->
<!--          </div>-->
<!--        </div>-->
<!--      </div>-->
<!--    </div>-->
  </div>
</template>

<!--<script setup>-->
<!--const posts = [-->
<!--  {-->
<!--    title: 'Boost your conversion rate',-->
<!--    href: '#',-->
<!--    category: { name: 'Article', href: '#' },-->
<!--    description:-->
<!--        'Lorem ipsum dolor sit amet consectetur adipisicing elit. Architecto accusantium praesentium eius, ut atque fuga culpa, similique sequi cum eos quis dolorum.',-->
<!--    date: 'Mar 16, 2020',-->
<!--    datetime: '2020-03-16',-->
<!--    imageUrl:-->
<!--        'https://images.unsplash.com/photo-1496128858413-b36217c2ce36?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1679&q=80',-->
<!--    readingTime: '6 min',-->
<!--    author: {-->
<!--      name: 'Roel Aufderehar',-->
<!--      href: '#',-->
<!--      imageUrl:-->
<!--          'https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80',-->
<!--    },-->
<!--  },-->
<!--  {-->
<!--    title: 'How to use search engine optimization to drive sales',-->
<!--    href: '#',-->
<!--    category: { name: 'Video', href: '#' },-->
<!--    description:-->
<!--        'Lorem ipsum dolor sit amet consectetur adipisicing elit. Velit facilis asperiores porro quaerat doloribus, eveniet dolore. Adipisci tempora aut inventore optio animi., tempore temporibus quo laudantium.',-->
<!--    date: 'Mar 10, 2020',-->
<!--    datetime: '2020-03-10',-->
<!--    imageUrl:-->
<!--        'https://images.unsplash.com/photo-1547586696-ea22b4d4235d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1679&q=80',-->
<!--    readingTime: '4 min',-->
<!--    author: {-->
<!--      name: 'Brenna Goyette',-->
<!--      href: '#',-->
<!--      imageUrl:-->
<!--          'https://images.unsplash.com/photo-1550525811-e5869dd03032?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80',-->
<!--    },-->
<!--  },-->
<!--  {-->
<!--    title: 'Improve your customer experience',-->
<!--    href: '#',-->
<!--    category: { name: 'Case Study', href: '#' },-->
<!--    description:-->
<!--        'Lorem ipsum dolor sit amet consectetur adipisicing elit. Sint harum rerum voluptatem quo recusandae magni placeat saepe molestiae, sed excepturi cumque corporis perferendis hic.',-->
<!--    date: 'Feb 12, 2020',-->
<!--    datetime: '2020-02-12',-->
<!--    imageUrl:-->
<!--        'https://images.unsplash.com/photo-1492724441997-5dc865305da7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1679&q=80',-->
<!--    readingTime: '11 min',-->
<!--    author: {-->
<!--      name: 'Daniela Metz',-->
<!--      href: '#',-->
<!--      imageUrl:-->
<!--          'https://images.unsplash.com/photo-1487412720507-e7ab37603c6f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80',-->
<!--    },-->
<!--  },-->
<!--]-->
<!--</script>-->
<script>
import {mapGetters} from "vuex";
import GetItemsRequest from "@/service/request/GetItemsRequest";
import ResultCode from "@/service/ResultCode";

export default {
  name: "HomeView",
  components: {
  },
  props: {
  },
  data() {
    return {
      volist: [],
    }
  },
  computed: {

  },
  methods: {
    async getMainPage() {
      try {
        let res = await this.api.mainPage();
        if (res.code === ResultCode.Success) {
          this.volist = res.volist;
          console.log(res);
        }
      } catch (e) {
        console.error(e);
      }
    },
  },
  created() {
    this.getMainPage();
  },
  mounted() {

  },
  beforeUnmount() {

  },
};
</script>

<style>
</style>  
