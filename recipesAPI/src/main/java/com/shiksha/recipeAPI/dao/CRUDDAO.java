package com.shiksha.recipeAPI.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CRUDDAO<E extends Serializable> {
	E save(E entity);
	
	Optional<E> getById(Serializable id);
	
	List<E> getAll();
	
	void delete(Serializable id);
	
}
