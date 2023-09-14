package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Dessert;
import org.example.domain.Dish;
import org.example.domain.Drink;
import org.example.domain.Order;
import org.example.dto.OrderDTO;
import org.example.repository.OrderRepository;
import org.example.repository.RepositoryHelper;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class OrderService extends RepositoryHelper {

	private final OrderRepository repository;

	public Order createOrder(OrderDTO orderDTO) {
		if (!checkOrder(orderDTO)) return null;
		Order order = new Order();
		order.setDish(getByIdRequired(Dish.class, orderDTO.getDishId()));
		order.setDessert(getByIdRequired(Dessert.class, orderDTO.getDessertId()));
		order.setDrink(getByIdRequired(Drink.class, orderDTO.getDrinkId()));

		order = repository.save(order);

		return order;
	}

	public Order getOrder(UUID id) {
		return repository.findById(id).get();
	}

	public List<Order> getAllOrders() {
		return repository.findAllBy();
	}

	public void deleteOrder(UUID id) {
		repository.deleteById(id);
	}

	private boolean checkOrder(OrderDTO orderDTO) {
		if ((orderDTO.getDessertId() == null || orderDTO.getDishId() == null) && orderDTO.getDrinkId() == null) {
			return false;
		}
		if (orderDTO.getDessertId() == null || orderDTO.getDishId() == null) {
			return false;
		}
		return true;
	}
}
