package com.myrestaurant.store.pizzarestaurantservice.mapper;

import com.myrestaurant.store.pizzarestaurantservice.dto.RestaurantDTO;
import com.myrestaurant.store.pizzarestaurantservice.model.Restaurant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantMapper extends GenericMapper<Restaurant, RestaurantDTO> {

}