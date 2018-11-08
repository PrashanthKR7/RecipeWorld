import { APIService } from '@services/api.service'

export class FavoriteService {
  constructor() {
    this.apiService = new APIService()
  }
  add(id) {
    return this.apiService.post(`recipes/${id}/favorite`)
  }

  remove(id) {
    return this.apiService.delete(`recipes/${id}/favorite`)
  }
}
