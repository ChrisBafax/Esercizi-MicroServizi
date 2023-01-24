package com.myrestaurant.store.pizzarestaurantservice.mapper;

import com.myrestaurant.store.pizzarestaurantservice.dto.ToppingDTO;
import com.myrestaurant.store.pizzarestaurantservice.model.Topping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ToppingMapper extends GenericMapper<Topping, ToppingDTO> {

}