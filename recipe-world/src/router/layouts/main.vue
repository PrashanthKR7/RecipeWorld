<template>
<div :class="$style['app-container']">
    <NavBar />
    <slot />
</div>
</template>
<script>
import NavBar from '@components/nav-bar'
export default {
  components: {
    NavBar,
  },
}
</script>
<style lang="scss" module>
@import '@design';
.app-container {
  display: -ms-flexbox;
  display: flex;
  -ms-flex-direction: column;
  flex-direction: column;
}
</style>
