package com.myrestaurant.store.RestaurantService.service;

import com.myrestaurant.store.RestaurantService.dto.RestaurantIdsDTO;
import com.myrestaurant.store.RestaurantService.model.Restaurant;

import java.util.List;

public interface RestaurantService extends GenericService<Restaurant, Long> {

    void addPizzasToRestaurantAsync(List<RestaurantIdsDTO> restaurantIdsDTO);

    void addPizzasToRestaurantSync(List<RestaurantIdsDTO> restaurantIdsDTOS);

    List<Object> getPizzasByRestaurantId(Long id);

}