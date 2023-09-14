package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Drink;
import org.example.repository.DrinkRepository;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class DrinkService {

	private final DrinkRepository drinkRepository;

	public Drink createDrink(Drink createDrink) {
		Drink drink = new Drink();
		drink.setDescription(createDrink.getDescription());
		drink.setName(createDrink.getName());
		drink.setPrice(createDrink.getPrice());
		drink.setIceCube(createDrink.getIceCube());
		drink.setAddLemon(createDrink.getAddLemon());

		drink = drinkRepository.save(drink);
		return drink;
	}

	public Drink getDrink(UUID id) {
		return drinkRepository.findById(id).get();
	}

	public List<Drink> getAllDrinks() {
		return drinkRepository.findAllBy();
	}

	public void deleteDrink(UUID id) {
		drinkRepository.deleteById(id);
	}
}

