package br.com.mobsolutions.eventos.repositories;

import java.util.List;
import java.util.Optional;

public interface Repository<T, K> {

	T save(T t);

	T update(T t);

	List<T> findAll();

	Optional<T> findById(K id);

	boolean existsById(K id);

	boolean deleteById(K id);
}
