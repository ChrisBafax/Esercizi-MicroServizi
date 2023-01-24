package com.myrestaurant.store.pizzarestaurantservice.controller;

import com.myrestaurant.store.pizzarestaurantservice.dto.RestaurantDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "Restaurant API")
public interface RestaurantController {

    @ApiOperation("Create new restaurant")
    RestaurantDTO createRestaurant(@RequestBody RestaurantDTO restaurantDTO);

    @ApiOperation("Find restaurant by ID")
    RestaurantDTO findRestaurantById(@PathVariable("id") Long id);

    @ApiOperation("Delete restaurant by ID")
    void deleteRestaurantById(@PathVariable("id") Long id);

    @ApiOperation("Find all restaurants")
    List<RestaurantDTO> findAllRestaurant();

    @ApiOperation("Update the restaurant")
    RestaurantDTO updateRestaurant(@RequestBody RestaurantDTO restaurantDTO, @PathVariable("id") Long id);

    @ApiOperation("Add a pizza to the restaurant")
    RestaurantDTO addPizzaToRestaurant(@RequestBody RestaurantDTO restaurantDTO);

}
