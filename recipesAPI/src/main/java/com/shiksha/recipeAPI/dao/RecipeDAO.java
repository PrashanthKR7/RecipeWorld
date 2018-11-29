package com.shiksha.recipeAPI.dao;

import java.util.List;

import com.shiksha.recipesAPI.model.Recipe;

public interface RecipeDAO extends CRUDDAO<Recipe> {
	List<String> getRecipeMetafieldsData();
	
	Recipe createRecipe(Recipe recipe);
}
