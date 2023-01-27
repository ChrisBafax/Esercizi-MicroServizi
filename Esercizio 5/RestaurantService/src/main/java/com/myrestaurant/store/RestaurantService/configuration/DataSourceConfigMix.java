package com.myrestaurant.store.RestaurantService.configuration;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"prod", "dev"})
public class DataSourceConfigMix {

        public DataSourceConfigMix() {
            System.out.println("I'm in prod or dev profile and I'm into DataSourceConfigMix.");
        }
}