package com.myrestaurant.store.RestaurantService.controller.impl;

import com.myrestaurant.store.RestaurantService.dto.RestaurantIdsDTO;

import java.util.List;

public interface PizzaServiceProxyController {

    List<Object> getPizzasByRestaurantId(Long id);

    List<Object> addPizzaToRestaurant(List<RestaurantIdsDTO> restaurantIdsDTOS);

}

// Of no use at the moment.