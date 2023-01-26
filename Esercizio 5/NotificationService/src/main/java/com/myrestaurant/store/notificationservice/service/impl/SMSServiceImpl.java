package com.myrestaurant.store.notificationservice.service.impl;

import com.myrestaurant.store.notificationservice.service.SMSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SMSServiceImpl implements SMSService {

    @Override
    public void sendMessage(String message) {
        log.info("SMS message : {} ", message);
    }
}
