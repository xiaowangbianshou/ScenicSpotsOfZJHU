import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import request from './utils/request';
import './assets/global.css'
import store from './store/store'

Vue.config.productionTip = false
Vue.use(ElementUI, { size: "mini" });

Vue.prototype.$http = request

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')