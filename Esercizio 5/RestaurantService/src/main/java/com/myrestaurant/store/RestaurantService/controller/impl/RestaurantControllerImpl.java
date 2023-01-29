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
    @ResponseStatus(HttpStatus.FOUND)
    public RestaurantDTO findRestaurantById(@PathVariable("id") Long id) {
        Restaurant restaurant = restaurantService.findById(id).orElse(null);
        return restaurantMapper.asDTO(restaurant);
    }

    @Override
    @DeleteMapping(path = {"/{id}/delete", "/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteRestaurantById(@PathVariable("id") Long id) {
        restaurantService.deleteById(id);
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<RestaurantDTO> findAllRestaurant() {
        // Find all restaurant from service and map as DTO.
        return restaurantMapper.asDTOList(restaurantService.findAll());
    }

    @Override
    @PutMapping(path = {"/{id}/update", "/{id}"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public RestaurantDTO updateRestaurant(@RequestBody RestaurantDTO restaurantDTO, @PathVariable("id") Long id) {
        Restaurant restaurant = restaurantMapper.asEntity(restaurantDTO);
        // Update restaurant from service and map it as DTO.
        return restaurantMapper.asDTO(restaurantService.update(restaurant, id));
    }

    @Override
    @GetMapping(path = {"/{id}/pizzas", "/pizzas/{id}"})
    @ResponseStatus(HttpStatus.FOUND)
    @CircuitBreaker(name = "getPizzasByRestaurantId", fallbackMethod = "getPizzasToRestaurantFallback")
    public List<Object> getPizzasByRestaurantId(@PathVariable("id") Long id) {
        List<Object> result = restaurantService.getPizzasByRestaurantId(id);
        return result;
    }

    @Override
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public List<Object> getPizzasToRestaurantFallback(Exception e) {
        return List.of("Error on getPizzasToRestaurantFallback, please try again later.");
    }

    @Override
    @PostMapping("/addPizzas")
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "addPizzasToRestaurantSync", fallbackMethod = "addPizzasToRestaurantSyncFallback")
    @Retry(name = "retryAddPizzasToRestaurantSync")
    public List<Object> addPizzaToRestaurantSync(@RequestBody List<RestaurantIdsDTO> restaurantIdsDTOS) {
        // Sync call
        restaurantService.addPizzasToRestaurantSync(restaurantIdsDTOS);
        return List.of("Pizzas added to restaurant.");
    }

    @Override
    @PostMapping("/addPizzas/async")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Object> addPizzaToRestaurantAsync(@RequestBody List<RestaurantIdsDTO> restaurantIdsDTOS) {
        // Async call
        restaurantService.addPizzasToRestaurantAsync(restaurantIdsDTOS);
        return List.of("Pizzas in queue to be added to the restaurant.");
    }

    @Override
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public List<Object> addPizzasToRestaurantSyncFallback(Exception e) {
        return List.of("Error on addPizzasToRestaurantFallback, please try again later or use the async call.");
    }

}