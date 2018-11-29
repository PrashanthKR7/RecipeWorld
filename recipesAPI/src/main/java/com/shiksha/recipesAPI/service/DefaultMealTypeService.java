package com.shiksha.recipesAPI.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiksha.recipeAPI.dao.CRUDDAO;
import com.shiksha.recipesAPI.model.MealType;
import com.shiksha.recipesAPI.repository.MealTypeRepository;
@Service
public class DefaultMealTypeService implements CRUDDAO<MealType> {

	@Autowired
	MealTypeRepository mealTypeRepository;

	@Override
	public MealType save(MealType entity) {
		return mealTypeRepository.save(entity);
	}

	@Override
	public Optional<MealType> getById(Serializable id) {
		return mealTypeRepository.findById((Long) id);
	}

	@Override
	public List<MealType> getAll() {
		return mealTypeRepository.findAll();
	}

	@Override
	public void delete(Serializable id) {
		mealTypeRepository.deleteById((Long)id);

	}

}
