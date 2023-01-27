package com.myrestaurant.store.RestaurantService.configuration;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DataSourceConfigDev {

        public DataSourceConfigDev() {
            System.out.println("I'm in dev profile and I'm into DataSourceConfigDev.");
        }
}
