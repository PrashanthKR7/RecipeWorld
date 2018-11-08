import { FETCH_RECIPES } from './../actions'
import { FETCH_START, FETCH_END, UPDATE_RECIPE_IN_LIST } from '../mutations'
import { RecipeService } from '@services/recipe.service'

const recipeService = new RecipeService()
export const state = {
  recipes: [],
  isLoading: true,
  recipesCount: 0,
}

export const actions = {
  [FETCH_RECIPES]({ commit }, params) {
    commit(FETCH_START)
    return recipeService.query(params.type, params.filters).then(({ data }) => {
      commit(FETCH_END, data)
    })
  },
}

export const mutations = {
  [FETCH_START](state) {
    state.isLoading = true
  },

  [FETCH_END](state, { recipes, count }) {
    state.isLoading = false
    state.recipes = recipes
    state.recipesCount = count
  },

  [UPDATE_RECIPE_IN_LIST](state, data) {
    state.recipes = state.recipes.map(recipe => {
      if (recipe.id !== data.id) {
        return recipe
      }
      // We could just return data, but it seems dangerous to
      // mix the results of different api calls, so we
      // protect ourselves by copying the information.
      recipe.favorited = data.favorited
      recipe.favoritesCount = data.favoritesCount
      return recipe
    })
  },
}
