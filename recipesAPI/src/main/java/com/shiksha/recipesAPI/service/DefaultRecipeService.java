package com.shiksha.recipesAPI.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiksha.recipesAPI.model.Recipe;
import com.shiksha.recipesAPI.repository.RecipeRepository;

@Service
public class DefaultRecipeService implements RecipeService, RecipeMetaService {
	@Autowired
	private RecipeRepository recipeRepository;
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Recipe> getAll() {
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

	@Override
	public List<?> getRecipeMetafieldsData() {
		StoredProcedureQuery getRecipeMetaProcedure = entityManager.createNamedStoredProcedureQuery("getRecipeMeta");
		getRecipeMetaProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.IN).setParameter(1,
				"cuisine,mealtype,diettype,cookingstyle,difficulty,");
		return getRecipeMetaProcedure.getResultList();

	}
}
