<template>
<b-field :label="label">
    <b-input :value="value" v-on="listeners" :type="type" :placeholder="placeholder" :password-reveal="type === 'password'">
    </b-input>
</b-field>
</template>

<script>
export default {
    props: {
        type: {
            type: String,
            default: 'text'
        },
        value: {
            type: [String, Number],
            default: ''
        },
        placeholder: {
            type: String,
            default: ''
        },
        label: {
            type: String,
            default: ''
        }
    },
    computed: {
        listeners() {
            return {
                ...this.$listeners,
                input: event => function(){this.$emit('input', event.target.value)},
            }
        },
    },
}
</script>
