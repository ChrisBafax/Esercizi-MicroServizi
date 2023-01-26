package com.myrestaurant.store.notificationservice.service;

public interface NotificationService<M> {

    void sendMessage(M message);

}