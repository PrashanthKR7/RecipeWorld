import axios from 'axios'

export const state = {
  cached: [],
}

export const getters = {}

export const mutations = {
  CACHE_USER(state, newUser) {
    state.cached.push(newUser)
  },
}

export const actions = {
  fetchUser({ commit, state, rootState }, { username }) {
    // check if we already have the user as current user
    const { currentUser } = rootState.auth
    if (currentUser && currentUser.username === username) {
      return Promise.resolve(currentUser)
    }

    // Check if we've already fetched and cached the user
    const matchedUser = state.cached.find(user => user.name === username)
    if (matchedUser) {
      return Promise.resolve(currentUser)
    }

    // Fetch the user from the aPI and cache it
    return axios.get('/api/users/${username}').then(response => {
      const user = response.data
      commit('CACHE_USER', user)
      return user
    })
  },
}
