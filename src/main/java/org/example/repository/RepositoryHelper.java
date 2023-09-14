package org.example.repository;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Component
public class RepositoryHelper {
	@PersistenceContext
	private EntityManager entityManager;

	public <E> E getByIdRequired(Class<E> entityClass, UUID id) {
		if (id == null) {
			return null;
		}
		E res = entityManager.find(entityClass, id);
		return res;
	}
}
