package com.myrestaurant.store.RestaurantService.service;

import java.util.List;
import java.util.Optional;

public interface GenericService<E, M> {

    E save(E entity);

    List<E> save(List<E> entities);

    void deleteById(M id);

    Optional<E> findById(M id);

    List<E> findAll();

    E update(E entity, M id);

}
