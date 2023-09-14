package org.example.service;

import org.assertj.core.api.Assertions;
import org.example.domain.Drink;
import org.example.repository.DrinkRepository;
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
		"delete from drink"
}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class DrinkServiceTest {

	@Autowired
	private DrinkService drinkService;

	@Autowired
	private DrinkRepository drinkRepository;

	@Test
	public void testSaveDrink(){
		Drink drink = new Drink();
		drink.setPrice(20.0);
		drink.setName("name");
		drink.setDescription("desc");
		drink.setAddLemon(true);
		drink.setIceCube(0);

		Drink result = drinkService.createDrink(drink);
		result.setId(null);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(drink);
	}

	@Test
	public void testGetDrink() {
		Drink drink = new Drink();
		drink.setPrice(20.0);
		drink.setName("name");
		drink.setDescription("desc");
		drink.setAddLemon(true);
		drink.setIceCube(0);
		drink = drinkRepository.save(drink);

		Drink result = drinkService.getDrink(drink.getId());
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(drink);
	}

	@Test
	public void testGetAllDrinks() {
		Drink drink = new Drink();
		drink.setPrice(20.0);
		drink.setName("name");
		drink.setDescription("desc");
		drink.setAddLemon(true);
		drink.setIceCube(0);
		drink = drinkRepository.save(drink);

		List<Drink> result = drinkService.getAllDrinks();
		org.junit.jupiter.api.Assertions.assertEquals(1, result.size());
		Assertions.assertThat(result.get(0)).usingRecursiveComparison().isEqualTo(drink);
	}

	@Test
	public void testDeleteDrink() {
		Drink drink = new Drink();
		drink.setPrice(20.0);
		drink.setName("name");
		drink.setDescription("desc");
		drink.setAddLemon(true);
		drink.setIceCube(0);
		drink = drinkRepository.save(drink);

		drinkService.deleteDrink(drink.getId());

		Assert.assertFalse(drinkRepository.existsById(drink.getId()));
	}
}
