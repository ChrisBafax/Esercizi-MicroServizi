package com.myrestaurant.store.pizzarestaurantservice.controller;

import com.myrestaurant.store.pizzarestaurantservice.dto.DriverDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "Driver API")
public interface DriverController {

    @ApiOperation("Create new driver")
    DriverDTO createDriver(@RequestBody DriverDTO driverDTO);

    @ApiOperation("Find driver by ID")
    DriverDTO findDriverById(@PathVariable("id") Long id);

    @ApiOperation("Delete driver by ID")
    void deleteDriverById(@PathVariable("id") Long id);

    @ApiOperation("Find all drivers")
    List<DriverDTO> findAllDriver();

    @ApiOperation("Update a driver")
    DriverDTO updateDriver(@RequestBody DriverDTO driverDTO, @PathVariable("id") Long id);
}
