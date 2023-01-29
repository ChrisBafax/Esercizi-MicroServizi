package com.myrestaurant.store.RestaurantService.controller.impl;

import com.myrestaurant.store.RestaurantService.controller.DriverController;
import com.myrestaurant.store.RestaurantService.dto.DriverDTO;
import com.myrestaurant.store.RestaurantService.mapper.DriverMapper;
import com.myrestaurant.store.RestaurantService.model.Driver;
import com.myrestaurant.store.RestaurantService.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverControllerImpl implements DriverController {

    private final DriverService driverService;

    private final DriverMapper driverMapper;

    @Override
    @PostMapping(path = {"/", "/create"})
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDTO createDriver(@RequestBody DriverDTO driverDTO) {
        Driver driver = driverMapper.asEntity(driverDTO);
        // Save the driver with the service and then map it as a DTO.
        return driverMapper.asDTO(driverService.save(driver));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> findDriverById(@PathVariable("id") Long id) {
        if (!driverService.checkId(id)) {
            return new ResponseEntity<>("The driver you are trying to find is not in this database.", HttpStatus.NOT_FOUND);
        } else {
            Driver driver = driverService.findById(id).orElse(null);
            DriverDTO dto = driverMapper.asDTO(driver);
            return new ResponseEntity<>(dto, HttpStatus.FOUND);
        }
    }

    @Override
    @DeleteMapping(path = {"/{id}/delete", "/{id}"})
    public ResponseEntity<?> deleteDriverById(@PathVariable("id") Long id) {
        if (!driverService.checkId(id)) {
            return new ResponseEntity<>("The driver you are trying to delete is not in this database.", HttpStatus.NOT_FOUND);
        } else {
            driverService.deleteById(id);
            return new ResponseEntity<>("Driver deleted successfully.", HttpStatus.OK);
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<?> findAllDriver() {
        if (driverService.findAll().isEmpty()) {
            return new ResponseEntity<>("There are no drivers in this database.", HttpStatus.NOT_FOUND);
        } else {
            // Find all driver from service and map as DTO.
            List<DriverDTO> dtoList = driverMapper.asDTOList(driverService.findAll());
            return new ResponseEntity<>(dtoList, HttpStatus.FOUND);
        }
    }

    @Override
    @PutMapping(path = {"/{id}/update", "/{id}"})
    public ResponseEntity<?> updateDriver(@RequestBody DriverDTO driverDTO, @PathVariable("id") Long id) {
        if (!driverService.checkId(id)) {
            return new ResponseEntity<>("The driver you are trying to update is not in this database.", HttpStatus.NOT_FOUND);
        } else {
            Driver driver = driverMapper.asEntity(driverDTO);
            // Update driver from service and map it as DTO.
            DriverDTO dto = driverMapper.asDTO(driverService.update(driver, id));
            return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
        }
    }
}