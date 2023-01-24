package com.myrestaurant.store.pizzarestaurantservice.dao;

import com.myrestaurant.store.pizzarestaurantservice.model.Topping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToppingRepository extends JpaRepository<Topping, Long> {

}