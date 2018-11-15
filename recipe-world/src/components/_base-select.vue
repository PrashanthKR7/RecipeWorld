<template>
  <b-field :class="classes" :label="label" :type="error ? 'is-danger': ''" :message="error ? error: ''">
    <b-select v-on="listeners" :value="value" :expanded="expanded" :placeholder="placeholder">
      <option v-for="opt in options" :key="opt.id" :value="opt.id">{{opt.name}}</option>
    </b-select>
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
    },
    classes: {
      type: String,
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
    options: {
      type: Array,
      default: new Array(),
    },
    expanded: {
      type: Boolean,
      default: false,
    },
    name: {
      type: String,
      required: true,
    },
    value: {
      type: String,
      default: null,
    },
  },
  computed: {
    listeners() {
      return {
        ...this.$listeners,
        input: event => this.$emit('input', event),
        change: event => {
          console.log(event)
          this.$emit('input', event)
        },
      }
    },
  },
}
</script>

<style lang="scss" module>
@import '@design';
</style>
