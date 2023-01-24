package com.myrestaurant.store.pizzarestaurantservice.mapper;

import com.myrestaurant.store.pizzarestaurantservice.dto.DriverDTO;
import com.myrestaurant.store.pizzarestaurantservice.model.Driver;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DriverMapper extends GenericMapper<Driver, DriverDTO> {

}