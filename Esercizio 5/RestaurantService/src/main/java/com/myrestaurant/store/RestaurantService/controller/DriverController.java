package com.myrestaurant.store.RestaurantService.controller;

import com.myrestaurant.store.RestaurantService.dto.DriverDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Api(tags = "Driver API")
public interface DriverController {

    @ApiOperation("Create new driver")
    DriverDTO createDriver(@RequestBody DriverDTO driverDTO);

    @ApiOperation("Find driver by ID")
    ResponseEntity<?> findDriverById(@PathVariable("id") Long id);

    @ApiOperation("Delete driver by ID")
    ResponseEntity<?> deleteDriverById(@PathVariable("id") Long id);

    @ApiOperation("Find all drivers")
    ResponseEntity<?> findAllDriver();

    @ApiOperation("Update a driver")
    ResponseEntity<?> updateDriver(@RequestBody DriverDTO driverDTO, @PathVariable("id") Long id);
}
