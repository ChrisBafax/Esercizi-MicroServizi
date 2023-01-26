package com.myrestaurant.store.RestaurantService.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PizzasToRestaurantAddedListener {

    @RabbitListener(queues = "${app.rabbitmq.pizzas-added-routingkey}")
    public void onPizzasToRestaurantAdded(List<Object> pizzas) {
        log.info("Into onPizzasToRestaurantAdded method. -> Now do everything!");

        for(Object pizza : pizzas) {
            log.info("Pizza: " + pizza.toString() + " added to restaurant!");
        }

    }

}