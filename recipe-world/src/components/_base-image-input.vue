<template>
  <div>
    <b-field :expanded="expanded" :label="label">
      <b-upload :expanded="expanded" name="image" :value="value" @input="onImageUpload($event)" drag-drop accept="image/*" required>
        <section :expanded="expanded" class="section">
          <div class="content has-text-centered" v-if="value==null">
            <p>
              <b-icon icon="upload" size="is-large">
              </b-icon>
            </p>
            <p>Drop your file here or click to upload</p>
          </div>

          <div v-if="value!==null && showPreview" class="content has-text-centered">
            <div v-html="imageHTML"></div>
            <p>
              <b-icon icon="upload" size="is-small">
              </b-icon> Re-upload image
            </p>
          </div>

          <div class="content has-text-centered" v-if="value!==null && !showPreview">
            <p>
              <b-icon icon="upload" size="is-large">
              </b-icon>
            </p>
            <p>Drop your file here or click to re-upload</p>
          </div>
        </section>
      </b-upload>
    </b-field>

    <div class="tags" v-if="value">
      <span class="tag is-primary ">
        <span class="has-ellipsis">{{value.name}}</span>
        <button class="delete is-small" type="button" @click="$emit('remove')"></button>
      </span>
    </div>
  </div>
</template>

<script>
import { UPLOAD_FILE } from '@state/actions'
import { uploadMethods } from '@state/helpers'
import { toast } from '@utils/toast-helper'

export default {
  data() {
    return {
      imageHTML: '',
    }
  },
  props: {
    label: { type: String, default: '' },
    value: {},
    imageUrl: { type: String, default: null },
    expanded: { type: Boolean, default: false },
    showPreview: { type: Boolean, default: false },
  },
  methods: {
    ...uploadMethods,
    async onImageUpload(imageFile) {
      this.$emit('input', imageFile)

      this[UPLOAD_FILE](imageFile)
        .then(response => {
          if (this.showPreview && imageFile) {
            // const encodedImage = await this.previewImage(imageFile)
            this.imageHTML = `<img src="${
              response.fileDownloadUri
            }" title="${escape(imageFile.name)}"/>`
          }
          this.$emit('upload', response.fileDownloadUri)
          toast.success('Image uploaded successfully')
        })
        .catch(e => {
          console.log(e)
        })
    },
    previewImage(imageFile) {
      // Only process image files.
      if (imageFile && !imageFile.type.match('image.*')) {
        return
      }
      const reader = new FileReader()

      return new Promise((resolve, reject) => {
        reader.onerror = () => {
          reader.abort()
          reject('')
        }
        reader.onload = () => {
          resolve(reader.result)
        }
        // Read in the image file as a data URL.
        reader.readAsDataURL(imageFile)
      })
    },
  },

  async mounted() {
    if (this.showPreview && this.value && this.imageUrl) {
      // const encodedImage = await this.previewImage(this.value)
      this.imageHTML = `<img src="${this.imageUrl}" title="${escape(
        this.value.name
      )}"/>`
    }
  },
}
</script>

<style scoped>
</style>
