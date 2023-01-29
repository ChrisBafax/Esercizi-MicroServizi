package com.myrestaurant.store.RestaurantService.service.impl;

import com.myrestaurant.store.RestaurantService.dao.RestaurantRepository;
import com.myrestaurant.store.RestaurantService.dto.RestaurantIdsDTO;
import com.myrestaurant.store.RestaurantService.model.Restaurant;
import com.myrestaurant.store.RestaurantService.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository repository;

    private final RabbitTemplate rabbitTemplate;

    @Value("${app.rabbitmq.add-pizzas-routingkey}")
    private String addPizzasToRestaurantRoutingKey;

    @Value("${app.pizza-service-url}")
    private String pizzaServiceUrl;

    @Override
    public Restaurant save(Restaurant entity) {
        return repository.save(entity);
    }

    @Override
    public List<Restaurant> save(List<Restaurant> entities) {
        return repository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Restaurant> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Restaurant> findAll() {
        return repository.findAll();
    }

    @Override
    public Restaurant update(Restaurant entity, Long id) {
        Optional<Restaurant> optionalRestaurant = findById(id);
        // Check for non updated fields
        if (optionalRestaurant.isPresent()) {
            if (entity.getAddress() != null) {
                optionalRestaurant.get().setAddress(entity.getAddress());
            }
            if (entity.getName() != null) {
                optionalRestaurant.get().setName(entity.getName());
            }
            if (entity.getCity() != null) {
                optionalRestaurant.get().setCity(entity.getCity());
            }
            if (entity.getDrivers() != null) {
                optionalRestaurant.get().setDrivers(entity.getDrivers());
            }
            return save(optionalRestaurant.get());
        }
        return null;
    }

    @Override
    public void addPizzasToRestaurantAsync(List<RestaurantIdsDTO> restaurantIdsDTOS) {
        // Async call
        rabbitTemplate.convertAndSend("", addPizzasToRestaurantRoutingKey, restaurantIdsDTOS);
    }

    @Override
    public void addPizzasToRestaurantSync(List<RestaurantIdsDTO> restaurantIdsDTOS) {
        // Sync call
        RestTemplate restTemplate = new RestTemplate();
        List<Object> result = List.of(Objects.requireNonNull(restTemplate.postForObject(pizzaServiceUrl, restaurantIdsDTOS, Object[].class)));
    }

    @Override
    public List<Object> getPizzasByRestaurantId(Long id) {
        // Sync call
        RestTemplate restTemplate = new RestTemplate();
        String uri = pizzaServiceUrl + "/" + id;
        List<Object> result = List.of(Objects.requireNonNull(
                restTemplate.getForObject(
                        uri,
                        Object[].class)));
        return result;
    }


}