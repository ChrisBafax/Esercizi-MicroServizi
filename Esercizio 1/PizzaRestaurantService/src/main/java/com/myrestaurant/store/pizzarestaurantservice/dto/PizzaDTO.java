package com.myrestaurant.store.pizzarestaurantservice.dto;

import com.myrestaurant.store.pizzarestaurantservice.model.Restaurant;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class PizzaDTO {

    private long id;

    private String name;

    private boolean favourite;

    private Set<ToppingDTO> toppings;

    private Set<Restaurant> restaurants;

}
