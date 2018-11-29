package com.shiksha.recipesAPI.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiksha.recipeAPI.dao.CRUDDAO;
import com.shiksha.recipesAPI.model.CookingStyle;
import com.shiksha.recipesAPI.repository.CookingStyleRepository;

@Service
public class DefaultCookingStyleService implements CRUDDAO<CookingStyle> {

	@Autowired
	CookingStyleRepository cookingStyleRepository;

	@Override
	public CookingStyle save(CookingStyle entity) {
		return cookingStyleRepository.save(entity);
	}

	@Override
	public Optional<CookingStyle> getById(Serializable id) {
		return cookingStyleRepository.findById((Long) id);
	}

	@Override
	public List<CookingStyle> getAll() {
		return cookingStyleRepository.findAll();
	}

	@Override
	public void delete(Serializable id) {
		cookingStyleRepository.deleteById((Long)id);

	}

}
