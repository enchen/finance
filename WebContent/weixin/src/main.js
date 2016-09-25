import Vue from 'vue'
import VueRouter from 'vue-router'
import Res from 'vue-resource'
import App from './App'
import User from './components/User.vue'
import Spend from './components/Spend.vue'
import Success from './components/success.vue'
Vue.use(VueRouter)
Vue.use(Res)
var router = new VueRouter()

router.map({
  '/': {
    component:App
  },
  'user': {
    component: User
  }
  ,
  'spend': {
    component: Spend
  }
  ,
  'app': {
    component: App
  }
  ,
  'success': {
    component: Success
  }
  // '/bar': {
  //   component: Bar
  // }
})

// now we can start the app!
// router will create an instance of App and mount to
// the element matching the selector #app.
router.start(Vue.extend({}), '#app')

