package com.myrestaurant.store.notificationservice.listener.impl;

import com.myrestaurant.store.notificationservice.listener.NotificationListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@Profile("letter")
public class LetterListenerImpl implements NotificationListener {

    @Override
    public void onNotifyPizzasToRestaurant(String message) {
        log.info("Into LetterListenerImpl - onNotifyPizzasToRestaurant method");
        log.info("Finished Letter.");
    }
}