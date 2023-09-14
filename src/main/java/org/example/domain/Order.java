package org.example.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue
	@Column(columnDefinition = "uuid")
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "dish_id")
	private Dish dish;

	@ManyToOne
	@JoinColumn(name = "dessert_id")
	private Dessert dessert;

	@ManyToOne
	@JoinColumn(name = "drink_id")
	private Drink drink;

	public void setDish(Dish dish) {
		if (dish == null) {
			return;
		}
		this.dish = dish;
	}

	public void setDessert(Dessert dessert) {
		if (dessert == null) {
			return;
		}
		this.dessert = dessert;
	}

	public void setDrink(Drink drink) {
		if (drink == null) {
			return;
		}
		this.drink = drink;
	}
}
