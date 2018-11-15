import apiService from '@services/api.service'

export class FavoriteService {
  constructor() {}
  add(id) {
    return apiService.post(`recipes/${id}/favorite`)
  }

  remove(id) {
    return apiService.delete(`recipes/${id}/favorite`)
  }
}
