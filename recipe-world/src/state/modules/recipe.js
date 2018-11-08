import {
  SET_RECIPE,
  SET_COMMENTS,
  RESET_STATE,
  TAG_ADD,
  TAG_REMOVE,
  UPDATE_RECIPE_IN_LIST,
} from '../mutations'
import Vue from 'vue'
import {
  FETCH_RECIPE,
  FETCH_COMMENTS,
  COMMENT_CREATE,
  COMMENT_DESTROY,
  FAVORITE_ADD,
  FAVORITE_REMOVE,
  RECIPE_RESET_STATE,
  RECIPE_EDIT_REMOVE_TAG,
  RECIPE_EDIT_ADD_TAG,
  RECIPE_EDIT,
  RECIPE_PUBLISH,
  RECIPE_DELETE,
} from '../actions'
import { RecipeService } from '@services/recipe.service'
import { CommentService } from '@services/comment.service'
import { FavoriteService } from '@services/favorite.service'

const recipeService = new RecipeService()
const commentService = new CommentService()
const favoriteService = new FavoriteService()

const initialState = {
  recipe: {
    title: '',
    description: '',
    author: {},
    cookingTime: 0,
    cookingDifficulty: '',
    ingredients: [],
    cuisine: '',
    mealType: '',
    steps: '',
    tags: [],
  },
  comments: [],
}
export const state = { ...initialState }

export const actions = {
  [FETCH_RECIPE]({ commit }, recipeId, prevRecipe) {
    if (prevRecipe !== undefined) {
      return commit(SET_RECIPE, prevRecipe)
    }
    return recipeService.get(recipeId).then(({ data }) => {
      commit(SET_RECIPE, data.recipe)
      return data
    })
  },
  [FETCH_COMMENTS]({ commit }, recipeId) {
    return commentService.get(recipeId).then(({ data }) => {
      commit(SET_COMMENTS, data.comments)
    })
  },
  [COMMENT_CREATE]({ dispatch }, payload) {
    return commentService.post(payload.recipeId, payload.comment).then(() => {
      dispatch(FETCH_COMMENTS, payload.recipeId)
    })
  },
  [COMMENT_DESTROY]({ dispatch }, payload) {
    return commentService
      .delete(payload.recipeId, payload.commentId)
      .then(() => {
        dispatch(FETCH_COMMENTS, payload.recipeId)
      })
  },
  [FAVORITE_ADD]({ commit }, recipeId) {
    return favoriteService.add(recipeId).then(({ data }) => {
      commit(UPDATE_RECIPE_IN_LIST, data.recipe, { root: true })
      commit(SET_RECIPE, data.article)
    })
  },
  [FAVORITE_REMOVE]({ commit }, recipeId) {
    return favoriteService.remove(recipeId).then(({ data }) => {
      commit(UPDATE_RECIPE_IN_LIST, data.recipe, { root: true })
      commit(SET_RECIPE, data.article)
    })
  },
  [RECIPE_PUBLISH]({ state }) {
    return this.recipeService.create(state.recipe)
  },
  [RECIPE_DELETE](context, recipeId) {
    return this.recipeService.destroy(recipeId)
  },
  [RECIPE_EDIT]({ state }) {
    return this.recipeService.update(state.recipe.recipeId, state.article)
  },

  // Tags not implemented
  [RECIPE_EDIT_ADD_TAG](context, tag) {
    context.commit(TAG_ADD, tag)
  },
  [RECIPE_EDIT_REMOVE_TAG](context, tag) {
    context.commit(TAG_REMOVE, tag)
  },
  [RECIPE_RESET_STATE]({ commit }) {
    commit(RESET_STATE)
  },
}

export const mutations = {
  [SET_RECIPE](state, recipe) {
    state.recipe = recipe
  },
  [SET_COMMENTS](state, comments) {
    state.comments = comments
  },
  [TAG_ADD](state, tag) {
    state.recipes.tags = state.recipes.tags.concat([tag])
  },
  [TAG_REMOVE](state, tag) {
    state.recipes.tags = state.recipes.tagsfilter(t => t !== tag)
  },
  [RESET_STATE]() {
    for (let f in state) {
      Vue.set(state, f, initialState[f])
    }
  },
}
