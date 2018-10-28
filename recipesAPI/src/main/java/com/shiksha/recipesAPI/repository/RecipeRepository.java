package com.shiksha.recipesAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shiksha.recipesAPI.model.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Long> {

}
