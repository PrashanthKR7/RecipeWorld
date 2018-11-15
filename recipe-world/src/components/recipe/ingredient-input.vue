
<template>
  <b-field :label="label" :type="error ? 'is-danger': ''" :message="error ? error: ''">
    <b-field grouped :type="error ? 'is-danger': ''">
      <b-tooltip label="e.g: For toppings, For dough.." position="is-bottom" size="is-small">
        <b-input v-model="value.heading" expanded placeholder="Ingredient Group"></b-input>
      </b-tooltip>

      <b-tooltip class="has-margin-left-sm" label="Check if this is one of main ingredients for easily searchable" position="is-bottom" size="is-small" multilined>
        <b-checkbox v-model="value.main" native-value="true"></b-checkbox>
      </b-tooltip>
      <b-input v-model="value.name" expanded placeholder="Ingredient"></b-input>
      <b-input v-model="value.quantity" placeholder="Quantity"></b-input>
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
    get() {
      return {
        name: this.value.name,
        quantity: this.value.quantity,
        main: this.value.main,
        heading: this.value.heading,
      }
    },
    add() {
      this.$emit('input', this.get())

      setTimeout(() => {
        this.$emit('change', this.get())
      })
    }
  },
}
</script>

<style lang="scss" scoped>
</style>
