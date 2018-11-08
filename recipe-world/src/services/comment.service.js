import { APIService } from '@services/api.service'

export class CommentService {
  constructor() {
    this.apiService = new APIService()
  }

  get(recipeId) {
    if (typeof recipeId !== 'string') {
      throw new Error(
        'CommentService.get() recipe recipeId required to fetch comments'
      )
    }
    return this.apiService.get('recipes', `${recipeId}/comments`)
  }

  post(recipeId, payload) {
    return this.apiService.post(`recipess/${recipeId}/comments`, {
      comment: { body: payload },
    })
  }

  delete(recipeId, commentId) {
    return this.apiService.delete(`articles/${recipeId}/comments/${commentId}`)
  }
}
