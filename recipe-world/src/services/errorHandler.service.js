import Vue from 'vue'

function errorResponseHandler(error) {
  // check for errorHandle config
  if (
    error.config.hasOwnProperty('errorHandle') &&
    error.config.errorHandle === false
  ) {
    return Promise.reject(error)
  }

  // if has response show the error
  if (error.response) {
    alert(error.response.data.message)
  }
}

Vue.axios.interceptors.response.use(
  function(response) {
    // Do something with response data
    if (response.error != null) {
      return Promise.reject(response)
    }
    return response
  },
  function(error) {
    // Do something with response error
    return Promise.reject(error)
  }
)

export default errorResponseHandler
