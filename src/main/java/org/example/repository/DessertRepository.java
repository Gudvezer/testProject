package org.example.repository;

import org.example.domain.Dessert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DessertRepository extends CrudRepository<Dessert, UUID> {

	List<Dessert> findAllBy();

}
