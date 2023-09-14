package org.example.domain;

import lombok.Getter;
import lombok.Setter;
import org.example.domain.common.Meal;
import org.example.domain.enums.Cuisine;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Entity
public class Dish extends Meal {

	@Enumerated(EnumType.STRING)
	private Cuisine cuisine;
}
