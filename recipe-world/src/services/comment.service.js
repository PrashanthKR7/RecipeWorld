import apiService from '@services/api.service'

export class CommentService {
  constructor() {}

  get(recipeId) {
    if (typeof recipeId !== 'string') {
      throw new Error(
        'CommentService.get() recipe recipeId required to fetch comments'
      )
    }
    return apiService.get('recipes', `${recipeId}/comments`)
  }

  post(recipeId, payload) {
    return apiService.post(`recipess/${recipeId}/comments`, {
      comment: { body: payload },
    })
  }

  delete(recipeId, commentId) {
    return apiService.delete(`articles/${recipeId}/comments/${commentId}`)
  }
}
