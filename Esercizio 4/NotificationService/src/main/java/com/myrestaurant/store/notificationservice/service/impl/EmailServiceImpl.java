package com.myrestaurant.store.notificationservice.service.impl;

import com.myrestaurant.store.notificationservice.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Override
    public void sendMessage(String message) {
        log.info("Email message : {} ", message);
    }
}
