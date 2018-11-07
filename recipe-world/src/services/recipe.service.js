import { APIService } from '@services/api.service'

export class RecipeService {
  constructor() {
    this.apiService = new APIService()
  }

  query(type, params) {
    return this.apiService.query('recipes' + (type === 'feed' ? '/feed' : ''), {
      params,
    })
  }

  get(slug) {
    return this.apiService.get('recipes', slug)
  }

  create(params) {
    return this.apiService.post('recipes', { recipe: params })
  }

  update(slug, params) {
    return this.apiService.update('recipes', slug, params)
  }

  delete(slug) {
    return this.apiService.delete(`recipes/${slug}`)
  }
}
