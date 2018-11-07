
<template>
<div>
    <div class="container is-fullheight hero-body">
        <div class="columns is-centered">
            <div class="column is-6">
                <logo></logo>
                <h3 class="title has-text-centered has-text-dark">User Login</h3>
                <div class="box">
                    <b-field label="E-Mail">
                        <b-input v-model="mail" type="email" placeholder="E-Mail">
                        </b-input>
                    </b-field>

                    <b-field label="Password">
                        <b-input v-model="password" type="password" placeholder="Password" minlength="6" password-reveal>
                        </b-input>
                    </b-field>
                    <b-field>
                        <a class="password-remind-link has-text-dark is-pulled-right" @click="passwordReminder()">I forgot my password</a>
                    </b-field>
                    <button class="button is-dark is-large is-fullwidth" @click="doLogin()">
                        Login
                        </button>
                </div>
                <div class="has-text-centered">
                    <router-link to="/register">Signup!</router-link>
                </div>

            </div>
        </div>
    </div>


</div>
</template>

<script>
import Logo from './common/Logo'
import PasswordForgot from './modals/PasswordForgot'
export default {
    name: 'Login',
    data() {
        return {
            mail: '',
            password: ''
        }
    },
    components: {
        Logo,
        PasswordForgot
    },
    methods: {
        passwordReminder() {
            this.$modal.open({
                parent: this,
                component: PasswordForgot,
                hasModalCard: true,
                props: {}

            })
        },
        doLogin() {
            this.$router.push('/dashboard')
        }
    }
};
</script>

<style scoped>

</style>