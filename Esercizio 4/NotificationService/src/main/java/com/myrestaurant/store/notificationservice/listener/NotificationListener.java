package com.myrestaurant.store.notificationservice.listener;

import com.myrestaurant.store.notificationservice.service.EmailService;
import com.myrestaurant.store.notificationservice.service.SMSService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationListener {

    private final EmailService emailService;

    private final SMSService smsService;

    @RabbitListener(queues = "${app.rabbitmq.restaurant-service-routingkey}")
    public void onAddPizzasToRestaurant(String message) {
        log.info("Into onAddPizzasToRestaurant method");
        log.info("Received Message: " + message);
        emailService.sendMessage(message);
        smsService.sendMessage(message);
        log.info("Finished.");
        System.out.println("Received Message: " + message);
    }

}