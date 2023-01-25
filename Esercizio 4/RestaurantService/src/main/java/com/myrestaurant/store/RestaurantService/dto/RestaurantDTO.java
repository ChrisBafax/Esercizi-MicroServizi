package com.myrestaurant.store.RestaurantService.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class RestaurantDTO {

    private Long id;

    private String name;

    private String address;

    private String city;

    private Set<DriverDTO> drivers;

}
