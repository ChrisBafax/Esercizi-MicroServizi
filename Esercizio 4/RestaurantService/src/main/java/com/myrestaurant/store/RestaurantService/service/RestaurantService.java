package com.myrestaurant.store.RestaurantService.service;

import com.myrestaurant.store.RestaurantService.dto.RestaurantIdsDTO;
import com.myrestaurant.store.RestaurantService.model.Restaurant;

import java.util.List;

public interface RestaurantService extends GenericService<Restaurant, Long> {

    void addPizzasToRestaurant(List<RestaurantIdsDTO> restaurantIdsDTO);

}