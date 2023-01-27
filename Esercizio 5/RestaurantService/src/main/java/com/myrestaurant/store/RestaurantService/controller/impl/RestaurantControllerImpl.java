package com.myrestaurant.store.RestaurantService.controller.impl;

import com.myrestaurant.store.RestaurantService.controller.RestaurantController;
import com.myrestaurant.store.RestaurantService.dto.RestaurantDTO;
import com.myrestaurant.store.RestaurantService.dto.RestaurantIdsDTO;
import com.myrestaurant.store.RestaurantService.mapper.RestaurantMapper;
import com.myrestaurant.store.RestaurantService.model.Restaurant;
import com.myrestaurant.store.RestaurantService.service.RestaurantService;
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

    // No usage at the moment.
    public final PizzaServiceProxyController pizzaServiceProxyClient;

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
    public List<Object> getPizzasByRestaurantId(@PathVariable("id") Long id) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = pizzaServiceUrl + "/" + id;
        List<Object> result = List.of(Objects.requireNonNull(
                restTemplate.getForObject(
                        uri,
                        Object[].class)));
        // Doesn't work
//        List<Object> result = pizzaServiceProxyClient.getPizzasByRestaurantId(id);
        return result;
    }

    @Override
    @PostMapping("/addPizzas")
    @ResponseStatus(HttpStatus.CREATED)
    // Doesn't work
//    @CircuitBreaker(name = "addPizzasToRestaurant", fallbackMethod = "addPizzasToRestaurantFallback")
//    @Retry(name = "retryAddPizzasToRestaurant")
    public List<Object> addPizzaToRestaurant(List<RestaurantIdsDTO> restaurantIdsDTOS) {
        // Sync call
        // RestTemplate restTemplate = new RestTemplate();
        // List<Object> result = List.of(Objects.requireNonNull(restTemplate.postForObject(pizzaServiceUrl, restaurantIdsDTOS,Object[].class)));
        // Async call
        restaurantService.addPizzasToRestaurant(restaurantIdsDTOS);
        // Doesn't work
        // List<Object> result = pizzaServiceProxyClient.addPizzasToRestaurant(restaurantIdsDTOS);
        return null;
    }

//    public List<Object> addPizzasToRestaurantFallback(Exception e) {
//        return null;
//    }

}