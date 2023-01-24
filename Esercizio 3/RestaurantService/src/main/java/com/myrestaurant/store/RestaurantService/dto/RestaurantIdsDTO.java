package com.myrestaurant.store.RestaurantService.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Setter
public class RestaurantIdsDTO {

    private Long restaurantId;

    private Long pizzaId;

}
