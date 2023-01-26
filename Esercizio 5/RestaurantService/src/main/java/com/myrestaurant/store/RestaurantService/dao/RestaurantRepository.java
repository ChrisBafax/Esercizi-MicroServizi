package com.myrestaurant.store.RestaurantService.dao;

import com.myrestaurant.store.RestaurantService.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}