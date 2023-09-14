package org.example.domain;

import lombok.Getter;
import lombok.Setter;
import org.example.domain.common.Meal;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Dessert extends Meal {
}
