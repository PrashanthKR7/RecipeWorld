import Vue from 'vue'
import axios from 'axios'
import VueAxios from 'vue-axios'
import { jwtService } from '@src/services/jwt.service'

export class APIService {
  constructor() {
    Vue.use(VueAxios, axios)
    Vue.axios.defaults.baseURL = '/api'
  }

  setHeader() {
    Vue.axios.defaults.headers.common[
      'Authorization'
    ] = `Bearer ${jwtService.getToken()}`
  }

  query(resource, params) {
    return Vue.axios.get(resource, params).catch(error => {
      throw new Error(`ApiService ${error}`)
    })
  }

  get(resource, slug = '') {
    return Vue.axios.get(`${resource}/${slug}`).catch(error => {
      throw new Error(`ApiService ${error}`)
    })
  }

  post(resource, params) {
    return Vue.axios.post(`${resource}`, params)
  }

  update(resource, slug, params) {
    return Vue.axios.update(`${resource}/${slug}`, params)
  }

  put(resource, params) {
    return Vue.axios.put(`${resource}`, params)
  }

  delete(resource) {
    return Vue.axios.delete(resource).catch(error => {
      throw new Error(`ApiService ${error}`)
    })
  }
}
