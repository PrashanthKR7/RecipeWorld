import { Toast } from 'buefy/dist/components/toast'

export const toast = {
  error: (message = 'Something went wrong', position = 'is-bottom') => {
    Toast.open({
      duration: 2000,
      message: message,
      position: position,
      type: 'is-danger',
    })
  },
  success: (message = 'The action is successful', position = 'is-bottom') => {
    Toast.open({
      duration: 2000,
      message: message,
      position: position,
      type: 'is-success',
    })
  },
  info: (message, position = 'is-bottom') => {
    Toast.open({
      duration: 2000,
      message: message,
      position: position,
      type: 'is-info',
    })
  },
}
