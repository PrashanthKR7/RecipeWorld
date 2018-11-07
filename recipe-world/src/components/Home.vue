<template>
<div class="app-container">
    <Header />
    <router-view></router-view>
    <Footer />
</div>
</template>

<script>
import Header from '@/components/Header';
import Footer from '@/components/Footer';
export default {
    name: 'Home',
    components: {
        Header,
        Footer
    }
}
</script>

<style scoped>
.app-container {
    display: -ms-flexbox;
    display: flex;
    -ms-flex-direction: column;
    flex-direction: column;
    min-height: 100vh
}
</style>