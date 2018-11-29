import Vue from 'vue'
import axios from 'axios'
import VueAxios from 'vue-axios'
import jwtService from '@src/services/jwt.service'
import { toast } from '@utils/toast-helper'
class APIService {
  constructor() {
    if (!APIService.instance) {
      APIService.instance = this
    }
    Vue.use(VueAxios, axios)
    Vue.axios.defaults.baseURL = '/api/v1'
    Vue.axios.interceptors.response.use(
      function(response) {
        // Do something with response data
        if (response.error != null) {
          return Promise.reject(response)
        }
        return response.data
      },
      function(error) {
        toast.error(error.response.data.message)
        return Promise.reject(error)
      }
    )
    return APIService.instance
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

  get(resource, id = '') {
    return Vue.axios.get(`${resource}/${id}`).catch(error => {
      throw new Error(`ApiService ${error}`)
    })
  }

  post(resource, params, config) {
    return Vue.axios.post(`${resource}`, params, config)
  }

  update(resource, id, params) {
    return Vue.axios.update(`${resource}/${id}`, params)
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

const apiService = new APIService()
Object.freeze(apiService)
export default apiService
