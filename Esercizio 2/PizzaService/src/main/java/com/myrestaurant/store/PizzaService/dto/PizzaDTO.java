package com.myrestaurant.store.PizzaService.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class PizzaDTO {

    private long id;

    private String name;

    private boolean favourite;

    private Set<ToppingDTO> toppings;

}
