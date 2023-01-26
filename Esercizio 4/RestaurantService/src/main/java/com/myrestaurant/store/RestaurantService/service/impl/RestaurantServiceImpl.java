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

import java.util.List;
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
        if (optionalRestaurant.isPresent()) {
            return save(entity);
        }
        return null;
    }

    @Override
    public void addPizzasToRestaurant(List<RestaurantIdsDTO> restaurantIdsDTOS) {
        // Sync call
        // List<Object> result = List.of(Objects.requireNonNull(restTemplate.postForObject(pizzaServiceUrl, restaurantIdsDTOS,Object[].class)));
        // rabbitTemplate.convertAndSend("", addPizzasToRestaurantRoutingKey,"Added no. " + result.size() + " pizzas.");

        // Async call
        rabbitTemplate.convertAndSend("", addPizzasToRestaurantRoutingKey, restaurantIdsDTOS);
    }
}
