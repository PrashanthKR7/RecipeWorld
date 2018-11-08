import axios from 'axios'
import jwtService from '@services/jwt.service'
import {
  INIT,
  LOGIN,
  LOGOUT,
  REGISTER,
  CHECK_AUTH,
  UPDATE_USER,
} from '../actions'

import { SET_AUTH, SET_ERROR, PURGE_AUTH } from './../mutations'
import { APIService } from '@services/api.service'

const apiService = new APIService()
export const state = {
  user: {},
  errors: null,
  isAuthenticated: !!jwtService.getToken(),
}

export const getters = {
  currentUser(state) {
    return state.user
  },
}

export const actions = {
  // This is automatically run in `src/state/store.js` when the app
  // starts, along with any other actions named `init` in other modules.
  [INIT]() {},

  // Logs in the current user.
  [LOGIN]({ commit, dispatch }, credentials = {}) {
    if (getters.isAuthenticated) return dispatch(CHECK_AUTH)

    return new Promise((resolve, reject) => {
      apiService
        .post('users/login', { user: credentials })
        .then(({ data }) => {
          commit(SET_AUTH, data.user)
          resolve(data)
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
        .post('users', { user: credentials })
        .then(({ data }) => {
          commit(SET_AUTH, data.user)
          resolve(data)
        })
        .catch(({ response }) => {
          commit(SET_ERROR, response.data.errors)
          reject(response)
        })
    })
  },

  [CHECK_AUTH]({ commit, state }) {
    if (jwtService.getToken()) {
      apiService.setHeader()
      apiService
        .get('user')
        .then(({ data }) => {
          commit(SET_AUTH, data.user)
        })
        .catch(({ response }) => {
          commit(SET_ERROR, response.data.errors)
        })
    } else {
      commit(PURGE_AUTH)
    }

    if (!state.currentUser) return Promise.resolve(null)

    return axios
      .get('/api/session')
      .then(response => {
        const user = response.data
        commit('SET_CURRENT_USER', user)
        return user
      })
      .catch(error => {
        if (error.response.status === 401) {
          commit('SET_CURRENT_USER', null)
        }
        return null
      })
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

  [SET_AUTH](state, user) {
    state.isAuthenticated = true
    state.user = user
    state.errors = null
    jwtService.saveToken(state.user.token)
  },

  [PURGE_AUTH](state) {
    state.isAuthenticated = false
    state.user = {}
    state.errors = {}
    jwtService.destroyToken()
  },
}
