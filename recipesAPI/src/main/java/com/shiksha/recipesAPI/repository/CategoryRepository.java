package com.shiksha.recipesAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shiksha.recipesAPI.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}