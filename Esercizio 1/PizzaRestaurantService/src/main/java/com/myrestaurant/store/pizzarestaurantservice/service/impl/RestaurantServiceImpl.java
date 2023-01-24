package com.myrestaurant.store.pizzarestaurantservice.service.impl;

import com.myrestaurant.store.pizzarestaurantservice.dao.RestaurantRepository;
import com.myrestaurant.store.pizzarestaurantservice.model.Restaurant;
import com.myrestaurant.store.pizzarestaurantservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository repository;

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
    public Restaurant addPizzaToRestaurant(Restaurant restaurant) {
        return repository.save(restaurant);
    }
}
