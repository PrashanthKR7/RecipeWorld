<template>
<Layout>
  <div class="container is-fullheight hero-body">
    <h1>
      <BaseIcon name="user" /> {{ user.name }} Profile
    </h1>
    <pre>{{ user }}</pre>
  </div>
</Layout>
</template>
<script>
import Layout from '@layouts/main'
export default {
  page() {
    return {
      title: this.user.name,
      meta: [
        {
          name: 'description',
          content: `The user profile for ${this.user.name}.`,
        },
      ],
    }
  },
  components: {
    Layout,
  },
  props: {
    user: {
      type: Object,
      required: true,
    },
  },
}
</script>
