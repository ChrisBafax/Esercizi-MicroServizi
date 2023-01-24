package com.myrestaurant.store.pizzarestaurantservice.controller;

import com.myrestaurant.store.pizzarestaurantservice.dto.PizzaDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "Pizza API")
public interface PizzaController {

    @ApiOperation("Create new pizza")
    PizzaDTO createPizza(@RequestBody PizzaDTO pizzaDTO);

    @ApiOperation("Find pizza by ID")
    PizzaDTO findPizzaById(@PathVariable("id") Long id);

    @ApiOperation("Delete pizza by ID")
    void deletePizzaById(@PathVariable("id") Long id);

    @ApiOperation("Find all pizzas")
    List<PizzaDTO> findAllPizzas();

    @ApiOperation("Update a pizza")
    PizzaDTO updatePizza(@RequestBody PizzaDTO pizzaDTO, @PathVariable("id") Long id);

    @ApiOperation("Find pizza by restaurant ID")
    List<PizzaDTO> findPizzasByRestaurantID(@PathVariable("id") Long id);

}