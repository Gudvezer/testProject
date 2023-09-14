package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrderDTO {

	private UUID dessertId;

	private UUID dishId;

	private UUID drinkId;
}
