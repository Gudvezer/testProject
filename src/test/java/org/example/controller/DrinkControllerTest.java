package org.example.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.example.domain.Drink;
import org.example.service.DrinkService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(DrinkController.class)
@WebMvcTest(controllers = DrinkController.class)
public class DrinkControllerTest {

	@MockBean
	private DrinkService drinkService;

	@Autowired
	protected MockMvc mvc;

	@Autowired
	protected ObjectMapper mapper;

	@Test
	public void testGetDrink() throws Exception {
		Drink read = new Drink();
		read.setId(UUID.randomUUID());
		read.setPrice(20.0);
		read.setName("name");
		read.setDescription("desc");
		read.setAddLemon(true);
		read.setIceCube(0);

		Mockito.when(drinkService.getDrink(read.getId())).thenReturn(read);

		String resultJson = mvc.perform(MockMvcRequestBuilders.get("/api/drink").param("id", read.getId().toString()))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		Drink actualDrink = mapper.readValue(resultJson, Drink.class);
		Assertions.assertThat(actualDrink).isEqualToComparingFieldByField(read);
	}

	@Test
	public void testGetAllDrinks() throws Exception {
		Drink read = new Drink();
		read.setId(UUID.randomUUID());
		read.setPrice(20.0);
		read.setName("name");
		read.setDescription("desc");
		read.setAddLemon(true);
		read.setIceCube(0);

		Mockito.when(drinkService.getAllDrinks()).thenReturn(List.of(read));

		String resultJson = mvc.perform(MockMvcRequestBuilders.get("/api/drink/all"))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		List<Drink> actualDrink = mapper.readValue(resultJson, new TypeReference<>() {
		});
		Assertions.assertThat(actualDrink.get(0)).isEqualToComparingFieldByField(read);
	}

	@Test
	public void testDeleteDrink() throws Exception {
		UUID id = UUID.randomUUID();

		mvc.perform(delete("/api/drink").param("id", id.toString())).andExpect(status().isOk());

		Mockito.verify(drinkService).deleteDrink(id);
	}
}
