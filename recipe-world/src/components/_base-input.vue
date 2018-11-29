<template>
  <b-field :expanded="expanded" :label="label" :type="error ? 'is-danger': ''" :message="error ? error: ''">
    <b-input :value="value" v-on="listeners" :type="type" :placeholder="placeholder" :password-reveal="type === 'password'">
    </b-input>
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
  props: {
    type: {
      type: String,
      default: 'text',
      validator: val => {
        return (
          ['url', 'text', 'password', 'email', 'search', 'textarea'].indexOf(
            val
          ) !== -1
        )
      },
    },
    value: {
      type: [String, Number],
      default: '',
    },
    placeholder: {
      type: String,
      default: '',
    },
    label: {
      type: String,
      default: '',
    },
    error: {
      type: String,
      required: false,
    },
    name: {
      type: String,
      required: false,
    },
    expanded: {
      type: Boolean,
      default: false,
    },
  },
  computed: {
    listeners() {
      return {
        ...this.$listeners,
        input: event => {
          this.$emit('input', event)
        },
      }
    },
  },
}
</script>
