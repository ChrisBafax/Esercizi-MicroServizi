package com.myrestaurant.store.PizzaService.controller.impl;

import com.myrestaurant.store.PizzaService.controller.PizzaController;
import com.myrestaurant.store.PizzaService.dto.PizzaDTO;
import com.myrestaurant.store.PizzaService.dto.RestaurantIdsDTO;
import com.myrestaurant.store.PizzaService.mapper.PizzaMapper;
import com.myrestaurant.store.PizzaService.mapper.RestaurantIdsMapper;
import com.myrestaurant.store.PizzaService.model.Pizza;
import com.myrestaurant.store.PizzaService.model.RestaurantIds;
import com.myrestaurant.store.PizzaService.service.PizzaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pizzas")
@RequiredArgsConstructor
public class PizzaControllerImpl implements PizzaController {

    private final PizzaService pizzaService;

    private final PizzaMapper pizzaMapper;

    private final RestaurantIdsMapper restaurantIdsMapper;

    @Override
    @PostMapping(path = {"/", "/create"})
    @ResponseStatus(HttpStatus.CREATED)
    public PizzaDTO createPizza(@RequestBody PizzaDTO pizzaDTO) {
        Pizza pizza = pizzaMapper.asEntity(pizzaDTO);
        // Save the pizza with the service and then map it as a DTO.
        return pizzaMapper.asDTO(pizzaService.save(pizza));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> findPizzaById(@PathVariable("id") Long id) {
        if (!pizzaService.checkId(id)) {
            return new ResponseEntity<>("The pizza you are trying to find is not in this database.", HttpStatus.NOT_FOUND);
        } else {
            // Find pizza by ID from service and map as DTO.
            return new ResponseEntity<>(pizzaMapper.asDTO(pizzaService.findById(id).orElse(null)), HttpStatus.FOUND);
        }
    }

    @Override
    @DeleteMapping(path = {"/{id}/delete", "/{id}"})
    public ResponseEntity<?> deletePizzaById(@PathVariable("id") Long id) {
        if (!pizzaService.checkId(id)) {
            return new ResponseEntity<>("The pizza you are trying to delete is not in this database.", HttpStatus.NOT_FOUND);
        } else {
            // Delete pizza from service.
            pizzaService.deleteById(id);
            return new ResponseEntity<>("Pizza deleted successfully.", HttpStatus.OK);
        }
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<?> findAllPizzas() {
        if (pizzaService.findAll().isEmpty()) {
            return new ResponseEntity<>("There are no pizzas in this database.", HttpStatus.NOT_FOUND);
        } else {
            // Find all pizzas from service and map as DTO.
            return new ResponseEntity<>(pizzaMapper.asDTOList(pizzaService.findAll()), HttpStatus.FOUND);
        }
    }

    @Override
    @PutMapping(path = {"/{id}/update", "/{id}"})
    public ResponseEntity<?> updatePizza(@RequestBody PizzaDTO pizzaDTO, @PathVariable("id") Long id) {
        if (!pizzaService.checkId(id)) {
            return new ResponseEntity<>("The pizza you are trying to update is not in this database.", HttpStatus.NOT_FOUND);
        } else {
            Pizza pizza = pizzaMapper.asEntity(pizzaDTO);
            // Update pizza from service and map it as DTO.
            PizzaDTO dto = pizzaMapper.asDTO(pizzaService.update(pizza, id));
            return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
        }
    }

    @Override
    @GetMapping("/restaurant/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<PizzaDTO> findByRestaurantId(@PathVariable Long id) {
        List<Pizza> pizzas = pizzaService.findByRestaurantId(id);
        return pizzaMapper.asDTOList(pizzas);
    }

    @Override
    @PostMapping("/restaurant")
    @ResponseStatus(HttpStatus.OK)
    public List<PizzaDTO> addPizzasToRestaurant(@RequestBody List<RestaurantIdsDTO> restaurantIdsDTOS) {
        List<RestaurantIds> ids = restaurantIdsMapper.asEntityList(restaurantIdsDTOS);
        return pizzaMapper.asDTOList(pizzaService.addPizzasToRestaurant(ids));
    }
}