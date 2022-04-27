import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./common/store";
import axios from "./common/axios";

// import Datepicker from '@vuepic/vue-datepicker'
// import '@vuepic/vue-datepicker/dist/main.css'
import Datepicker from 'vue3-datepicker'
// import "bootstrap/dist/css/bootstrap.min.css";
// import "bootstrap/dist/js/bootstrap.js";

createApp(App).use(axios).use(store).use(router).component('Datepicker', Datepicker).mount("#app");
