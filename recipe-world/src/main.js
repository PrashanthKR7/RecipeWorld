import Vue from 'vue'
import App from './App.vue'
import router from '@router'
import store from '@state/store'
import '@components/_globals'
import Buefy from 'buefy'
import VeeValidate, { Validator } from 'vee-validate'
import installRules from '@validators'

Vue.use(VeeValidate)
installRules(Validator)
Vue.use(Buefy)
Vue.config.productionTip = process.env.NODE_ENV === 'production'
new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
