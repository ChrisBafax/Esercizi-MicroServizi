package com.myrestaurant.store.PizzaService.service.impl;

import com.myrestaurant.store.PizzaService.dao.ToppingRepository;
import com.myrestaurant.store.PizzaService.model.Topping;
import com.myrestaurant.store.PizzaService.service.ToppingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ToppingServiceImpl implements ToppingService {

    private final ToppingRepository toppingRepository;

    @Override
    public Topping save(Topping entity) {
        return toppingRepository.save(entity);
    }

    @Override
    public List<Topping> save(List<Topping> entities) {
        return toppingRepository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        toppingRepository.deleteById(id);
    }

    @Override
    public Optional<Topping> findById(Long id) {
        return toppingRepository.findById(id);
    }

    @Override
    public List<Topping> findAll() {
        return toppingRepository.findAll();
    }

    @Override
    public Topping update(Topping entity, Long id) {
        Optional<Topping> optionalTopping = findById(id);
        // Check for non updated fields
        if (optionalTopping.isPresent()) {
            if (entity.getName() != null) {
                optionalTopping.get().setName(entity.getName());
            }
            if (entity.getPizzas() != null) {
                optionalTopping.get().setPizzas(entity.getPizzas());
            }
            return save(optionalTopping.get());
        }
        return null;
    }

    @Override
    public boolean checkId(Long id) {
        return toppingRepository.existsById(id);
    }
}