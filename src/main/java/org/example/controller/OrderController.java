package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.Order;
import org.example.dto.OrderDTO;
import org.example.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

	private final OrderService orderService;

	@PostMapping
	public ResponseEntity<?> saveOrder(@RequestBody OrderDTO orderDTO) {
		Order order = orderService.createOrder(orderDTO);
		if (order == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong Order");
		}
		return ResponseEntity.ok(order);
	}

	@GetMapping
	public ResponseEntity<?> getOrder(@RequestParam UUID id) {
		try {
			return ResponseEntity.ok(orderService.getOrder(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllOrders() {
		try {
			return ResponseEntity.ok(orderService.getAllOrders());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}


	@DeleteMapping
	public ResponseEntity<?> deleteOrder(@RequestParam UUID id) {
		try {
			orderService.deleteOrder(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
