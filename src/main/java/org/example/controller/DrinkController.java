package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.Drink;
import org.example.service.DrinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/drink")
public class DrinkController {

	private final DrinkService drinkService;

	@PostMapping
	public ResponseEntity<?> saveDrink(@RequestBody Drink drink) {
		try {
			return ResponseEntity.ok(drinkService.createDrink(drink));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping
	public ResponseEntity<?> getDrink(@RequestParam UUID id) {
		try {
			return ResponseEntity.ok(drinkService.getDrink(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllDrinks() {
		try {
			return ResponseEntity.ok(drinkService.getAllDrinks());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}


	@DeleteMapping
	public ResponseEntity<?> deleteDrink(@RequestParam UUID id) {
		try {
			drinkService.deleteDrink(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
