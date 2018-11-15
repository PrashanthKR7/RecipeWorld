<template>
  <Layout>
    <div class="container is-fullheight hero-body">
      <form @submit.prevent="createRecipe">
        <div class="columns">
          <div class="column is-one-quarter">
            <BaseImageInput name="coverImage" label="Cover Picture" :expanded="true" v-model="coverImage" :showPreview="true" @remove="deleteImage" @input="coverImage = $event" />
          </div>

          <div class="column">
            <BaseInput placeholder="Recipe title" name="title" label="Title" v-model="title" v-validate="'required|alpha_spaces|min:4'" :error="errors.first('title')" />
            <BaseInput name="description" label="Description" type="textarea" v-model="description" v-validate="'required|min:50|max:300'" :error="errors.first('description')" />
            <IngredientInput label="Ingredients" name="ingredient" :error="errors.first('ingredient')" v-model="ingredient" v-validate="'ingredient:' + ingrNames" @change="addIngredient" />

            <BaseCollapsible :isOpen="isIngOpen" label="Ingredients">
              <ul class="has-margin-top-none" v-if="ingredients.length > 0">
                <li class="is-size-6" v-for="(ingredient,index) in ingredients" :key="ingredient.id">
                  <div class="columns">
                    <div class="column">{{ingredient.name}}</div>
                    <div class="column">{{ingredient.quantity}}</div>
                    <div class="column is-narrow">
                      <p class="control">
                        <button @click.prevent="removeIngredient(index)" class="button is-small">
                          <b-icon icon="minus-box"></b-icon>
                        </button>
                      </p>
                    </div>
                  </div>
                </li>
              </ul>
              <p v-else>Ingredients are not added yet.</p>
            </BaseCollapsible>

            <div class="columns is-multiline">

              <b-field grouped class="column is-one-third-tablet" expanded>
                <BaseInput :expanded="true" placeholder="in minutes" name="prepTime" label="Prep Time" v-model="preparingTime" v-validate="'numeric|max_value:600|min_value:1'" :error="errors.first('prepTime')" />
                <BaseInput :expanded="true" placeholder="in minutes" name="cookTime" label="Cook Time" v-model="cookingTime" v-validate="'required|numeric|max_value:600|min_value:1'" :error="errors.first('cookTime')" />
              </b-field>

              <BaseSelect v-model="difficulty" v-validate="'required|included:'+ meta.difficulty.map(v => v.id).join(',')" :expanded="true" name="difficulty" classes="column is-one-third-tablet" label="Cooking Difficulty" placeholder="Select a cooking difficulty" :options="meta.difficulty" :error="errors.first('difficulty')" />
              <BaseSelect v-model="cookingstyle" v-validate="'required|included:'+ meta.cookingstyle.map(v => v.id).join(',')" :expanded="true" name="cookingstyle" classes="column is-one-third-tablet" label="Cooking Style" placeholder="Select a cooking style" :options="meta.cookingstyle" :error="errors.first('cookingstyle')" />
            </div>

            <div class="columns is-multiline">
              <BaseSelect v-model="cuisine" v-validate="'required|included:'+ meta.cuisine.map(v => v.id).join(',')" :expanded="true" name="cuisine" classes="column is-one-third-tablet" label="Cuisine" placeholder="Select a cuisine" :options="meta.cuisine" :error="errors.first('cuisine')" />
              <BaseSelect v-model="mealtype" v-validate="'required|included:'+ meta.mealtype.map(v => v.id).join(',')" :expanded="true" name="mealtype" classes="column is-one-third-tablet" label="Meal Type" placeholder="Select a meal type" :options="meta.mealtype" :error="errors.first('mealtype')" />
              <BaseSelect v-model="diettype" v-validate="'required|included:'+ meta.diettype.map(v => v.id).join(',')" :expanded="true" name="diettype" classes="column is-one-third-tablet" label="Diet Type" placeholder="Select a diet type" :options="meta.diettype" :error="errors.first('diettype')" />
            </div>

            <ProcedureInput v-model="step" label="Stepwise Procedure" name="step" :error="errors.first('step')" v-validate="'step'" @change="addStep" />
            <BaseCollapsible :isOpen="isStpOpen" label="Steps">
              <draggable element="ol" v-model="steps" :options="dragOptions" v-if="steps.length > 0">
                <transition-group>
                  <li class="is-size-6" v-for="(step, index) in steps" :key="step.id">
                    <div class="columns">
                      <div class="column">{{step.content}}</div>
                      <div class="column is-narrow" v-if="step.image">
                        <img class="step-image" :src="step.imageHTML" :title="step.image.name" />
                      </div>
                      <div class="column is-narrow">
                        <p class="control">
                          <button @click.prevent="removeStep(index)" class="button is-small">
                            <b-icon icon="minus-box"></b-icon>
                          </button>
                        </p>
                      </div>
                    </div>
                  </li>
                </transition-group>
              </draggable>
              <p v-else>Steps are not added yet.</p>
            </BaseCollapsible>
            <BaseInput v-model="link" label="External Link" name="link" placeholder="Recipe link (YouTube, Instagram or Facebook page etc)" v-validate="'url'" :error="errors.first('link')" />

            <b-field label="Add some tags" expanded>
              <b-taginput ellipsis maxlength="15" maxtags="5" v-model="tags" placeholder="Add a tag">
              </b-taginput>
            </b-field>
            <b-field expanded>
              <button class="button is-primary is-medium is-fullwidth">Create</button>
            </b-field>
          </div>
        </div>
      </form>
    </div>
    <output id="list"></output>
  </Layout>
</template>

<script>
import draggable from 'vuedraggable'
import { authComputed, recipeComputed } from '@state/helpers'
import IngredientInput from '@components/recipe/ingredient-input'
import ProcedureInput from '@components/recipe/procedure-input'
import { groupBy } from 'lodash'
import Layout from '@layouts/main'
import { mapFields } from 'vuex-map-fields'
import { recipeStructure, recipeArrayFields } from '@utils/recipe-structure'

export default {
  page: {
    title: 'create',
    meta: [
      {
        name: 'description',
        content: `Cook up a new recipe`,
      },
    ],
  },
  components: {
    Layout,
    draggable,
    IngredientInput,
    ProcedureInput,
  },
  props: ['meta'],
  data() {
    return {
      hasError: false,
      isIngOpen: false,
      isStpOpen: false,
      step: {
        content: '',
        image: null,
        imageHTML: '',
      },
      uniqueCounter: 0,
      ingredient: {
        heading: '',
        name: '',
        quantity: '',
        main: false,
      },
    }
  },
  methods: {
    deleteImage() {
      this.coverImage = null
    },
    createRecipe() {
      if (this.isAuthenticated) {
        this.$validator.validateAll().then(result => {
          if (!result) {
            // console.log(this.recipe)
            console.log('not created')
            setTimeout(() => {
              this.$validator.reset()
            }, 2500)
          } else {
            console.log('created')
          }
        })
      }
    },
    addIngredient() {
      console.log(this.errors)
      if (!this.errors.has('ingredient')) {
        this.ingredients = [
          ...this.ingredients,
          {
            name: this.ingredient.name,
            quantity: this.ingredient.quantity,
            main: this.ingredient.main,
            heading: this.ingredient.heading,
            id: this.uniqueCounter++,
          },
        ]

        console.log(groupBy(this.ingredients, 'heading'))

        this.ingredient.name = ''
        this.ingredient.quantity = ''
        this.ingredient.heading = ''
        this.ingredient.main = false
        this.isIngOpen = true

        setTimeout(() => {
          this.errors.remove('ingredient')
        })
      }
    },

    removeIngredient(index) {
      const copyIngredients = [...this.ingredients]
      copyIngredients.splice(index, 1)
      this.ingredients = [...copyIngredients]
      setTimeout(() => {
        this.errors.remove('ingredient')
      })
    },

    async addStep() {
      if (!this.errors.has('step')) {
        this.step.imageHTML = this.step.image
          ? await this.previewImage(this.step.image)
          : ''
        this.steps.push({
          id: this.uniqueCounter++,
          content: this.step.content,
          image: this.step.image || null,
          imageHTML: this.step.imageHTML,
        })

        this.step.content = ''
        this.step.image = null
        this.step.imageHTML = ''
        this.isStpOpen = true
        setTimeout(() => {
          this.errors.remove('step')
        })
      }
    },

    removeStep(index) {
      index > -1 && this.steps.splice(index, 1)
    },

    previewImage(file) {
      // Only process image files.
      if (!file.type.match('image.*')) {
        return
      }
      const reader = new FileReader()

      return new Promise((resolve, reject) => {
        reader.onerror = () => {
          reader.abort()
          reject('')
        }
        reader.onload = () => {
          resolve(reader.result)
        }
        // Read in the image file as a data URL.
        reader.readAsDataURL(file)
      })
    },
  },
  computed: {
    ...recipeComputed,
    ...authComputed,
    dragOptions() {
      return {
        animation: 0,
        group: 'description',
        ghostClass: 'ghost',
      }
    },
    ingrNames() {
      return this.ingredients.map(v => v.name)
    },
  },
}
</script>

<style scoped lang="scss">
.step-image {
  max-width: 300px !important;
}

.box.content ul,
ol {
  margin-top: 0px;
}

.ghost {
  opacity: 0.5;
  background: #c8ebfb;
}
</style>
