package com.myrestaurant.store.pizzarestaurantservice.dto;

import com.myrestaurant.store.pizzarestaurantservice.model.Restaurant;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class DriverDTO {

    private Long id;

    private String name;

    private Set<Restaurant> restaurants;
}
