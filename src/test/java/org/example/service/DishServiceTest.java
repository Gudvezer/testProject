package org.example.service;

import org.assertj.core.api.Assertions;
import org.example.domain.Dish;
import org.example.domain.enums.Cuisine;
import org.example.repository.DishRepository;
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
		"delete from dish"
}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class DishServiceTest {

	@Autowired
	private DishService dishService;

	@Autowired
	private DishRepository dishRepository;

	@Test
	public void testSaveDish(){
		Dish dish = new Dish();
		dish.setPrice(20.0);
		dish.setName("name");
		dish.setDescription("desc");
		dish.setCuisine(Cuisine.POLISH);

		Dish result = dishService.createDish(dish);
		result.setId(null);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(dish);
	}

	@Test
	public void testGetDish() {
		Dish dish = new Dish();
		dish.setPrice(20.0);
		dish.setName("name");
		dish.setDescription("desc");
		dish.setCuisine(Cuisine.POLISH);
		dish = dishRepository.save(dish);

		Dish result = dishService.getDish(dish.getId());
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(dish);
	}

	@Test
	public void testGetAllDishs() {
		Dish dish = new Dish();
		dish.setPrice(20.0);
		dish.setName("name");
		dish.setDescription("desc");
		dish.setCuisine(Cuisine.POLISH);
		dish = dishRepository.save(dish);

		List<Dish> result = dishService.getAllDishes();
		org.junit.jupiter.api.Assertions.assertEquals(1, result.size());
		Assertions.assertThat(result.get(0)).usingRecursiveComparison().isEqualTo(dish);
	}

	@Test
	public void testDeleteDish() {
		Dish dish = new Dish();
		dish.setPrice(20.0);
		dish.setName("name");
		dish.setDescription("desc");
		dish.setCuisine(Cuisine.POLISH);
		dish = dishRepository.save(dish);

		dishService.deleteDish(dish.getId());

		Assert.assertFalse(dishRepository.existsById(dish.getId()));
	}
}
