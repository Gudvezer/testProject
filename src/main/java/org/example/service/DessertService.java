package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Dessert;
import org.example.repository.DessertRepository;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class DessertService {

	private final DessertRepository dessertRepository;

	public Dessert createDessert(Dessert createDessert) {
		Dessert dessert = new Dessert();
		dessert.setDescription(createDessert.getDescription());
		dessert.setName(createDessert.getName());
		dessert.setPrice(createDessert.getPrice());

		dessert = dessertRepository.save(dessert);
		return dessert;
	}

	public Dessert getDessert(UUID id) {
		return dessertRepository.findById(id).get();
	}

	public List<Dessert> getAllDesserts() {
		return dessertRepository.findAllBy();
	}

	public void deleteDessert(UUID id) {
		dessertRepository.deleteById(id);
	}
}
