
<template>
<Layout>
    <div class="container is-fullheight hero-body">
        <div class="columns is-centered">
            <div class="column is-6 is-narrow">
                <br>
                <p class="title is-3 has-text-centered is-spaced">Welcome</p>
                <p class="subtitle  is-5 has-text-justified has-margin-left-lg has-margin-right-lg">Lorem ipsum dolor sit amet, te vix falli utamur. Melius dolores sed no, ne nec corpora similique intellegebat. Cu ius nisl tantas molestie, eius officiis at mel. Homero percipitur vim in. Rebum dissentias at mea, ei quas vivendo imperdiet
                    his. Eu ludus sententiae vel, ne dicam epicuri temporibus his, nec cu quem agam. Vix purto dictas no, te omnium tritani qui, pro solum invidunt ne. Mei appetere facilisis ne, in sint putant scribentur ius. Sed ea munere mentitum, ludus
                    verterem. Id pro periculis cotidieque. </p>
            </div>
            <div class="column is-6">
                <div class="column is-12">
                    <b-tabs v-model="activeTab" position="is-centered" size="is-large">
                        <b-tab-item label="Login">
                            <form @submit.prevent="tryToLogIn">
                                <BaseInput label="E-mail or Username" v-model="email" placeholder="E-mail or Username" type="string" />
                                <BaseInput label="Password" v-model="password" placeholder="Password" minlength="6" type="password" />
                                <b-field>
                                    <a class="password-remind-link is-link is-pulled-right" @click="passwordReminder()">I forgot my password</a>
                                </b-field>
                                <BaseButton class="button is-primary is-medium is-fullwidth" :disabled="tryingToLogIn" type="submit">
                                    <BaseIcon v-if="tryingToLogIn" name="sync" spin />
                                    <span v-else>Log in</span>
                                </BaseButton>
                                <p v-if="authError">
                                    There was an error logging in to your account.
                                </p>
                            </form>
                        </b-tab-item>


                        <b-tab-item label="Register">
                            <form @submit.prevent="tryToLogIn">
                                <BaseInput label="Username" v-model="email" placeholder="Username" type="string" />
                                <BaseInput label="Your e-mail" v-model="email" placeholder="Your e-mail" type="email" />
                                <BaseInput label="Your password" v-model="password" placeholder="Your password" minlength="6" type="password" />
                                <BaseInput label="Your password again" v-model="password" placeholder="Your password again" minlength="6" type="password" />
                                <BaseButton class="button is-primary is-medium is-fullwidth" :disabled="tryingToLogIn" type="submit">
                                    <BaseIcon v-if="tryingToLogIn" name="sync" spin />
                                    <span v-else>Register</span>
                                </BaseButton>
                            </form>
                        </b-tab-item>
                    </b-tabs>
                </div>
            </div>
        </div>
    </div>
</Layout>
</template>

<script>
import {
    authMethods
} from '@state/helpers'
import Layout from '@layouts/main'
import appConfig from '@src/app.config'

export default {
    page: {
        title: 'login',
        meta: [{
            name: 'description',
            content: `Login in to ${appConfig.title}`,
        }, ],
    },
    data() {
        return {
            activeTab: 0,
            username: '',
            email: '',
            password: '',
            passwordAgain: '',
            authError: null,
            tryingToLogIn: false
        };
    },
    components: {
        Layout
    },
    methods: {
        ...authMethods,
        tryToLogIn() {
            this.tryingToLogIn = true
            // Reset the authError if it existed
            this.authError = null
            return this.logIn({
                username: this.username,
                password: this.password
            }).then(token => {
                this.tryingToLogIn = false

                // Redirect to the originally requested page, or to the home page
                this.$router.push(this.$route.query.redirectFrom || {
                    name: 'home'
                })
            }).catch(error => {
                this.tryingToLogIn = false
                this.authError = error
            })
        },
        tryToRegister() {

        }
    }
};
</script>
