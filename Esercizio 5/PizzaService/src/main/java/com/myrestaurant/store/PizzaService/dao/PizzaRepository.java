package com.myrestaurant.store.PizzaService.dao;

import com.myrestaurant.store.PizzaService.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {

}
