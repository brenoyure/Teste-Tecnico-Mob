package br.com.mobsolutions.eventos.repositories;

public interface JpaRepository<T, K> extends Repository<T, K> {

	T getReferenceById(K id);

}
