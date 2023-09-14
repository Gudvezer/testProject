package org.example.domain.common;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class Meal{

	@Id
	@GeneratedValue
	@Column(columnDefinition = "uuid")
	private UUID id;

	@NotNull
	private String name;

	@NotNull
	private Double price;

	private String description;

}
