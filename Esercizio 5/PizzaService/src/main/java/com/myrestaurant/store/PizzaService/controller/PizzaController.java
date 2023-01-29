package com.myrestaurant.store.PizzaService.controller;

import com.myrestaurant.store.PizzaService.dto.PizzaDTO;
import com.myrestaurant.store.PizzaService.dto.RestaurantIdsDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "Pizza API")
public interface PizzaController {

    @ApiOperation("Create new pizza")
    PizzaDTO createPizza(@RequestBody PizzaDTO pizzaDTO);

    @ApiOperation("Find pizza by ID")
    ResponseEntity<?> findPizzaById(@PathVariable("id") Long id);

    @ApiOperation("Delete pizza by ID")
    ResponseEntity<?> deletePizzaById(@PathVariable("id") Long id);

    @ApiOperation("Find all pizzas")
    ResponseEntity<?> findAllPizzas();

    @ApiOperation("Update a pizza")
    ResponseEntity<?> updatePizza(@RequestBody PizzaDTO pizzaDTO, @PathVariable("id") Long id);

    @ApiOperation("Find by restaurant ID")
    List<PizzaDTO> findByRestaurantId(@PathVariable("id") Long id);

    @ApiOperation("Add pizzas to restaurant")
    List<PizzaDTO> addPizzasToRestaurant(@RequestBody List<RestaurantIdsDTO> restaurantIdsDTOS);
}
