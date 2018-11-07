<template>
<div class='is-flex is-horizontal-center'>
    <figure class='image'><img :src="logo" alt="Recipe World Logo"></figure>
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
};
</script>

<style lang="scss" module>
@import '@design';
.is-horizontal-center {
    justify-content: center;
}
</style>