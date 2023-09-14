package org.example.repository;

import org.example.domain.Drink;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DrinkRepository extends CrudRepository<Drink, UUID> {

	List<Drink> findAllBy();
}
