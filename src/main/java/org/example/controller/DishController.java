package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.Dish;
import org.example.service.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/dish")
public class DishController {

	private final DishService dishService;

	@PostMapping
	public ResponseEntity<?> saveDish(@RequestBody Dish dish) {
		try {
			return ResponseEntity.ok(dishService.createDish(dish));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping
	public ResponseEntity<?> getDish(@RequestParam UUID id) {
		try {
			return ResponseEntity.ok(dishService.getDish(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/all")
	public ResponseEntity<?> getDish() {
		try {
			return ResponseEntity.ok(dishService.getAllDishes());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@DeleteMapping
	public ResponseEntity<?> deleteDish(@RequestParam UUID id) {
		try {
			dishService.deleteDish(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
