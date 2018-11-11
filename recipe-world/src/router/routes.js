import store from '@state/store'
import { LOGOUT, FETCH_PROFILE, FETCH_RECIPE } from '@state/actions'
export default [
  {
    path: '/',
    name: 'home',
    component: () => lazyLoadView(import('@views/home')),
  },
  {
    path: '/login',
    name: 'login',
    component: () => lazyLoadView(import('@views/login')),
    meta: {
      beforeResolve(routeTo, routeFrom, next) {
        if (store.getters['auth/isAuthenticated']) {
          // redirect to home page instead
          next({ name: 'home' })
        } else {
          // continue to the login page
          next()
        }
      },
    },
  },
  {
    path: '/profile',
    name: 'profile',
    component: () => lazyLoadView(import('@views/profile')),
    meta: {
      authRequired: true,
    },
    props: () => ({ user: store.state.auth.currentUser }),
  },
  {
    path: '/profile/:username',
    name: 'username-profile',
    component: () => lazyLoadView(import('@views/profile')),
    // Set the user from the route params, once it's set in the
    // beforeResolve route guard.
    props: route => ({ user: route.params.user }),
    meta: {
      authRequired: true,
      beforeResolve(routeTo, routeFrom, next) {
        store
          .dispatch(`user/${FETCH_PROFILE}}`, {
            username: routeTo.params.username,
          })
          .then(user => {
            // Add the user to the route params, so that it can
            // be provided as a prop for the view component below.
            routeTo.params.user = user
            // continue to the route
            next()
          })
          .catch(() => {
            // If a user with the provided username could not be
            // found, redirect to the 404 page.
            next({ name: '404', params: { resource: 'User' } })
          })
      },
    },
  },
  {
    path: '/logout',
    name: 'logout',
    meta: {
      authRequired: true,
      beforeResolve(routeTo, routeFrom, next) {
        store.dispatch(`auth/${LOGOUT}`)
        const authRequiredOnPreviousRoute = routeFrom.matched.some(
          route => route.meta.authRequired
        )

        // Navigate back to previous page, or home as a fallback
        next(authRequiredOnPreviousRoute ? { name: 'home' } : { ...routeFrom })
      },
    },
  },
  {
    path: '/recipes/:recipeid',
    name: 'recipe',
    props: true,
    component: () => lazyLoadView(import('@views/recipe')),
    beforeResolve(routeTo, routeFrom, next) {
      store
        .dispatch(`recipe/${FETCH_RECIPE}`, {
          recipeId: routeTo.params.recipeid,
        })
        .then(user => {
          // Add the user to the route params, so that it can
          // be provided as a prop for the view component below.
          routeTo.params.user = user
          // continue to the route
          next()
        })
        .catch(() => {
          // If a user with the provided username could not be
          // found, redirect to the 404 page.
          next({ name: '404', params: { resource: 'Recipe' } })
        })
    },
  },
  {
    path: '/recipes',
    name: 'library',
    component: () => lazyLoadView(import('@views/library')),
  },
  {
    path: '/create-edit/:recipeid?',
    name: 'create-edit',
    meta: {
      authRequired: true,
    },
    // props: () => ({ user: store.state.auth.currentUser }),
    component: () => lazyLoadView(import('@views/create')),
  },

  {
    path: '/404',
    name: '404',
    component: require('@views/404').default,
    // Set the user from the route params, once it's set in the
    // beforeResolve route guard.
    props: true,
  },
  // Redirect any unmatched routes to the 404 page. This may
  // require some server configuration to work in production:
  // https://router.vuejs.org/en/essentials/history-mode.html#example-server-configurations
  {
    path: '*',
    redirect: '404',
  },
]

function lazyLoadView(AsyncView) {
  const AsyncHandler = () => ({
    component: AsyncView,
    // component to use while loading the required component
    loading: require('@views/loading').default,
    //  fallback component is case the timeout is exceeded when loading the component
    error: require('@views/timeout').default,
    // delay before showing the loading component
    delay: 400,
    // time before giving up trying to load the component
    timeout: 10000,
  })
  return Promise.resolve({
    functional: true,
    render(h, { data, children }) {
      // Transparently pass any props or children
      // to the view component.
      return h(AsyncHandler, data, children)
    },
  })
}
