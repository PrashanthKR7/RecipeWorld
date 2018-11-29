package com.shiksha.recipesAPI.repository;

import org.springframework.stereotype.Repository;

import com.shiksha.recipesAPI.model.KeyIngredient;

@Repository
public interface IngredientRepository extends BaseRepository<KeyIngredient, Long> {

}
