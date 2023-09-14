package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.Dessert;
import org.example.service.DessertService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/dessert")
public class DessertController {

	private final DessertService dessertService;

	@PostMapping
	public ResponseEntity<?> saveDessert(@RequestBody Dessert drink) {
		try {
			return ResponseEntity.ok(dessertService.createDessert(drink));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping
	public ResponseEntity<?> getDessert(@RequestParam UUID id) {
		try {
			return ResponseEntity.ok(dessertService.getDessert(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/all")
	public ResponseEntity<?> getDesserts() {
		try {
			return ResponseEntity.ok(dessertService.getAllDesserts());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@DeleteMapping
	public ResponseEntity<?> deleteDessert(@RequestParam UUID id) {
		try {
			dessertService.deleteDessert(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
