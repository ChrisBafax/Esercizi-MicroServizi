package com.myrestaurant.store.PizzaService.controller;

import com.myrestaurant.store.PizzaService.dto.ToppingDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "Topping API")
public interface ToppingController {

    @ApiOperation("Create new topping")
    ToppingDTO createTopping(@RequestBody ToppingDTO toppingDTO);

    @ApiOperation("Find topping by ID")
    ResponseEntity<?> findToppingById(@PathVariable("id") Long id);

    @ApiOperation("Delete topping by ID")
    ResponseEntity<?> deleteToppingById(@PathVariable("id") Long id);

    @ApiOperation("Find all toppings")
    List<ToppingDTO> findAllToppings();

    @ApiOperation("Update a topping")
    ResponseEntity<?> updateTopping(@RequestBody ToppingDTO toppingDTO, @PathVariable("id") Long id);

}
