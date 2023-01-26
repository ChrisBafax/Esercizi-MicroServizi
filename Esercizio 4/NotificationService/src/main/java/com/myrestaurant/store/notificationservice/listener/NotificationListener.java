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

    @RabbitListener(queues = "${app.rabbitmq.notify-pizzas-added-routingkey}")
    public void onNotifyPizzasAddedToRestaurant(String message) {
        log.info("Into onAddPizzasToRestaurant method");
        emailService.sendMessage(message);
        smsService.sendMessage(message);
        log.info("Finished.");
    }

}