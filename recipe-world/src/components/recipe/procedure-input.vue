<template>
  <b-field :label="label" :type="error ? 'is-danger': ''" :message="error ? error: ''">
    <b-field grouped :type="error ? 'is-danger': ''">
      <b-input v-model="value.content" expanded placeholder="Step"></b-input>
      <b-field class="file has-margin-right-sm">
        <b-upload v-model="value.imageFile">
          <a class="button is-primary">
            <b-icon icon="upload"></b-icon>
            <span>Choose Image</span>
          </a>
        </b-upload>
      </b-field>
      <p class="control">
        <button @click.prevent="add" class="button">
          <b-icon icon="plus-box"></b-icon>
        </button>
      </p>
    </b-field>
  </b-field>
</template>

<script>
export default {
  $_veeValidate: {
    name() {
      return this.name
    },
    value() {
      return this.value
    },
  },
  props: ['value', 'label', 'error', 'name'],
  methods: {
    get: function() {
      return {
        content: this.value.content,
        imageFile: this.value.imageFile,
      }
    },
    add: function() {
      this.$emit('input', this.get())

      setTimeout(() => {
        this.$emit('change', this.get())
      })
    },
  },
}
</script>

<style scoped>
</style>
