package com.myrestaurant.store.notificationservice.listener.impl;

import com.myrestaurant.store.notificationservice.listener.NotificationListener;
import com.myrestaurant.store.notificationservice.service.EmailService;
import com.myrestaurant.store.notificationservice.service.LetterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@Profile("letter")
public class LetterListenerImpl implements NotificationListener {

    LetterService letterService;

    EmailService emailService;

    @Override
    @RabbitListener(queues = "${app.rabbitmq.notify-pizzas-added-routingkey}")
    public void onNotifyPizzasToRestaurant(String message) {
        log.info("Into LetterListenerImpl - onNotifyPizzasToRestaurant method");
        letterService.sendMessage(message);
        emailService.sendMessage(message);
        log.info("Finished Letter.");
    }
}