<template>
<b-field :class="classes" :label="label" :type="type" :message="message">
  <b-select :expanded="expanded" :placeholder="placeholder">
    <option v-for="opt in options" :key="opt.id" :value="opt.id" v-validate="validators">{{opt.value}}</option>
  </b-select>
</b-field>
</template>

<script>
export default {
  props: {
    type: {
      type: String,
      default: 'text',
    },
    classes: {
      type: Array,
      default: new Array(),
    },
    placeholder: {
      type: String,
      default: '',
    },
    label: {
      type: String,
      default: '',
    },

    message: {},
    validators: {},
    options: {
      type: Array,
      default: new Array(),
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
        input: event =>
          function() {
            this.$emit('input', event.target.value)
          },
      }
    },
  },
}
</script>

<style lang="scss" module>
@import '@design';
.button {
  @extend %typography-small;
  &:disabled {
    cursor: not-allowed;
  }
}
</style>
