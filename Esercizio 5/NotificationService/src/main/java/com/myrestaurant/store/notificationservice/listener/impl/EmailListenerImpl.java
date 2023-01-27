package com.myrestaurant.store.notificationservice.listener.impl;

import com.myrestaurant.store.notificationservice.listener.NotificationListener;
import com.myrestaurant.store.notificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@Profile("email")
public class EmailListenerImpl implements NotificationListener {

    private final EmailService emailService;

    @Override
    @RabbitListener(queues = "${app.rabbitmq.notify-pizzas-added-routingkey}")
    public void onNotifyPizzasToRestaurant(String message) {
        log.info("Into EmailListenerImpl - onNotifyPizzasToRestaurant method");
        emailService.sendMessage(message);
        log.info("Finished Email.");
    }
}