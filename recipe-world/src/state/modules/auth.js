import jwtService from '@services/jwt.service'
import {
  INIT,
  LOGIN,
  LOGOUT,
  REGISTER,
  CHECK_AUTH,
  UPDATE_USER,
} from '@state/actions'

import { SET_AUTH, SET_ERROR, PURGE_AUTH } from '@state/mutations'
import apiService from '@services/api.service'

export const state = {
  user: {},
  errors: null,
  isAuthenticated: !!jwtService.getToken(),
}

export const getters = {
  currentUser(state) {
    return state.user
  },
  isAuthenticated(state) {
    return state.isAuthenticated
  },
}

export const actions = {
  // This is automatically run in `src/state/store.js` when the app
  // starts, along with any other actions named `init` in other modules.
  [INIT]() {},

  // Logs in the current user.
  [LOGIN]({ commit, dispatch }, credentials = {}) {
    if (state.isAuthenticated) return dispatch(CHECK_AUTH)

    return new Promise((resolve, reject) => {
      apiService
        .post('auth/signin', credentials)
        .then(({ result }) => {
          result.username = credentials.usernameOrEmail
          commit(SET_AUTH, result)
          resolve(result)
        })
        .catch(({ response }) => {
          commit(SET_ERROR, response.data.errors)
          reject(response)
        })
    })
  },

  // Logs out the current user.
  [LOGOUT]({ commit }) {
    commit(PURGE_AUTH)
  },

  [REGISTER]({ commit }, credentials) {
    return new Promise((resolve, reject) => {
      apiService
        .post('auth/signup', credentials)
        .then(({ result }) => {
          result.username = credentials.username
          commit(SET_AUTH, result)
          resolve(result)
        })
        .catch(({ response }) => {
          commit(SET_ERROR, response.data.errors)
          reject(response)
        })
    })
  },

  [CHECK_AUTH]() {
    if (jwtService.hasToken()) {
      state.user.username = jwtService.getToken(jwtService.USERNAME)
      return Promise.resolve(state.user)
    } else {
      return Promise.resolve(null)
    }
  },

  [UPDATE_USER]({ commit }, payload) {
    const { email, username, password, image, bio } = payload
    const user = {
      email,
      username,
      bio,
      image,
    }
    if (password) {
      user.password = password
    }

    return apiService.put('user', user).then(({ data }) => {
      commit(SET_AUTH, data.user)
      return data
    })
  },
}

export const mutations = {
  [SET_ERROR](state, error) {
    state.errors = error
  },
  [SET_AUTH](state, data) {
    state.isAuthenticated = true
    state.user.token = data.accessToken
    state.user.username = data.username
    state.errors = null
    jwtService.saveToken(state.user.username, jwtService.USERNAME)
    jwtService.saveToken(state.user.token)
  },

  [PURGE_AUTH](state) {
    state.isAuthenticated = false
    state.user = {}
    state.errors = {}
    jwtService.destroyToken(jwtService.USERNAME)
    jwtService.destroyToken()
  },
}
