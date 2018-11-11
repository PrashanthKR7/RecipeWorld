<template>
  <Layout>
    <b-loading :is-full-page="true" :active="true"></b-loading>
  </Layout>
</template>

<script>
import Layout from '@layouts/main'
export default {
  page: {
    title: 'Loading page...',
    meta: [
      {
        name: 'description',
        content: 'Loading page...',
      },
    ],
  },
  components: {
    Layout,
  },
}
</script>

<style lang="scss" module>
@import '@design';

.loadingIcon {
  @extend %typography-xxlarge;

  display: block;
  margin: 0 auto;

  &:global(.v-enter-active) {
    transition: opacity 1s;
  }

  &:global(.v-enter) {
    opacity: 0;
  }
}
</style>
