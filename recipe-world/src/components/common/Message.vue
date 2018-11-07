<template>
    <b-message type="is-dark" has-icon>
        <h3 class="subtitle is-5">{{ header }}</h3>
        <p v-html="message"></p>
    </b-message>
</template>

<script>
export default {
    name: 'Message',
    props: ['data'],
    data() {
        return {
            message: '',
            header: ''
        }
    },
    created() {
        this.message = this.data.msg
        this.header = this.data.header
    }
}
</script>
