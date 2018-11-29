
<template>
  <Layout>
    <div class="container is-fullheight has-padding-top-md hero-body">
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
                <form @submit.prevent="" data-vv-scope="login">
                  <b-field label="Username" :type="errors.has('login.username') ? 'is-danger': ''" :message="errors.has('login.username') ? errors.first('login.username') : ''">
                    <b-input v-model="username" name="username" v-validate="'required'" type="string" placeholder="Username">
                    </b-input>
                  </b-field>
                  <b-field label="Password" :type="errors.has('login.password') ? 'is-danger': ''" :message="errors.has('login.password') ? errors.first('login.password') : ''">
                    <b-input v-model="password" name="password" v-validate="'required'" type="password" placeholder="Password">
                    </b-input>
                  </b-field>
                  <b-field>
                    <a class="password-remind-link is-link is-pulled-right" @click="passwordReminder()">I forgot my password</a>
                  </b-field>
                  <BaseButton class="button is-primary is-medium is-fullwidth" @click="tryToLogIn" :disabled="tryingToLogIn" type="submit">
                    <BaseIcon v-if="tryingToLogIn" name="sync" spin />
                    <span v-else>Log in</span>
                  </BaseButton>

                </form>
              </b-tab-item>

              <b-tab-item label="Register">
                <form @submit.prevent="" data-vv-scope="register">
                  <b-field label="Name" :type="errors.has('register.name') ? 'is-danger': ''" :message="errors.has('register.name') ? errors.first('register.name') : ''">
                    <b-input v-model="name" name="name" v-validate="'required'" type="string" placeholder="Your name">
                    </b-input>
                  </b-field>

                  <b-field label="Username" :type="errors.has('register.username') ? 'is-danger': ''" :message="errors.has('register.username') ? errors.first('register.username') : ''">
                    <b-input v-model="username" name="username" v-validate="'required'" type="string" placeholder="Your unique username">
                    </b-input>
                  </b-field>

                  <b-field label="Your e-mail" :type="errors.has('register.email') ? 'is-danger': ''" :message="errors.has('register.email') ? errors.first('register.email') : ''">
                    <b-input v-model="email" name="email" v-validate="'required|email'" type="email" placeholder="Your e-mail">
                    </b-input>
                  </b-field>

                  <b-field label="Your password" :type="errors.has('register.password') ? 'is-danger': ''" :message="errors.has('register.password') ? errors.first('register.password') : ''">
                    <b-input v-model="password" name="password" v-validate="'required|confirmed:password'" type="password" placeholder="Your password">
                    </b-input>
                  </b-field>

                  <!--  password confirmation/matching doesn't work for b-input, works only on native html5 elements -->
                  <b-field label="Confirm password" :type="errors.has('register.password') ? 'is-danger': ''">
                    <div class="control has-icons-right is-clearfix" aria-required="true" :aria-invalid="errors.has('register.password')">
                      <input autocomplete="on" ref="password" type="password" placeholder="Your password again" :class="['input', {'is-danger': errors.has('register.password')}]">
                      <!---->
                      <span v-if="errors.has('register.password')" class="icon is-right has-text-danger">
                        <i class="mdi mdi-alert-circle mdi-24px"></i>
                      </span>
                      <!---->
                    </div>
                  </b-field>

                  <BaseButton class="button is-primary is-medium is-fullwidth" @click="tryToRegister" :disabled="tryingToLogIn" type="submit">
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
import { authMethods } from '@state/helpers'
import Layout from '@layouts/main'
import PasswordForgot from '@components/modals/password-forgot'
import appConfig from '@src/app.config'
import { toast } from '@utils/toast-helper'
import { LOGIN, REGISTER } from '@state/actions'
export default {
  page: {
    title: 'login',
    meta: [
      {
        name: 'description',
        content: `Login in to ${appConfig.title}`,
      },
    ],
  },
  data() {
    return {
      activeTab: 0,
      name: '',
      username: '',
      email: '',
      password: '',
      tryingToLogIn: false,
    }
  },
  components: {
    Layout,
  },
  methods: {
    ...authMethods,
    passwordReminder() {
      this.$modal.open({
        parent: this,
        props: {},
        component: PasswordForgot,
        hasModalCard: true,
      })
    },
    tryToLogIn() {
      this.$validator.validateAll('login').then(result => {
        if (result) {
          this.tryingToLogIn = true
          return this[LOGIN]({
            usernameOrEmail: this.username,
            password: this.password,
          })
            .then(() => {
              this.tryingToLogIn = false
              toast.success("You're successfully logged in!")
              // Redirect to the originally requested page, or to the home page
              this.$router.push(
                this.$route.query.redirectFrom || {
                  name: 'home',
                }
              )
            })
            .catch(() => {
              this.tryingToLogIn = false
            })
        }

        toast.error('Fill all the fields before logging in!')
      })
    },
    tryToRegister() {
      this.$validator.validateAll('register').then(result => {
        if (result) {
          this.tryingToLogIn = true
          return this[REGISTER]({
            name: this.name,
            username: this.username,
            email: this.email,
            password: this.password,
          })
            .then(() => {
              this.tryingToLogIn = false
              toast.success("You've successfully registered into recipe world!")
              // Redirect to the originally requested page, or to the home page
              this.$router.push(
                this.$route.query.redirectFrom || {
                  name: 'home',
                }
              )
            })
            .catch(() => {
              this.tryingToLogIn = false
            })
        }

        toast.error('Fill all the fields before logging in!')
      })
    },
  },
}
</script>
