package com.myrestaurant.store.RestaurantService.controller.impl;

import com.myrestaurant.store.RestaurantService.controller.RestaurantController;
import com.myrestaurant.store.RestaurantService.dto.RestaurantDTO;
import com.myrestaurant.store.RestaurantService.dto.RestaurantIdsDTO;
import com.myrestaurant.store.RestaurantService.mapper.RestaurantMapper;
import com.myrestaurant.store.RestaurantService.model.Restaurant;
import com.myrestaurant.store.RestaurantService.service.RestaurantService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantControllerImpl implements RestaurantController {

    private final RestaurantService restaurantService;

    private final RestaurantMapper restaurantMapper;

    @Override
    @PostMapping(path = {"/", "/create"})
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantDTO createRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        Restaurant restaurant = restaurantMapper.asEntity(restaurantDTO);
        // Save the pizza with the service and then map it as a DTO.
        return restaurantMapper.asDTO(restaurantService.save(restaurant));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> findRestaurantById(@PathVariable("id") Long id) {
        if (!restaurantService.checkId(id)) {
            return new ResponseEntity<>("The restaurant you are trying to find is not in this database.", HttpStatus.NOT_FOUND);
        } else {
            // Find restaurant by id from service and map it as DTO.
            RestaurantDTO dto = restaurantMapper.asDTO(restaurantService.findById(id).orElse(null));
            return new ResponseEntity<>(dto, HttpStatus.FOUND);
        }
    }

    @Override
    @DeleteMapping(path = {"/{id}/delete", "/{id}"})
    public ResponseEntity<?> deleteRestaurantById(@PathVariable("id") Long id) {
        if (!restaurantService.checkId(id)) {
            return new ResponseEntity<>("The restaurant you are trying to delete is not in this database.", HttpStatus.NOT_FOUND);
        } else {
            // Delete restaurant by id from service.
            restaurantService.deleteById(id);
            return new ResponseEntity<>("Restaurant successfully deleted.", HttpStatus.OK);
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<?> findAllRestaurant() {
        if (restaurantService.findAll().isEmpty()) {
            return new ResponseEntity<>("There are no restaurants in this database.", HttpStatus.NOT_FOUND);
        } else {
            // Find all restaurant from service and map as DTO.
            List<RestaurantDTO> dtoList = restaurantMapper.asDTOList(restaurantService.findAll());
            return new ResponseEntity<>(dtoList, HttpStatus.FOUND);
        }
    }

    @Override
    @PutMapping(path = {"/{id}/update", "/{id}"})
    public ResponseEntity<?> updateRestaurant(@RequestBody RestaurantDTO restaurantDTO, @PathVariable("id") Long id) {
        if (!restaurantService.checkId(id)) {
            return new ResponseEntity<>("The restaurant you are trying to update is not in this database.", HttpStatus.NOT_FOUND);
        } else {
            Restaurant restaurant = restaurantMapper.asEntity(restaurantDTO);
            // Update restaurant from service and map it as DTO.
            RestaurantDTO dto = restaurantMapper.asDTO(restaurantService.update(restaurant, id));
            return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
        }
    }

    @Override
    @GetMapping(path = {"/{id}/pizzas", "/pizzas/{id}"})
    @CircuitBreaker(name = "getPizzasByRestaurantId", fallbackMethod = "getPizzasToRestaurantFallback")
    @Retry(name = "getPizzasByRestaurantId")
    public ResponseEntity<List<Object>> getPizzasByRestaurantId(@PathVariable("id") Long id) {
        if (!restaurantService.checkId(id)) {
            return new ResponseEntity<>(List.of("The restaurant you are trying to find is not in this database."), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(restaurantService.getPizzasByRestaurantId(id), HttpStatus.FOUND);
        }
    }

    @Override
    public ResponseEntity<List<Object>> getPizzasToRestaurantFallback(Exception e) {
        List<Object> list = List.of("Error on getPizzasToRestaurantFallback.",
                "Please try again later.");
        return new ResponseEntity<>(list, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Override
    @PostMapping("/addPizzas")
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "addPizzasToRestaurantSync", fallbackMethod = "addPizzasToRestaurantSyncFallback")
    @Retry(name = "addPizzasToRestaurantSync")
    public List<Object> addPizzaToRestaurantSync(@RequestBody List<RestaurantIdsDTO> restaurantIdsDTOS) {
        // Sync call
        List<Object> result = restaurantService.addPizzasToRestaurantSync(restaurantIdsDTOS);
        return List.of("Pizzas successfully added to the restaurants.",
                "The pizzas added are: ",
                result);
    }

    @Override
    @PostMapping("/addPizzas/async")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Object> addPizzaToRestaurantAsync(@RequestBody List<RestaurantIdsDTO> restaurantIdsDTOS) {
        // Async call
        restaurantService.addPizzasToRestaurantAsync(restaurantIdsDTOS);
        return List.of("Pizzas in queue to be added to the restaurant.",
                "As soon as the pizza service will be available, the pizzas will be added.");
    }

    @Override
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public List<Object> addPizzasToRestaurantSyncFallback(Exception e) {
        return List.of("Error on addPizzasToRestaurantFallback.",
                "Please try again later or use the async call.",
                "The async call address is: /api/restaurants/addPizzas/async");
    }

}