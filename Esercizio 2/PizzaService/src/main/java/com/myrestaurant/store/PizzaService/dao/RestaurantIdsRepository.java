package com.myrestaurant.store.PizzaService.dao;

import com.myrestaurant.store.PizzaService.model.RestaurantIds;
import com.myrestaurant.store.PizzaService.model.RestaurantIdsPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantIdsRepository extends JpaRepository<RestaurantIds, RestaurantIdsPK> {

    List<RestaurantIds> findByRestaurantId(Long restaurantId);

}