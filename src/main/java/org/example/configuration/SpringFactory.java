package org.example.configuration;

import org.example.controller.DessertController;
import org.example.controller.DishController;
import org.example.controller.DrinkController;
import org.example.controller.OrderController;
import org.example.repository.DessertRepository;
import org.example.repository.DishRepository;
import org.example.repository.DrinkRepository;
import org.example.repository.OrderRepository;
import org.example.service.DessertService;
import org.example.service.DishService;
import org.example.service.DrinkService;
import org.example.service.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringFactory {

	@Bean
	public DessertService dessertService(DessertRepository dessertRepository) {
		return new DessertService(dessertRepository);
	}

	@Bean
	public DrinkService drinkService(DrinkRepository drinkRepository) {
		return new DrinkService(drinkRepository);
	}

	@Bean
	public DishService dishService(DishRepository dishRepository) {
		return new DishService(dishRepository);
	}

	@Bean
	public OrderService orderService(OrderRepository orderRepository) {
		return new OrderService(orderRepository);
	}

	@Bean
	public OrderController orderController(OrderService orderService) {
		return new OrderController(orderService);
	}

	@Bean
	public DishController dishController(DishService dishService) {
		return new DishController(dishService);
	}

	@Bean
	public DrinkController drinkController(DrinkService drinkService) {
		return new DrinkController(drinkService);
	}

	@Bean
	public DessertController dessertController(DessertService dessertService) {
		return new DessertController(dessertService);
	}
}
