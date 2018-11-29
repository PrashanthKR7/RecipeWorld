package com.shiksha.recipesAPI.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiksha.recipeAPI.dao.CRUDDAO;
import com.shiksha.recipesAPI.model.KeyIngredient;
import com.shiksha.recipesAPI.repository.IngredientRepository;

@Service
public class DefaultIngredientService implements CRUDDAO<KeyIngredient> {

	@Autowired
	IngredientRepository ingredientRepository;

	@Override
	public KeyIngredient save(KeyIngredient entity) {
		return ingredientRepository.save(entity);
	}
	
	public List<KeyIngredient> saveAll(List<KeyIngredient> entities) {
		return ingredientRepository.saveAll(entities);
	}
	

	@Override
	public Optional<KeyIngredient> getById(Serializable id) {
		return ingredientRepository.findById((Long) id);
	}

	@Override
	public List<KeyIngredient> getAll() {
		return ingredientRepository.findAll();
	}

	@Override
	public void delete(Serializable id) {
		ingredientRepository.deleteById((Long)id);

	}

}