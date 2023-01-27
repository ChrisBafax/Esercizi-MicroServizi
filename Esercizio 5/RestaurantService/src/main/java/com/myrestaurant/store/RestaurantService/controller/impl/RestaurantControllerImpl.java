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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantControllerImpl implements RestaurantController {

    private final RestaurantService restaurantService;

    private final RestaurantMapper restaurantMapper;

    @Value("${app.pizza-service-url}")
    private String pizzaServiceUrl;

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
    @Retry(name = "retryGetPizzasByRestaurantId")
    public List<Object> getPizzasByRestaurantId(@PathVariable("id") Long id) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = pizzaServiceUrl + "/" + id;
        List<Object> result = List.of(Objects.requireNonNull(
                restTemplate.getForObject(
                        uri,
                        Object[].class)));
        return result;
    }

    @Override
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public List<Object> getPizzasToRestaurantFallback(Exception e) {
        return List.of("Error on getPizzasToRestaurantFallback.");
    }

    @Override
    @PostMapping("/addPizzas")
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "addPizzasToRestaurant", fallbackMethod = "addPizzasToRestaurantFallback")
    @Retry(name = "retryAddPizzasToRestaurant")
    public List<Object> addPizzaToRestaurant(@RequestBody List<RestaurantIdsDTO> restaurantIdsDTOS) {
        // Sync call
        RestTemplate restTemplate = new RestTemplate();
        List<Object> result = List.of(Objects.requireNonNull(restTemplate.postForObject(pizzaServiceUrl, restaurantIdsDTOS, Object[].class)));
        // Async call
        // restaurantService.addPizzasToRestaurant(restaurantIdsDTOS);
        return List.of("Pizzas added to restaurant.");
    }

    @Override
    @PostMapping("/addPizzas/async")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Object> addPizzaToRestaurantASync(@RequestBody List<RestaurantIdsDTO> restaurantIdsDTOS) {
        restaurantService.addPizzasToRestaurant(restaurantIdsDTOS);
        return List.of("Pizzas in queue to be added to the restaurant.");
    }

    @Override
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public List<Object> addPizzasToRestaurantFallback(Exception e) {
        return List.of("Error on addPizzasToRestaurantFallback.");
    }

}