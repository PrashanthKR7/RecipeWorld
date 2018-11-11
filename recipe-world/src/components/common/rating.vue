<template>
  <fieldset class="starability-basic rating">
    <legend v-if="legend">{{ legend }}</legend>
    <span :key="index" v-for="(item, index) in items">
      <input type="radio" :id="uuid(index)" name="rating" :value="item.value" :checked="hasChecked(index)" @change="change($event)">
      <label class="touchable" :for="uuid(index)" :title="item.title || ''">{{ item.label || '' }}</label>
    </span>
  </fieldset>
</template>

<script>
export default {
  props: {
    legend: String,
    items: {
      type: Array,
      default: () => [],
    },
    value: {
      type: Number,
      default: -1,
    },
  },
  data() {
    return {
      selected: this.value,
    }
  },
  methods: {
    uuid(index) {
      return `rating-${this._uid}-item-${index}`
    },
    hasChecked(index) {
      return this.count - index === this.selected
    },
    change(e) {
      this.selected = e.target.value >>> 0
      this.$emit('change', this.selected)
    },
  },
  computed: {
    count() {
      return this.items.length
    },
  },
}
</script>

<style scoped>
</style>
