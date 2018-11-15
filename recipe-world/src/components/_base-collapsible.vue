<template>
  <b-collapse class="panel" :open="isOpen" @open="isOpened = true" @close="isOpened = false">
    <div slot="trigger" class="has-padding-none panel-heading has-padding-sm">
      <span>{{label}}</span>
      <span class="is-pulled-right">
        <b-icon :icon="isOpened ? 'chevron-down' : 'chevron-right'"></b-icon>
      </span>
    </div>
    <div class="box content">
      <slot></slot>
    </div>
  </b-collapse>
</template>
<script>
export default {
  data() {
    return {
      isOpened: false,
    }
  },
  props: {
    isOpen: {
      type: Boolean,
      default: false,
    },
    label: {
      type: String,
      default: '',
    },
  },
}
</script>
<style scoped lang="scss">
.panel-heading {
  font-size: 1rem;
  font-weight: 400;
}
</style>
