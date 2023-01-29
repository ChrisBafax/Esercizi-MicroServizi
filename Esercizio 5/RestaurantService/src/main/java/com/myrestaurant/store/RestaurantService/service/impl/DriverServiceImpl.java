package com.myrestaurant.store.RestaurantService.service.impl;

import com.myrestaurant.store.RestaurantService.dao.DriverRepository;
import com.myrestaurant.store.RestaurantService.model.Driver;
import com.myrestaurant.store.RestaurantService.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    @Override
    public Driver save(Driver entity) {
        return driverRepository.save(entity);
    }

    @Override
    public List<Driver> save(List<Driver> entities) {
        return driverRepository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        driverRepository.deleteById(id);
    }

    @Override
    public Optional<Driver> findById(Long id) {
        return driverRepository.findById(id);
    }

    @Override
    public List<Driver> findAll() {
        return driverRepository.findAll();
    }

    @Override
    public Driver update(Driver entity, Long id) {
        Optional<Driver> optionalDriver = findById(id);
        // Check for non updated fields
        if (optionalDriver.isPresent()) {
            if (entity.getName() != null) {
                optionalDriver.get().setName(entity.getName());
            }
            if (entity.getRestaurants() != null) {
                optionalDriver.get().setRestaurants(entity.getRestaurants());
            }
            return save(optionalDriver.get());
        }
        return null;
    }

    @Override
    public boolean checkId(Long id) {
        return driverRepository.existsById(id);
    }
}