package com.myrestaurant.store.pizzarestaurantservice.service;

import com.myrestaurant.store.pizzarestaurantservice.model.Pizza;

import java.util.List;

public interface PizzaService extends GenericService<Pizza, Long> {

    List<Pizza> findByRestaurantId(Long restaurantId);
}