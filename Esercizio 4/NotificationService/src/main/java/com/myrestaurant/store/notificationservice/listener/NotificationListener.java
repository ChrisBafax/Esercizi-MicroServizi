package com.myrestaurant.store.notificationservice.listener;

import com.myrestaurant.store.notificationservice.service.EmailService;
import com.myrestaurant.store.notificationservice.service.SMSService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationListener {

    private final EmailService emailService;

    private final SMSService smsService;

    @RabbitListener(queues = "${app.rabbitmq.pizzas-added-routingkey}")
    public void onAddPizzasToRestaurant(List<Object> pizzas) {
        String message = "Added no. " + (pizzas.size()/47) + " pizzas to restaurant.";
        log.info("Into onAddPizzasToRestaurant method");
        log.info("Received Message: " + message);
        emailService.sendMessage(message);
        smsService.sendMessage(message);
        log.info("Finished.");
        System.out.println("Received Message: " + message);
    }

}