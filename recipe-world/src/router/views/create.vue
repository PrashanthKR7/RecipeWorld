<template>
  <Layout>
    <div class="container is-fullheight hero-body">
      <form @submit.prevent="validateRecipe">
        <div class="columns">
          <div class="column is-one-quarter">
            <BaseImageInput name="thumbnail" label="Cover Picture" :expanded="true" v-model="thumbnailImg" :showPreview="true" @remove="deleteImage" @upload="thumbnail = $event" />

            <hr />
            <div class="field recipe-switch">
              <span class="control-label">Non-Vegetarian</span>
              <b-switch v-model="isVeg" />
              <span class="control-label">Vegetarian</span>
            </div>
          </div>

          <div class="column">
            <BaseInput placeholder="Recipe title" name="title" label="Title" v-model="title" v-validate="'required|alpha_spaces|min:4'" :error="errors.first('title')" />
            <BaseInput name="description" label="Description" type="textarea" v-model="description" v-validate="'required|min:50|max:300'" :error="errors.first('description')" />
            <IngredientInput label="Ingredients" name="ingredient" :error="errors.first('ingredient')" v-model="ingredient" v-validate="'ingredient:' + ingrNames" @change="addIngredient" />

            <BaseCollapsible :isOpen="isIngOpen" label="Ingredients">
              <IngredientsList class="column" :ingredients="displayIngredients" :allowRemove="true" v-if="displayIngredients" @onRemovingIngredient="removeIngredient" />
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
            <BaseCollapsible :isOpen="isStpOpen" label="Instructions">
              <draggable element="ol" v-model="instructions" :options="dragOptions" v-if="instructions.length > 0">
                <transition-group>
                  <RecipeInstruction v-for="step in instructions" :instruction="step" :key="step.idx" @removeStep="removeStep" :instructions="instructions" :allowRemove="true" />
                </transition-group>
              </draggable>
              <p v-else>Instructions are not added yet.</p>
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
import Layout from '@layouts/main'
import draggable from 'vuedraggable'
import { RECIPE_PUBLISH, UPLOAD_FILE } from '@state/actions'
import {
  authComputed,
  recipeComputed,
  recipeMethods,
  uploadMethods,
} from '@state/helpers'
import IngredientInput from '@components/recipe/ingredient-input'
import ProcedureInput from '@components/recipe/procedure-input'
import IngredientsList from '@components/recipe/ingredients-list'
import RecipeInstruction from '@components/recipe/recipe-instruction'
import { toast } from '@utils/toast-helper'

import { groupBy } from 'lodash'

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
    IngredientsList,
    RecipeInstruction,
  },
  props: ['meta'],
  data() {
    return {
      thumbnailImg: null,
      hasError: false,
      isIngOpen: false,
      isStpOpen: false,
      step: {
        content: '',
        imageFile: null,
        image: '',
      },
      uniqueCounter: 0,
      ingredient: {
        heading: '',
        name: '',
        quantity: '',
        main: false,
      },
      displayIngredients: null,
    }
  },
  methods: {
    ...uploadMethods,
    ...recipeMethods,
    deleteImage() {
      this.thumbnail = null
      this.thumbnailImg = null
    },
    createRecipe(recipe) {
      this.keyIngredients = [...this.ingredients]
        .filter(v => v.main)
        .map(v1 => ({ name: v1.name }))
      this.author = this.currentUser.username
      this[RECIPE_PUBLISH](recipe)
        .then(() => {
          toast.success("You've successfully created recipe!")
        })
        .catch(e => {
          console.log(e)
        })
    },
    validateRecipe() {
      if (this.isAuthenticated) {
        this.$validator.validateAll().then(valid => {
          if (!valid) {
            let time = 10
            let error = ''
            if (this.ingredients.length === 0) {
              error = 'No ingredients are given for the recipe!'
              time = 2500
            } else if (this.instructions.length === 0) {
              error = 'No instructions are given for the recipe!'
              time = 2500
            } else if (
              this.$validator.errors.items.filter(
                v => v.field === 'ingredient' || v.field === 'step'
              ).length !== this.$validator.errors.items.length
            ) {
              error = 'Fill all the fields to create the recipe!'
              time = 2500
            } else {
              this.createRecipe(this.recipe)
            }
            setTimeout(() => {
              this.$validator.reset()
            }, time)
            error !== '' && toast.error(error)
          } else {
            this.createRecipe(this.recipe)
          }
        })
      }
    },
    addIngredient() {
      if (!this.errors.has('ingredient')) {
        this.ingredient.heading =
          this.ingredient.heading.trim().length > 0
            ? this.ingredient.heading
            : 'Main Ingredients'
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

        this.displayIngredients = groupBy(this.ingredients, 'heading')
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

    removeById(id, data) {
      const copy = [...data]
      const index = copy.findIndex(v => v.id === id)
      copy.splice(index, 1)
      data = [...copy]
      return data
    },

    removeIngredient(id) {
      this.ingredients = this.removeById(id, this.ingredients)
      this.displayIngredients = this.ingredients.length
        ? groupBy(this.ingredients, 'heading')
        : null
      setTimeout(() => {
        this.errors.remove('ingredient')
      })
    },

    async addStep() {
      if (!this.errors.has('step')) {
        const image = this.step.imageFile
          ? await this[UPLOAD_FILE](this.step.imageFile).then(
              response => response.fileDownloadUri
            )
          : ''

        this.instructions = [
          ...this.instructions,
          {
            idx: this.uniqueCounter++,
            content: this.step.content,
            imageFile: this.step.imageFile || null,
            image: image,
          },
        ]

        this.step.content = ''
        this.step.imageFile = null
        this.step.image = ''
        this.isStpOpen = true
        setTimeout(() => {
          this.errors.remove('step')
        })
      }
    },

    removeStep(id) {
      this.instructions = this.removeById(id, this.instructions)
      setTimeout(() => {
        this.errors.remove('step')
      })
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
@import '@design';
.recipe-switch {
  align-items: center;
  display: flex;
  .control-label:first-child {
    padding-right: 0.5rem;
  }
}
.box.content ul,
ol {
  list-style-type: none !important;
  margin-top: 0rem;
}

.ghost {
  opacity: 0.5;
  background: #c8ebfb;
}
</style>
