package com.myrestaurant.store.PizzaService.service.impl;

import com.myrestaurant.store.PizzaService.dao.PizzaRepository;
import com.myrestaurant.store.PizzaService.dao.RestaurantIdsRepository;
import com.myrestaurant.store.PizzaService.model.Pizza;
import com.myrestaurant.store.PizzaService.model.RestaurantIds;
import com.myrestaurant.store.PizzaService.service.PizzaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PizzaServiceImpl implements PizzaService {

    private final PizzaRepository pizzaRepository;

    private final RestaurantIdsRepository restaurantIdsRepository;

    @Override
    public Pizza save(Pizza entity) {
        return pizzaRepository.save(entity);
    }

    @Override
    public List<Pizza> save(List<Pizza> entities) {
        return pizzaRepository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        pizzaRepository.deleteById(id);
    }

    @Override
    public Optional<Pizza> findById(Long id) {
        return pizzaRepository.findById(id);
    }

    @Override
    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

    @Override
    public Pizza update(Pizza entity, Long id) {
        Optional<Pizza> optionalPizza = findById(id);
        if (optionalPizza.isPresent()) {
            return save(entity);
        }
        return null;
    }

    @Override
    public List<Pizza> findByRestaurantId(Long restaurantId) {
        List<RestaurantIds> restaurantIds = restaurantIdsRepository.findByRestaurantId(restaurantId);
        List<Pizza> pizzas = new ArrayList<>(restaurantIds.size());
        for (RestaurantIds el : restaurantIds) {
            pizzas.add(pizzaRepository.findById(el.getPizzaId()).get());
        }
        return pizzas;
    }

    @Override
    public List<Pizza> addPizzasToRestaurant(List<RestaurantIds> restaurantIds) {
        List<Pizza> pizzas = new ArrayList<>();
        for (RestaurantIds el : restaurantIds) {
            pizzas.add(pizzaRepository.findById(el.getPizzaId()).get());
        }
        restaurantIdsRepository.saveAll(restaurantIds);
        return pizzas;
    }

}
