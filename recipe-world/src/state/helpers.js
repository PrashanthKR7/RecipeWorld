import { mapState, mapGetters, mapActions } from 'vuex'
import { recipeStructure } from '@utils/recipe-structure'
import { mapFields } from 'vuex-map-fields'

import {
  LOGIN,
  LOGOUT,
  FETCH_COMMENTS,
  FETCH_RECIPE,
  COMMENT_CREATE,
  COMMENT_DESTROY,
  RECIPE_PUBLISH,
  RECIPE_DELETE,
  RECIPE_EDIT,
  FAVORITE_ADD,
  FAVORITE_REMOVE,
  RECIPE_RESET_STATE,
  FETCH_RECIPES,
  REGISTER,
  UPLOAD_FILE,
} from '@state/actions'

export const authComputed = {
  ...mapState('auth', {
    isAuthenticated: state => state.isAuthenticated,
  }),
  ...mapGetters('auth', ['currentUser']),
}

export const authMethods = mapActions('auth', [LOGIN, LOGOUT, REGISTER])

export const recipeComputed = {
  ...mapState('recipe', {
    comments: state => state.comments,
  }),
  ...mapFields('recipe', Object.keys(recipeStructure).map(v => 'recipe.' + v)),
}

export const recipeMethods = mapActions('recipe', [
  FETCH_COMMENTS,
  FETCH_RECIPE,
  COMMENT_CREATE,
  COMMENT_DESTROY,
  RECIPE_PUBLISH,
  RECIPE_DELETE,
  RECIPE_EDIT,
  FAVORITE_ADD,
  FAVORITE_REMOVE,
  RECIPE_RESET_STATE,
])
export const uploadMethods = mapActions('recipe', [UPLOAD_FILE])

export const libraryComputed = {
  ...mapState('library', {
    recipes: state => state.recipes,
    recipeCount: state => state.recipesCount,
    isLoading: state => state.isLoading,
  }),
}

export const libraryMethods = mapActions('library', [FETCH_RECIPES])
