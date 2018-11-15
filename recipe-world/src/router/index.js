import Vue from 'vue'
import VueRouter from 'vue-router'

import VueMeta from 'vue-meta'
// adds a loading at the top during page loads
import NProgress from 'nprogress/nprogress'
import store from '@state/store'
import routes from './routes'
import { CHECK_AUTH } from '@state/actions'

Vue.use(VueRouter)
Vue.use(VueMeta, {
  // The component option name that vue-meta looks for meta info on
  keyName: 'page',
})

const router = new VueRouter({
  routes,
  // Use the HTML5 history API (i.e. normal-looking routes)
  // instead of routes with hashes (e.g. example.com/#/about).
  // This may require some server configuration in production:
  // https://router.vuejs.org/en/essentials/history-mode.html#example-server-configurations

  mode: 'history',

  // Simulate native-like scroll behavior when navigating to a new
  // route and using back/forward buttons.
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition
    } else {
      return { x: 0, y: 0 }
    }
  },
})

// Before each route evaluates...
router.beforeEach((routeTo, routeFrom, next) => {
  // if this isnt an initial page load
  if (routeFrom.name !== null) {
    // start the route progress bar
    NProgress.start()
  }

  // check if auth is required on this route
  const authRequired = routeTo.matched.some(route => route.meta.authRequired)

  // if auth isnt required for the route, just continue
  if (!authRequired) return next()
  // if auth isnt required and the user is logged in..
  if (store.getters[`auth/isAuthenticated`]) {
    // validate the local user token
    return store.dispatch(`auth/${CHECK_AUTH}`).then(validUser => {
      // Then continue if the token still represents a valid user,
      // otherwise redirect to login.
      validUser ? next() : redirectToLogin()
    })
  }

  // If auth is required and the user is NOT currently logged in,
  // redirect to login.
  redirectToLogin()

  function redirectToLogin() {
    // Pass the original route to the login component
    next({ name: 'login', query: { redirectFrom: routeTo.fullPath } })
  }
})

// Create a `beforeResolve` hook, which fires whenever
// `beforeRouteEnter` and `beforeRouteUpdate` would. This
// allows us to ensure data is fetched even when params change,
// but the resolved route does not. We put it in `meta` to
// indicate that it's a hook we created, rather than part of
// Vue Router (yet?).
router.beforeResolve(async (routeTo, routeFrom, next) => {
  try {
    for (const route of routeTo.matched) {
      await new Promise((resolve, reject) => {
        // If a `beforeResolve` hook is defined, call it with
        // the same arguments as the `beforeEnter` hook.
        if (route.meta && route.meta.beforeResolve) {
          route.meta.beforeResolve(routeTo, routeFrom, (...args) => {
            if (args.length) {
              next(...args)
              NProgress.done()
              reject(new Error('Redirected'))
            } else {
              resolve()
            }
          })
        } else {
          // Otherwise, continue resolving the route.
          resolve()
        }
      })
    }
  } catch (e) {
    // If a `beforeResolve` hook chose to redirect, just return.
    return
  }

  next()
})

// When each route is finished evaluating...
router.afterEach(() => {
  // Complete the animation of the route progress bar.
  NProgress.done()
})

export default router
