<template>
    <div>
        <navbar :hideNavLogins="false"></navbar>
        <div class="container is-fullheight hero-body">
            <div class="columns is-centered">
                <div class="column is-10">
                    <message :data="message"></message>
                </div>
            </div>
            <h3 class="title is-3 has-text-centered has-text-dark">Add New Recipe</h3>
            <div class="columns is-multiline is-centered">
                
                <div class="column is-12">
                    <b-field label="Recipe Name *">
                        <b-input v-model="name" placeholder="Recipe Name" expanded></b-input>
                    </b-field>
                    <b-field label="Author *">
                        <b-input v-model="author" placeholder="Recipe Author" expanded></b-input>
                    </b-field>
                    <b-field label="Year *">
                        <b-datepicker
                            v-model="year"
                            placeholder="Click to select..."
                            icon="calendar-today">
                        </b-datepicker>
                    </b-field>
                    <b-field label="Description *">
                        <b-input v-model="description" type="textarea" placeholder="Recipe Description" expanded></b-input>
                    </b-field>
                </div>
                <div class="column is-12">
                    <button class="button is-dark" @click="addRecipe">Add Recipe</button>
                </div>
            </div>
            <div class="columns is-centered">
                
            </div>
        </div>
    </div>
</template>

<script>
import Navbar from './common/Navbar'
import Message from './common/Message'

export default {
  name: 'NewRecipe',
  data() {
    return {
      name: null,
      author: null,
      year: null,
      description: null,
      message: {
        header: 'Add Your New Recipe',
        msg: 'You can add your recipes with Buefy',
      },
    }
  },
  components: {
    Navbar,
    Message,
  },
  mounted() {},
  methods: {
    addRecipe() {},
  },
}
</script>

<style scoped>
.top-15 {
  margin-top: 15px !important;
}
</style>
