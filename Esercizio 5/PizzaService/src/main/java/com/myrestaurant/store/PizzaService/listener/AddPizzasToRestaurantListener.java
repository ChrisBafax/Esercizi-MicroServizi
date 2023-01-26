package com.myrestaurant.store.PizzaService.listener;

import com.myrestaurant.store.PizzaService.dto.RestaurantIdsDTO;
import com.myrestaurant.store.PizzaService.mapper.RestaurantIdsMapper;
import com.myrestaurant.store.PizzaService.service.PizzaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddPizzasToRestaurantListener {

    private final PizzaService pizzaService;

    private final RestaurantIdsMapper restaurantIdsMapper;

    @RabbitListener(queues = "${app.rabbitmq.add-pizzas-routingkey}")
    public void addPizzasToRestaurant(List<RestaurantIdsDTO> restaurantIdsDTOS) {
        pizzaService.addPizzasToRestaurant(restaurantIdsMapper.asEntityList(restaurantIdsDTOS));
        log.info("Added pizzas to restaurant.");
    }
}
