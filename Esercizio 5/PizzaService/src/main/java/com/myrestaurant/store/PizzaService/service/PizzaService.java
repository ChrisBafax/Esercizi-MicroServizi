package com.myrestaurant.store.PizzaService.service;

import com.myrestaurant.store.PizzaService.model.Pizza;
import com.myrestaurant.store.PizzaService.model.RestaurantIds;

import java.util.List;

public interface PizzaService extends GenericService<Pizza, Long> {

    List<Pizza> findByRestaurantId(Long restaurantId);

    List<Pizza> addPizzasToRestaurant(List<RestaurantIds> restaurantIds);

}
