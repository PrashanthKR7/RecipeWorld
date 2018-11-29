const ID_TOKEN_KEY = 'user_id_token'
const USERNAME = 'username'

const getToken = (token_key = ID_TOKEN_KEY) => {
  return window.localStorage.getItem(token_key)
}

const saveToken = (token, token_key = ID_TOKEN_KEY) => {
  window.localStorage.setItem(token_key, token)
}

const destroyToken = (token_key = ID_TOKEN_KEY) => {
  window.localStorage.removeItem(token_key)
}
const hasToken = (token_key = ID_TOKEN_KEY) => {
  return window.localStorage.hasOwnProperty(token_key)
}

export default {
  getToken,
  saveToken,
  destroyToken,
  hasToken,
  USERNAME,
  ID_TOKEN_KEY,
}
