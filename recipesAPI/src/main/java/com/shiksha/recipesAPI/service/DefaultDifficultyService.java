package com.shiksha.recipesAPI.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiksha.recipeAPI.dao.CRUDDAO;
import com.shiksha.recipesAPI.model.Difficulty;
import com.shiksha.recipesAPI.repository.DifficultyRepository;
@Service
public class DefaultDifficultyService implements CRUDDAO<Difficulty> {

	@Autowired
	DifficultyRepository difficultyRepository;

	@Override
	public Difficulty save(Difficulty entity) {
		return difficultyRepository.save(entity);
	}

	@Override
	public Optional<Difficulty> getById(Serializable id) {
		return difficultyRepository.findById((Long) id);
	}

	@Override
	public List<Difficulty> getAll() {
		return difficultyRepository.findAll();
	}

	@Override
	public void delete(Serializable id) {
		difficultyRepository.deleteById((Long)id);

	}

}
