package com.myrestaurant.store.RestaurantService.controller;

import com.myrestaurant.store.RestaurantService.dto.RestaurantDTO;
import com.myrestaurant.store.RestaurantService.dto.RestaurantIdsDTO;
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

    @ApiOperation("Find all pizzas by restaurant ID")
    List<Object> getPizzasByRestaurantId(@PathVariable("id") Long id);

    @ApiOperation("Add pizzas to a restaurant")
    List<Object> addPizzaToRestaurant(@RequestBody List<RestaurantIdsDTO> restaurantIdsDTOS);

    @ApiOperation("Add pizzas to a restaurant fallback")
    List<Object> addPizzasToRestaurantFallback(Exception e);

    @ApiOperation("Get pizzas from a restaurant fallback")
    List<Object> getPizzasToRestaurantFallback(Exception e);

    @ApiOperation("Add pizzas to a restaurant Async call")
    List<Object> addPizzaToRestaurantASync(@RequestBody List<RestaurantIdsDTO> restaurantIdsDTOS);
}
