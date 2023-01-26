package com.myrestaurant.store.RestaurantService.dao;

import com.myrestaurant.store.RestaurantService.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

}