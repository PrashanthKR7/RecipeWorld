import apiService from '@services/api.service'

export class RecipeService {
  constructor() {}

  query(type, params) {
    return apiService.query('recipes' + (type === 'feed' ? '/feed' : ''), {
      params,
    })
  }

  meta() {
    return apiService.query('recipes/meta')
  }

  get(slug) {
    return apiService.get('recipes', slug)
  }

  create(params) {
    return apiService.post('recipes', params)
  }

  update(slug, params) {
    return apiService.update('recipes', slug, params)
  }

  delete(slug) {
    return apiService.delete(`recipes/${slug}`)
  }
}
