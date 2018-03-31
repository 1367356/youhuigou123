import Vue from 'vue'
import Router from 'vue-router'
import Discount from '@/components/Discount'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Discount',
      component: Discount
    }
  ]
})
