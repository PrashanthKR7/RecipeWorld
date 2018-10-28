package com.shiksha.recipesAPI.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiksha.recipesAPI.model.Recipe;
import com.shiksha.recipesAPI.repository.RecipeRepository;

@Service
public class DefaultRecipeService implements RecipeService {
	@Autowired
	private RecipeRepository recipeRepository;

	@Override
	public List<Recipe> getAll(){
		return recipeRepository.findAll();
	}


	@Override
	public Optional<Recipe> getById(Serializable recipeId) {
		return recipeRepository.findById((Long) recipeId);
	
	}

	@Override
	public void delete(Serializable id) {
		recipeRepository.deleteById((Long) id);
	}


	@Override
	public Recipe save(Recipe recipe) {
		return recipeRepository.save(recipe);
	}
}
