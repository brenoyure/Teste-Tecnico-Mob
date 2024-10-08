package br.com.mobsolutions.eventos.repositories;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Id;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Dependent
public abstract class JpaEntityManagerRepository<T, K> implements JpaRepository<T, K> {

	@PersistenceContext
	private EntityManager entityManager;

	private final Class<T> entityClazz;

	public JpaEntityManagerRepository(Class<T> entityClazz) {
		this.entityClazz = entityClazz;
	}

	@Override
	public T save(T t) {
		entityManager.persist(t);
		return t;
	}

	@Override
	public T update(T t) {
		return entityManager.merge(t);
	}

	@Override
	public List<T> findAll() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = cb.createQuery(entityClazz);
		query.from(entityClazz);

		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public Optional<T> findById(K id) {
		return Optional.ofNullable(entityManager.find(entityClazz, id));
	}

	@Override
	public boolean deleteById(K id) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaDelete<T> deleteQuery = builder.createCriteriaDelete(entityClazz);
		Root<T> entityRoot = deleteQuery.from(entityClazz);

		return entityManager.createQuery(deleteQuery.where(builder.equal(entityRoot.get(getIdFieldName()), id))).executeUpdate() > 0;

	}

	@Override
	public T getReferenceById(K id) {
		return entityManager.getReference(entityClazz, id);
	}

	@Override
	public boolean existsById(K id) {
		try {
			String existsByIdQuery = String.format("SELECT EXISTS(SELECT t FROM %s t WHERE t.%s = ?1)", entityClazz.getSimpleName(), getIdFieldName());
			return entityManager.createQuery(existsByIdQuery, Boolean.class).setParameter(1, id).getSingleResult();
		} catch (NoResultException e) { return false; }

	}

	private String getIdFieldName() {
		for (Field field : entityClazz.getDeclaredFields()) {
			if (field.isAnnotationPresent(Id.class) || field.isAnnotationPresent(EmbeddedId.class)) {
				return field.getName();
			}

		}

		return null;

	}

}
