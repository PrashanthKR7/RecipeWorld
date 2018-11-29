package com.shiksha.recipeAPI.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractDAO<T extends Serializable> {

	private Class<T> clasz;

	@PersistenceContext
	EntityManager entityManager;

	public void setClasz(Class<T> clasz) {
		this.clasz = clasz;
	}

	public T findOne(Long id) {
		return entityManager.find(clasz, id);
	}

	public List<T> findAll() {
		return entityManager.createQuery("from " + clasz.getName()).getResultList();
	}

	public void save(T entity) {
		entityManager.persist(entity);
	}

	public void update(T entity) {
		entityManager.merge(entity);
	}

	public void delete(T entity) {
		entityManager.remove(entity);
	}

	public void deleteById(Long entityId) {
		T entity = findOne(entityId);
		delete(entity);
	}
}