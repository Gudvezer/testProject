package org.example.service;

import org.assertj.core.api.Assertions;
import org.example.domain.Dessert;
import org.example.domain.Dish;
import org.example.domain.Drink;
import org.example.domain.Order;
import org.example.dto.OrderDTO;
import org.example.repository.DessertRepository;
import org.example.repository.DishRepository;
import org.example.repository.DrinkRepository;
import org.example.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Sql(statements = {
		"delete from orders"
}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class OrderServiceTest {

	@Autowired
	private OrderService orderService;

	@Autowired
	private DessertRepository dessertRepository;

	@Autowired
	private DrinkRepository drinkRepository;

	@Autowired
	private DishRepository dishRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Test
	public void testSaveOrder(){
		OrderDTO order = new OrderDTO();
		order.setDessertId(dessertRepository.save(new Dessert()).getId());
		order.setDishId(dishRepository.save(new Dish()).getId());
		order.setDrinkId(drinkRepository.save(new Drink()).getId());

		Order result = orderService.createOrder(order);
		Assertions.assertThat(result.getDrink().getId()).isEqualTo(order.getDrinkId());
		Assertions.assertThat(result.getDish().getId()).isEqualTo(order.getDishId());
		Assertions.assertThat(result.getDessert().getId()).isEqualTo(order.getDessertId());
	}

	@Test
	public void testGetOrder() {
		Order order = new Order();

		order = orderRepository.save(order);

		Order result = orderService.getOrder(order.getId());
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(order);
	}

	@Test
	public void testGetAllOrders() {
		Order order = new Order();
		order = orderRepository.save(order);

		List<Order> result = orderService.getAllOrders();
		org.junit.jupiter.api.Assertions.assertEquals(1, result.size());
		Assertions.assertThat(result.get(0)).usingRecursiveComparison().isEqualTo(order);
	}

	@Test
	public void testDeleteOrder() {
		Order order = new Order();

		order = orderRepository.save(order);

		orderService.deleteOrder(order.getId());

		Assert.assertFalse(orderRepository.existsById(order.getId()));
	}
}
