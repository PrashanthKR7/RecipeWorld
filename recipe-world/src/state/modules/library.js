import { FETCH_RECIPES } from './../actions'
import { FETCH_START, FETCH_END } from '../mutations';
import { RecipeService } from '@services/recipe.service';

const recipeService = new RecipeService()
const state ={
    recipes: [],
    isLoading: true,
    recipesCount: 0
}

const getters = {
    recipesCount(state) {
        return state.recipesCount
    },
    recipes(state){
        return state.recipes
    },
    isLoading(state){
        return state.isLoading
    }
}

const actions = {
    [FETCH_RECIPES]({commit}, params) {
        commit(FETCH_START)
        return recipeService.query(params.type, params.filters)
            .then(({data}) => {
                commit(FETCH_END, data)
            })
    }
}

const mutations = {
    [FETCH_START](state) {
        state.isLoading = true
    },

    [FETCH_END](state, {recipes, count}) {
        state.isLoading = false
        state.recipes = recipes
        state.recipesCount = count
    }
}


export default {
    state,
    getters,
    mutations,
    actions
}