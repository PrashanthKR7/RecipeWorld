package com.shiksha.recipesAPI.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CRUDService<E> {
	E save(E entity);
	
	Optional<E> getById(Serializable id);
	
	List<E> getAll();
	
	void delete(Serializable id);
	
}
