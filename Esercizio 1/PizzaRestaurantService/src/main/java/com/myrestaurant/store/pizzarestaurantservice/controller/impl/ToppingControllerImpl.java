package com.myrestaurant.store.pizzarestaurantservice.controller.impl;

import com.myrestaurant.store.pizzarestaurantservice.controller.ToppingController;
import com.myrestaurant.store.pizzarestaurantservice.dto.ToppingDTO;
import com.myrestaurant.store.pizzarestaurantservice.mapper.ToppingMapper;
import com.myrestaurant.store.pizzarestaurantservice.model.Topping;
import com.myrestaurant.store.pizzarestaurantservice.service.ToppingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topping")
@RequiredArgsConstructor
public class ToppingControllerImpl implements ToppingController {

    private final ToppingService toppingService;

    private final ToppingMapper toppingMapper;

    @Override
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ToppingDTO createTopping(@RequestBody ToppingDTO toppingDTO) {
        Topping topping = toppingMapper.asEntity(toppingDTO);
        // Save the topping with the service and then map it as a DTO.
        return toppingMapper.asDTO(toppingService.save(topping));
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ToppingDTO findToppingById(@PathVariable("id") Long id) {
        Topping topping = toppingService.findById(id).orElse(null);
        return toppingMapper.asDTO(topping);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteToppingById(@PathVariable("id") Long id) {
        toppingService.deleteById(id);
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<ToppingDTO> findAllToppings() {
        // Find all toppings from service and map as DTO
        return toppingMapper.asDTOList(toppingService.findAll());
    }

    @Override
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ToppingDTO updateTopping(@RequestBody ToppingDTO toppingDTO, @PathVariable("id") Long id) {
        Topping topping = toppingMapper.asEntity(toppingDTO);
        // Update topping from service and map it as DTO.
        return toppingMapper.asDTO(toppingService.update(topping, id));
    }
}