<template>
    <div class="modal-card">
        <section class="modal-card-body">
            <h3 class="title has-text-centered has-text-info">I forgot my password</h3>
            <div class="box">
                    <span class="has-text-centered">Enter your email address to renew your password</span>
                    <b-field label="E-Posta">
                        <b-input v-model="mail" type="email" placeholder="E-Posta">
                        </b-input>
                    </b-field>
                    <button class="button is-info is-large is-fullwidth" @click="doLogin()">
                        Ok
                    </button>
                </div>
				<div class="has-text-centered">
					Go back to <a class="has-text-info" @click="backToLogin()">Login</a>
				</div>
        </section>
    </div>
</template>

<script>
import LoginLogo from "../features/LoginLogo"
import LoginModal from "./LoginModal"

export default {
  name: 'PasswordForgot',
  data () {
    return {
      mail: '',
    }
  },
  components: {
    LoginLogo,
    LoginModal
  },
  methods: {
    backToLogin() {
        this.$parent.close()
        this.$modal.open({
          parent: this,
          component: LoginModal,
          hasModalCArd: true,
          props: {
          }
        })
    }
  }
}
</script>

<style scoped>
.password-remind-link {
	margin-bottom: 10px;
}
.background-light {
	background-color: hsl(0, 0%, 96%);
}
</style>

