package com.myrestaurant.store.RestaurantService.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class DriverDTO {

    private Long id;

    private String name;

//    private Set<Restaurant> restaurants;
}
