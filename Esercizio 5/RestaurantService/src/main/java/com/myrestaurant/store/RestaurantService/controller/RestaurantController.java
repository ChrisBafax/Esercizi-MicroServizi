package com.myrestaurant.store.RestaurantService.controller;

import com.myrestaurant.store.RestaurantService.dto.RestaurantDTO;
import com.myrestaurant.store.RestaurantService.dto.RestaurantIdsDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "Restaurant API")
public interface RestaurantController {

    @ApiOperation("Create new restaurant")
    RestaurantDTO createRestaurant(@RequestBody RestaurantDTO restaurantDTO);

    @ApiOperation("Find restaurant by ID")
    ResponseEntity<?> findRestaurantById(@PathVariable("id") Long id);

    @ApiOperation("Delete restaurant by ID")
    ResponseEntity<?> deleteRestaurantById(@PathVariable("id") Long id);

    @ApiOperation("Find all restaurants")
    ResponseEntity<?> findAllRestaurant();

    @ApiOperation("Update the restaurant")
    ResponseEntity<?> updateRestaurant(@RequestBody RestaurantDTO restaurantDTO, @PathVariable("id") Long id);

    @ApiOperation("Find all pizzas by restaurant ID")
    ResponseEntity<List<Object>> getPizzasByRestaurantId(@PathVariable("id") Long id);

    @ApiOperation("Get pizzas from a restaurant fallback")
    ResponseEntity<List<Object>> getPizzasToRestaurantFallback(Exception e);

    @ApiOperation("Add pizzas to a restaurant")
    List<Object> addPizzaToRestaurantSync(@RequestBody List<RestaurantIdsDTO> restaurantIdsDTOS);

    @ApiOperation("Add pizzas to a restaurant fallback")
    List<Object> addPizzasToRestaurantSyncFallback(Exception e);

    @ApiOperation("Add pizzas to a restaurant Async call")
    List<Object> addPizzaToRestaurantAsync(@RequestBody List<RestaurantIdsDTO> restaurantIdsDTOS);
}
