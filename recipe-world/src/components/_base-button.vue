<template>
<button :class="$style.button" v-on="$listeners">
    <slot />
</button>
</template>

<script>
export default {}
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
