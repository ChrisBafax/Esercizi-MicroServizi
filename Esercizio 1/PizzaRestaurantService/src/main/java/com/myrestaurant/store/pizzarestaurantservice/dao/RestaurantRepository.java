package com.myrestaurant.store.pizzarestaurantservice.dao;

import com.myrestaurant.store.pizzarestaurantservice.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}