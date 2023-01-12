<template>
  <div class="mt-1" style="width: 1300px; ">
    <div class="bg-white">
      <div class="mx-auto max-w-7xl py-16 px-4 sm:px-6 lg:px-8 lg:pb-24">
        <div class="max-w-xl">
          <h1 class="text-2xl font-bold tracking-tight text-gray-900 sm:text-3xl">Order history</h1>
          <p class="mt-2 text-sm text-gray-500">Check the status of recent orders, manage returns, and download invoices.</p>
        </div>

        <div class="mt-16">
          <h2 class="sr-only">Recent orders</h2>

          <div class="space-y-20">
            <div v-for="order in orders" :key="order.number">
              <h3 class="sr-only">
                Order placed on <time :datetime="order.datetime">{{ order.date }}</time>
              </h3>

              <table class="mt-4 w-full text-gray-500 sm:mt-6">
                <caption class="sr-only">
                  Products
                </caption>
                <thead class="sr-only text-left text-sm text-gray-500 sm:not-sr-only">
                  <tr>
                    <th scope="col" class="py-3 pr-8 font-normal sm:w-2/5 lg:w-1/3">상품명</th>
                    <th scope="col" class="hidden w-1/5 py-3 pr-8 font-normal sm:table-cell">가격</th>
                    <th scope="col" class="hidden py-3 pr-8 font-normal sm:table-cell">주문상태</th>
                    <th scope="col" class="w-0 py-3 text-right font-normal">상품정보</th>
                  </tr>
                </thead>
                <tbody class="divide-y divide-gray-200 border-b border-gray-200 text-sm sm:border-t">
                  <tr v-for="product in order.products" :key="product.id">
                    <td class="py-6 pr-8">
                      <div class="flex items-center">
                        <img :src="product.imageSrc" :alt="product.imageAlt" class="mr-6 h-16 w-16 rounded object-cover object-center" />
                        <div>
                          <div class="font-medium text-gray-900">{{ product.name }}</div>
                          <div class="mt-1 sm:hidden">{{ product.price }}</div>
                        </div>
                      </div>
                    </td>
                    <td class="hidden py-6 pr-8 sm:table-cell">{{ product.price }}</td>
                    <td class="hidden py-6 pr-8 sm:table-cell">{{ product.status }}</td>
                    <td class="whitespace-nowrap py-6 text-right font-medium">
                      <a :href="product.href" class="text-indigo-600"
                        >View<span class="hidden lg:inline">Product</span><span class="sr-only">, {{ product.name }}</span></a
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
  </div>
</template>

<script setup>
const orders = [
  {
    number: 'WU88191111',
    date: 'January 22, 2021',
    datetime: '2021-01-22',
    invoiceHref: '#',
    total: '$238.00',
    products: [
      {
        id: 1,
        name: 'Machined Pen and Pencil Set',
        href: '#',
        price: '70,000 원',
        status: 'Delivered Jan 25, 2021',
        imageSrc: 'https://tailwindui.com/img/ecommerce-images/order-history-page-02-product-01.jpg',
        imageAlt: 'Detail of mechanical pencil tip with machined black steel shaft and chrome lead tip.',
      },
      // More products...
    ],
  },
  // More orders...
]
</script>
<style>
</style>

