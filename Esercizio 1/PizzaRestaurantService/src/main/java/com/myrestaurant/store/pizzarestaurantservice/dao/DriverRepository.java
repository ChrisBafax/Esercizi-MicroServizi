package com.myrestaurant.store.pizzarestaurantservice.dao;

import com.myrestaurant.store.pizzarestaurantservice.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

}