<template>
<section>
    <hr>
    <b-pagination :total="total" :order="order" :current.sync="current" :size="size" :simple="isSimple" :rounded="isRounded" :per-page="perPage" @change="pageChange">
    </b-pagination>
</section>
</template>

<script>
export default {
  data() {
    return {
      order: 'is-centered',
      size: '',
      current: 1,
      isSimple: false,
      isRounded: false,
    }
  },
  props: {
    total: Number,
    perPage: Number,
  },
  methods: {
    pageChange(value) {
      this.$emit('page-changed', value)
      this.$emit('update:current', value)
    },
  },
}
</script>
