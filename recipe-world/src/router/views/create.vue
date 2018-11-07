<template>
<Layout>
    <div class="container is-fullheight hero-body">
        <form @submit.prevent="createRecipe">
            <div class="columns">
                <div class="column is-one-quarter">
                    <b-field expanded label="Cover Picture">
                        <b-upload expanded v-model="dropFile" @input="coverImage()" drag-drop>
                            <section expanded class="section">
                                <div class="content has-text-centered" v-if="dropFile==null">
                                    <p>
                                        <b-icon icon="upload" size="is-large">
                                        </b-icon>
                                    </p>
                                    <p>Drop your file here or click to upload</p>
                                </div>

                                <div class="content has-text-centered" v-else>
                                    <div v-html="coverImageHTML"></div>
                                    <p>
                                        <b-icon icon="upload" size="is-small">
                                        </b-icon> Re-upload image
                                    </p>
                                </div>
                            </section>


                        </b-upload>
                    </b-field>

                    <div class="tags" v-if="dropFile">
                        <span class="tag is-primary ">
                        <span class="has-ellipsis">{{dropFile.name}}</span><button class="delete is-small" type="button" @click="deleteDropFile()"></button>
                        </span>
                    </div>

                </div>
                <div class="column">
                    <b-field label="Title" :type="errors.has('title') ? 'is-danger': ''" :message="errors.has('title') ? errors.first('title') : ''">
                        <b-input placeholder="Recipe title" v-model="recipe.title" v-validate="'required|alpha|min:4'" name="title"></b-input>
                    </b-field>

                    <b-field label="Description" :type="errors.has('description') ? 'is-danger': ''" :message="errors.has('description') ? errors.first('description') : ''">
                        <b-input name="description" v-model="recipe.description" v-validate="'required|min:50|length:300'" type="textarea"></b-input>
                    </b-field>

                    <b-field label="Ingredients" :type="errors.has('ingredientName') ? 'is-danger' : ''" :message="errors.has('ingredientName') ? errors.first('ingredientName'): ''">
                        <b-field grouped>
                            <b-input name="ingredientName" v-model="ingredientName" expanded placeholder="Ingredient"></b-input>
                            <b-input name="quantity" v-model="quantity" expanded placeholder="Quantity"></b-input>
                            <p class="control">
                                <button @click.prevent="addIngredient" class="button"><b-icon icon="plus-box"></b-icon></button>
                            </p>


                        </b-field>
                    </b-field>
                    <b-collapse class="panel" :open.sync="isIngOpen">
                        <div slot="trigger" class="has-padding-none panel-heading has-padding-sm">
                            <span>Ingredients</span>
                            <span class="is-pulled-right">
                                <b-icon :icon="isIngOpen ? 'chevron-down' : 'chevron-right'"></b-icon>
                            </span>
                        </div>
                        <div class="box content">
                            <ul class="has-margin-top-none" v-if="recipe.ingredients.length > 0">
                                <li class="is-size-6" v-for="(ingredient,index) in recipe.ingredients" :key="ingredient.id">
                                    <div class="columns">
                                        <div class="column">{{ingredient.name}}</div>
                                        <div class="column">{{ingredient.quantity}}</div>
                                        <div class="column is-narrow">
                                            <p class="control">
                                                <button @click.prevent="removeIngredient(index)" class="button is-small"><b-icon icon="minus-box"></b-icon></button>
                                            </p>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                            <p v-else>Ingredients are not added yet.</p>
                        </div>
                    </b-collapse>

                    <div class="columns is-multiline">
                        <b-field class="column is-one-third-tablet" expanded label="Cooking Time (in min)" :message="errors.has('cookingTime') ? errors.first('cookingTime'): ''" :type="errors.has('cookingTime') ? 'is-danger': ''">
                            <b-input name="cookingTime" placeholder="Cooking time in minutes" v-model.number="recipe.cookingTime" v-validate="'numeric|max_value:600|min_value:1'"></b-input>
                        </b-field>
                        <b-field class="column is-one-third-tablet" label="Cooking Difficulty" :type="{ 'is-danger': hasError }" :message="{ 'Please enter a description': hasError }">
                            <b-select expanded placeholder="Select a cooking difficulty">
                                <option value="1">Bulma</option>
                                <option value="2">Vue.js</option>
                                <option value="3">Buefy</option>
                            </b-select>
                        </b-field>
                        <b-field class="column is-one-third-tablet" label="Cooking Style" :type="{ 'is-danger': hasError }" :message="{ 'Please enter a description': hasError }">
                            <b-select expanded placeholder="Select a cooking style">
                                <option value="1">Bulma</option>
                                <option value="2">Vue.js</option>
                                <option value="3">Buefy</option>
                            </b-select>
                        </b-field>
                    </div>

                    <div class="columns is-multiline">
                        <b-field class="column is-one-third-tablet" label="Cuisine" :type="{ 'is-danger': hasError }" :message="{ 'Please enter a description': hasError }">
                            <b-select expanded placeholder="Select a cuisine">
                                <option value="1">Bulma</option>
                                <option value="2">Vue.js</option>
                                <option value="3">Buefy</option>
                            </b-select>
                        </b-field>

                        <b-field class="column is-one-third-tablet" label="Meal Type" :type="{ 'is-danger': hasError }" :message="{ 'Please enter a description': hasError }">
                            <b-select expanded placeholder="Select a meal type">
                                <option value="1">Bulma</option>
                                <option value="2">Vue.js</option>
                                <option value="3">Buefy</option>
                            </b-select>
                        </b-field>

                        <b-field class="column is-one-third-tablet" label="Diet Type" :type="{ 'is-danger': hasError }" :message="{ 'Please enter a description': hasError }">
                            <b-select expanded placeholder="Select a diet type">
                                <option value="1">Bulma</option>
                                <option value="2">Vue.js</option>
                                <option value="3">Buefy</option>
                            </b-select>
                        </b-field>
                    </div>

                    <b-field label="Stepwise Procedure" :type="{'is-danger':hasError}" :message="{'':hasError}">
                        <b-field grouped>
                            <b-input name="step" v-model="step.content" expanded placeholder="Step"></b-input>
                            <b-field class="file has-margin-right-sm">
                                <b-upload v-model="step.image">
                                    <a class="button is-primary">
                                        <b-icon icon="upload"></b-icon>
                                        <span>Choose Image</span>
                                    </a>
                                </b-upload>
                            </b-field>
                            <p class="control">
                                <button @click.prevent="addStep" class="button"><b-icon icon="plus-box"></b-icon></button>
                            </p>


                        </b-field>
                    </b-field>
                    <b-collapse class="panel" :open.sync="isStpOpen">
                        <div slot="trigger" class="has-padding-none panel-heading has-padding-sm">
                            <span>Steps</span>
                            <span class="is-pulled-right">
                                <b-icon :icon="isStpOpen ? 'chevron-down' : 'chevron-right'"></b-icon>
                            </span>
                        </div>
                        <div class="box content steps">
                            <draggable element="ol" v-model="recipe.steps" :options="dragOptions" v-if="recipe.steps.length > 0">
                                <transition-group>
                                    <li class="is-size-6" v-for="(step, index) in recipe.steps" :key="step.id">
                                        <div class="columns">
                                            <div class="column">{{step.content}}</div>
                                            <div class="column is-narrow" v-if="step.image">
                                                <img class="step-image" :src="step.imageHTML" :title="step.image.name"/>
                                            </div>
                                            <div class="column is-narrow">
                                                <p class="control">
                                                    <button @click.prevent="removeStep(index)" class="button is-small"><b-icon icon="minus-box"></b-icon></button>
                                                </p>
                                            </div>
                                        </div>
                                    </li>
                                </transition-group>
                            </draggable>
                            <p v-else>Ingredients are not added yet.</p>
                        </div>
                    </b-collapse>

                    <b-field label="External Link" :type="errors.has('link') ? 'is-danger': ''" :message="errors.has('link') ? errors.first('link') : ''">
                        <b-input placeholder="Recipe title" v-model="recipe.title" v-validate="'url'" name="link"></b-input>
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
import {
    authMethods
} from '@state/helpers'
import Layout from '@layouts/main'
import {
    Validator
} from 'vee-validate'

Validator.extend('ingredient', {
    getMessage: field => 'The ' + field + ' value is not truthy.',
    validate: value => !!value,
})

export default {
    page: {
        title: 'create',
        meta: [{
            name: 'description',
            content: `Cook up a new recipe`,
        }, ],
    },
    data() {
        return {
            hasError: false,
            dropFile: null,
            isIngOpen: false,
            isStpOpen: false,
            coverImageHTML: '',
            recipe: {
                title: null,
                description: null,
                ingredients: [],
                cuisine: null,
                mealType: null,
                dietType: null,
                cookingStyle: null,
                cookingTime: null,
                steps: [],
            },
            step: {
                content: '',
                id: '',
                image: null,
                imageHTML: '',
            },
            ingredientName: '',
            quantity: '',
            uniqueCounter: 0,
        }
    },

    components: {
        Layout,
        draggable,
    },
    methods: {
        deleteDropFile() {
            this.dropFile = null
        },
        createRecipe() {
            console.log('recipe created')
        },
        addIngredient() {
            console.log(this.ingredientName)
            if (
                this.recipe.ingredients.filter(v => v.name === this.ingredientName)
                .length > 0
            ) {
                console.log(2222)
            } else {
                this.recipe.ingredients.push({
                    name: this.ingredientName,
                    quantity: this.quantity,
                    id: this.uniqueCounter++,
                })
                this.isIngOpen = true
                this.ingredientName = ''
                this.quantity = ''
            }
        },

        removeIngredient(index) {
            this.recipe.ingredients.splice(index, 1)
        },
        async addStep() {
            if (
                this.recipe.ingredients.filter(v => v.step === this.step).length > 0
            ) {
                console.log(2222)
            } else {
                this.step.imageHTML = this.step.image ? await this.previewImage(this.step.image) : ''

                this.recipe.steps.push({
                    image: this.step.image || null,
                    content: this.step.content,
                    id: this.uniqueCounter++,
                    imageHTML: this.step.imageHTML,
                })

                this.isStpOpen = true

                this.quantity = ''
            }
        },

        removeStep(index) {
            index > -1 && this.recipe.steps.splice(index, 1)
        },

        async coverImage() {
            const encodedImage = await this.previewImage(this.dropFile)
            this.coverImageHTML = `<img src="${encodedImage}" title="${escape(this.dropFile)}"/>`
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
        dragOptions() {
            return {
                animation: 0,
                group: 'description',
                ghostClass: 'ghost',
            }
        },
    },
}
</script>

<style scoped>
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

.panel-heading {
    font-size: 1rem;
    font-weight: 400;
}
</style>