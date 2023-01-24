package com.myrestaurant.store.pizzarestaurantservice.controller.impl;

import com.myrestaurant.store.pizzarestaurantservice.controller.PizzaController;
import com.myrestaurant.store.pizzarestaurantservice.dto.PizzaDTO;
import com.myrestaurant.store.pizzarestaurantservice.mapper.PizzaMapper;
import com.myrestaurant.store.pizzarestaurantservice.model.Pizza;
import com.myrestaurant.store.pizzarestaurantservice.service.PizzaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pizza")
@RequiredArgsConstructor
public class PizzaControllerImpl implements PizzaController {

    private final PizzaService pizzaService;

    private final PizzaMapper pizzaMapper;

    @Override
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public PizzaDTO createPizza(@RequestBody PizzaDTO pizzaDTO) {
        Pizza pizza = pizzaMapper.asEntity(pizzaDTO);
        // Save the pizza with the service and then map it as a DTO.
        return pizzaMapper.asDTO(pizzaService.save(pizza));
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public PizzaDTO findPizzaById(@PathVariable("id") Long id) {
        Pizza pizza = pizzaService.findById(id).orElse(null);
        return pizzaMapper.asDTO(pizza);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePizzaById(@PathVariable("id") Long id) {
        pizzaService.deleteById(id);
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<PizzaDTO> findAllPizzas() {
        // Find all pizzas from service and map as DTO.
        return pizzaMapper.asDTOList(pizzaService.findAll());
    }

    @Override
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PizzaDTO updatePizza(@RequestBody PizzaDTO pizzaDTO, @PathVariable("id") Long id) {
        Pizza pizza = pizzaMapper.asEntity(pizzaDTO);
        // Update pizza from service and map it as DTO.
        return pizzaMapper.asDTO(pizzaService.update(pizza, id));
    }

    @Override
    @GetMapping("/restaurant/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<PizzaDTO> findPizzasByRestaurantID(@PathVariable Long id) {
        List<Pizza> pizzas = pizzaService.findByRestaurantId(id);
        return pizzaMapper.asDTOList(pizzas);
    }
}