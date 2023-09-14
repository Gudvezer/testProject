package org.example.service;

import org.assertj.core.api.Assertions;
import org.example.domain.Dessert;
import org.example.repository.DessertRepository;
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
		"delete from dessert"
}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class DessertServiceTest {

	@Autowired
	private DessertService dessertService;

	@Autowired
	private DessertRepository dessertRepository;

	@Test
	public void testSaveDessert(){
		Dessert dessert = new Dessert();
		dessert.setPrice(20.0);
		dessert.setName("name");
		dessert.setDescription("desc");

		Dessert result = dessertService.createDessert(dessert);
		result.setId(null);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(dessert);
	}

	@Test
	public void testGetDessert() {
		Dessert dessert = new Dessert();
		dessert.setPrice(20.0);
		dessert.setName("name");
		dessert.setDescription("desc");
		dessert = dessertRepository.save(dessert);

		Dessert result = dessertService.getDessert(dessert.getId());
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(dessert);
	}

	@Test
	public void testGetAllDesserts() {
		Dessert dessert = new Dessert();
		dessert.setPrice(20.0);
		dessert.setName("name");
		dessert.setDescription("desc");
		dessert = dessertRepository.save(dessert);

		List<Dessert> result = dessertService.getAllDesserts();
		org.junit.jupiter.api.Assertions.assertEquals(1, result.size());
		Assertions.assertThat(result.get(0)).usingRecursiveComparison().isEqualTo(dessert);
	}

	@Test
	public void testDeleteDessert() {
		Dessert dessert = new Dessert();
		dessert.setPrice(20.0);
		dessert.setName("name");
		dessert.setDescription("desc");
		dessert = dessertRepository.save(dessert);

		dessertService.deleteDessert(dessert.getId());

		Assert.assertFalse(dessertRepository.existsById(dessert.getId()));
	}
}
