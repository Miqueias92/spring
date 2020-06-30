package br.com.mapper.repository;

import java.util.Optional;

public interface CrudRepository <T, ID>{

	Iterable<T> findAll();

	boolean deleteById(ID id) throws IllegalArgumentException;

	Optional<T> findById(ID id);

	T save(T entity) throws IllegalArgumentException;

	boolean update(T entity);
}
