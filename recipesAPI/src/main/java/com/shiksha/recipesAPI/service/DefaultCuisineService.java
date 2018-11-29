package com.shiksha.recipesAPI.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiksha.recipeAPI.dao.CRUDDAO;
import com.shiksha.recipesAPI.model.Cuisine;
import com.shiksha.recipesAPI.repository.CuisineRepository;
@Service
public class DefaultCuisineService implements CRUDDAO<Cuisine> {

	@Autowired
	CuisineRepository cuisineRepository;

	@Override
	public Cuisine save(Cuisine entity) {
		return cuisineRepository.save(entity);
	}

	@Override
	public Optional<Cuisine> getById(Serializable id) {
		return cuisineRepository.findById((Long) id);
	}

	@Override
	public List<Cuisine> getAll() {
		return cuisineRepository.findAll();
	}

	@Override
	public void delete(Serializable id) {
		cuisineRepository.deleteById((Long)id);

	}

}
