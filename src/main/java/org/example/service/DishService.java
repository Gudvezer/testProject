package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Dish;
import org.example.repository.DishRepository;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class DishService {

	private final DishRepository dishRepository;

	public Dish createDish(Dish createDish) {
		Dish dish = new Dish();
		dish.setDescription(createDish.getDescription());
		dish.setName(createDish.getName());
		dish.setPrice(createDish.getPrice());
		dish.setCuisine(createDish.getCuisine());

		dish = dishRepository.save(dish);
		return dish;
	}

	public Dish getDish(UUID id) {
		return dishRepository.findById(id).get();
	}

	public List<Dish> getAllDishes() {
		return dishRepository.findAllBy();
	}

	public void deleteDish(UUID id) {
		dishRepository.deleteById(id);
	}
}
