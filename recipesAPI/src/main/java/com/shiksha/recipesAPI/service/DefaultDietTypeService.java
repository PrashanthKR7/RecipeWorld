package com.shiksha.recipesAPI.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiksha.recipeAPI.dao.CRUDDAO;
import com.shiksha.recipesAPI.model.DietType;
import com.shiksha.recipesAPI.repository.DietTypeRepository;
@Service
public class DefaultDietTypeService implements CRUDDAO<DietType> {

	@Autowired
	DietTypeRepository dietTypeRepository;

	@Override
	public DietType save(DietType entity) {
		return dietTypeRepository.save(entity);
	}

	@Override
	public Optional<DietType> getById(Serializable id) {
		return dietTypeRepository.findById((Long) id);
	}

	@Override
	public List<DietType> getAll() {
		return dietTypeRepository.findAll();
	}

	@Override
	public void delete(Serializable id) {
		dietTypeRepository.deleteById((Long)id);

	}

}
