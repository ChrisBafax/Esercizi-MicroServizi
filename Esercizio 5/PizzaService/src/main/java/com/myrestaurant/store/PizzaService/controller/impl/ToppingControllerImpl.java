package com.myrestaurant.store.PizzaService.controller.impl;

import com.myrestaurant.store.PizzaService.controller.ToppingController;
import com.myrestaurant.store.PizzaService.dto.ToppingDTO;
import com.myrestaurant.store.PizzaService.mapper.ToppingMapper;
import com.myrestaurant.store.PizzaService.model.Topping;
import com.myrestaurant.store.PizzaService.service.ToppingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/toppings")
@RequiredArgsConstructor
public class ToppingControllerImpl implements ToppingController {

    private final ToppingService toppingService;

    private final ToppingMapper toppingMapper;

    @Override
    @PostMapping(path = {"/", "/create"})
    @ResponseStatus(HttpStatus.CREATED)
    public ToppingDTO createTopping(@RequestBody ToppingDTO toppingDTO) {
        Topping topping = toppingMapper.asEntity(toppingDTO);
        // Save the topping with the service and then map it as a DTO.
        return toppingMapper.asDTO(toppingService.save(topping));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> findToppingById(@PathVariable("id") Long id) {
        if (!toppingService.checkId(id)) {
            return new ResponseEntity<>("The topping you are looking for is not in this database.", HttpStatus.NOT_FOUND);
        } else {
            // Find topping by id from service and map it as DTO.
            ToppingDTO dto = toppingMapper.asDTO(toppingService.findById(id).orElse(null));
            return new ResponseEntity<>(dto, HttpStatus.FOUND);
        }
    }

    @Override
    @DeleteMapping(path = {"/{id}/delete", "/{id}"})
    public ResponseEntity<?> deleteToppingById(@PathVariable("id") Long id) {
        if (!toppingService.checkId(id)) {
            return new ResponseEntity<>("The topping you are trying to delete is not in this database.", HttpStatus.NOT_FOUND);
        } else {
            // Delete topping by id from service.
            toppingService.deleteById(id);
            return new ResponseEntity<>("Topping deleted successfully.", HttpStatus.OK);
        }
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<ToppingDTO> findAllToppings() {
        // Find all toppings from service and map as DTO
        return toppingMapper.asDTOList(toppingService.findAll());
    }

    @Override
    @PutMapping(path = {"/{id}/update", "/{id}"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> updateTopping(@RequestBody ToppingDTO toppingDTO, @PathVariable("id") Long id) {
        if (!toppingService.checkId(id)) {
            return new ResponseEntity<>("The topping you are trying to update is not in this database.", HttpStatus.NOT_FOUND);
        } else {
            Topping topping = toppingMapper.asEntity(toppingDTO);
            // Update topping from service and map it as DTO.
            ToppingDTO dto = toppingMapper.asDTO(toppingService.update(topping, id));
            return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
        }
    }
}
