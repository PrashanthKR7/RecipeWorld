const ID_TOKEN_KEY = 'user_id_token'

const getToken = () => {
  return window.localStorage.getItem(ID_TOKEN_KEY)
}

const saveToken = token => {
  window.localStorage.setItem(ID_TOKEN_KEY, token)
}

const destroyToken = () => {
  window.localStorage.removeItem(ID_TOKEN_KEY)
}
const hasToken = () => {
  console.log(window.localStorage.hasOwnProperty('user_id_token'))
  return window.localStorage.hasOwnProperty('user_id_token')
}

export default { getToken, saveToken, destroyToken, hasToken }
