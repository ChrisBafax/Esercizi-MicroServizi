package com.myrestaurant.store.PizzaService.dao;

import com.myrestaurant.store.PizzaService.model.Topping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToppingRepository extends JpaRepository<Topping, Long> {

}