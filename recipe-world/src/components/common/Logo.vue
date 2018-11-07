<template>
    <div class='is-flex is-horizontal-center'>
        <figure class='image'><img :src="logo" alt="Book Reads"></figure>
    </div>
</template>

<script>
export default {
    name: 'Logo',
    data() {
        return {
            logo: './assets/logo.png'
        }
    }
}
</script>

<style scoped>
.is-horizontal-center {
  justify-content: center;
}
</style>
