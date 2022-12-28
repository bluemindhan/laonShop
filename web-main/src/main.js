import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import './index.css'
import axios from "./plugins/axios"
import apiservice from "./service/apiservice"
import store from "./store"

import mitt from 'mitt'

// import './mock';


const emitter = mitt();

const app = createApp(App);
app.config.globalProperties.axios = axios
app.config.globalProperties.emitter = emitter
app
    .use(router)
    .use(store)
    .use(apiservice)
    .mount('#app')
