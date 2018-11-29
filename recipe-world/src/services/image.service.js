import apiService from '@services/api.service'

export class ImageService {
  constructor() {}

  upload(file) {
    const formData = new FormData()
    formData.append('file', file)

    return apiService
      .post('storage/upload', formData)
      .then(({ result }) => result)
  }

  uploadMultiple(files) {
    return apiService.post('storage/uploadMultiple', files)
  }
}
