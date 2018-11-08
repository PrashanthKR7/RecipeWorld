<template>
<div id="app">
  <!--
    Even when routes use the same component, treat them
    as distinct and create the component again.
    -->
  <router-view :key="$route.fullPath"></router-view>
</div>
</template>

<script>
import appConfig from '@src/app.config'
export default {
  page: {
    titleTemplate(title) {
      title = typeof title === 'function' ? title(this.$store) : title
      return title ? `${title} | ${appConfig.title}` : appConfig.title
    },
  },
}
</script>

<style lang="scss">
@import '~normalize.css/normalize.css';
@import '~nprogress/nprogress.css';
@import '@design';
*,
*::before,
*::after {
  box-sizing: border-box;
}

d {
  color: $color-link-text;
}

h1 {
  @extend %typography-xxlarge;
}

h2 {
  @extend %typography-xlarge;
}

h3 {
  @extend %typography-large;
}

h4 {
  @extend %typography-medium;
}

h5,
h6 {
  @extend %typography-small;
}

// ===
// Vendor
// ===
#nprogress .bar {
  background: $color-link-text;
}

.app-content {
  -ms-flex: 1;
  flex: 1;
}

@media screen and (max-width: 768px) {
  html.has-navbar-fixed-top {
    padding-top: 5rem;
  }
}
</style>
