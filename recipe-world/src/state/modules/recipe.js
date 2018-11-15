import Vue from 'vue'
import { getField, updateField } from 'vuex-map-fields'
import {
  SET_RECIPE,
  SET_COMMENTS,
  RESET_STATE,
  TAG_ADD,
  TAG_REMOVE,
  UPDATE_RECIPE_IN_LIST,
  SET_RECIPE_META,
} from '@state/mutations'
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
  FETCH_RECIPE_META,
} from '@state/actions'
import { RecipeService } from '@services/recipe.service'
import { CommentService } from '@services/comment.service'
import { FavoriteService } from '@services/favorite.service'
import { recipeStructure, recipeArrayFields } from '@utils/recipe-structure'
const recipeService = new RecipeService()
const commentService = new CommentService()
const favoriteService = new FavoriteService()
const initialState = {
  recipe: { ...recipeStructure, ...recipeArrayFields },
  comments: [],
  meta: {},
}
export const state = { ...initialState }

export const actions = {
  [FETCH_RECIPE]({ commit }, recipeId, prevRecipe) {
    if (prevRecipe !== undefined) {
      return commit(SET_RECIPE, prevRecipe)
    }
    return recipeService.get(recipeId).then(({ result }) => {
      commit(SET_RECIPE, result.recipe)
      return result
    })
  },
  [FETCH_RECIPE_META]({ commit }) {
    return recipeService.meta().then(({ result }) => {
      commit(SET_RECIPE_META, result)
    })
  },
  [FETCH_COMMENTS]({ commit }, recipeId) {
    return commentService.get(recipeId).then(({ result }) => {
      commit(SET_COMMENTS, result.comments)
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
    return favoriteService.add(recipeId).then(({ result }) => {
      commit(UPDATE_RECIPE_IN_LIST, result.recipe, { root: true })
      commit(SET_RECIPE, result.article)
    })
  },
  [FAVORITE_REMOVE]({ commit }, recipeId) {
    return favoriteService.remove(recipeId).then(({ result }) => {
      commit(UPDATE_RECIPE_IN_LIST, result.recipe, { root: true })
      commit(SET_RECIPE, result.article)
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
  updateField,
  [SET_RECIPE](state, recipe) {
    state.recipe = recipe
  },
  [SET_RECIPE_META](state, meta) {
    state.meta = JSON.parse(meta[0])
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

export const getters = { getField }
