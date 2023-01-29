package com.myrestaurant.store.notificationservice.service.impl;

import com.myrestaurant.store.notificationservice.service.LetterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LetterServiceImpl implements LetterService {

    @Override
    public void sendMessage(String message) {
        log.info("Letter message : {} ", message);
    }
}
