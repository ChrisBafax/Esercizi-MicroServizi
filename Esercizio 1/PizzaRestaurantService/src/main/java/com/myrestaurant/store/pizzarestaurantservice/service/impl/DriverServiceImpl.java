package com.myrestaurant.store.pizzarestaurantservice.service.impl;

import com.myrestaurant.store.pizzarestaurantservice.dao.DriverRepository;
import com.myrestaurant.store.pizzarestaurantservice.model.Driver;
import com.myrestaurant.store.pizzarestaurantservice.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository repository;

    @Override
    public Driver save(Driver entity) {
        return repository.save(entity);
    }

    @Override
    public List<Driver> save(List<Driver> entities) {
        return repository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Driver> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Driver> findAll() {
        return repository.findAll();
    }

    @Override
    public Driver update(Driver entity, Long id) {
        Optional<Driver> optionalDriver = findById(id);
        if (optionalDriver.isPresent()) {
            return save(entity);
        }
        return null;
    }
}