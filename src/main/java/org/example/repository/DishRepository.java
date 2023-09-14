package org.example.repository;

import org.example.domain.Dish;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DishRepository extends CrudRepository<Dish, UUID> {
	List<Dish> findAllBy();
}
