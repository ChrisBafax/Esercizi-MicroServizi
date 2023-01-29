package com.myrestaurant.store.RestaurantService.configuration;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("coll")
public class DataSourceConfigColl {

    public DataSourceConfigColl() {
        System.out.println("I'm in coll profile and I'm into DataSourceConfigColl.");
    }
}
