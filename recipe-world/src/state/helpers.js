import { mapState, mapGetters, mapActions } from 'vuex'
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
} from './actions'

export const authComputed = {
  ...mapState('auth', {
    isAuthenticated: state => state.isAuthenticated,
  }),
  ...mapGetters('auth', ['currentUser']),
}

export const authMethods = mapActions('auth', [[LOGIN], [LOGOUT]])

export const recipeComputed = {
  ...mapState('library', {
    recipe: state => state.recipe,
    comments: state => state.comments,
  }),
}

export const recipeMethods = mapActions('recipe', [
  [FETCH_COMMENTS],
  [FETCH_RECIPE],
  [COMMENT_CREATE],
  [COMMENT_DESTROY],
  [RECIPE_PUBLISH],
  [RECIPE_DELETE],
  [RECIPE_EDIT],
  [FAVORITE_ADD],
  [FAVORITE_REMOVE],
  [RECIPE_RESET_STATE],
])

export const libraryComputed = {
  ...mapState('library', {
    recipes: state => state.recipes,
    recipeCount: state => state.recipesCount,
    isLoading: state => state.isLoading,
  }),
}

export const libraryMethods = mapActions('library', [[FETCH_RECIPES]])
