package com.myrestaurant.store.RestaurantService.mapper;

import com.myrestaurant.store.RestaurantService.dto.DriverDTO;
import com.myrestaurant.store.RestaurantService.dto.RestaurantDTO;
import com.myrestaurant.store.RestaurantService.model.Driver;
import com.myrestaurant.store.RestaurantService.model.Restaurant;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-26T17:34:01+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class RestaurantMapperImpl implements RestaurantMapper {

    @Override
    public Restaurant asEntity(RestaurantDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Restaurant.RestaurantBuilder restaurant = Restaurant.builder();

        restaurant.id( dto.getId() );
        restaurant.name( dto.getName() );
        restaurant.address( dto.getAddress() );
        restaurant.city( dto.getCity() );
        restaurant.drivers( driverDTOSetToDriverSet( dto.getDrivers() ) );

        return restaurant.build();
    }

    @Override
    public RestaurantDTO asDTO(Restaurant entity) {
        if ( entity == null ) {
            return null;
        }

        RestaurantDTO.RestaurantDTOBuilder restaurantDTO = RestaurantDTO.builder();

        restaurantDTO.id( entity.getId() );
        restaurantDTO.name( entity.getName() );
        restaurantDTO.address( entity.getAddress() );
        restaurantDTO.city( entity.getCity() );
        restaurantDTO.drivers( driverSetToDriverDTOSet( entity.getDrivers() ) );

        return restaurantDTO.build();
    }

    @Override
    public List<Restaurant> asEntityList(List<RestaurantDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Restaurant> list = new ArrayList<Restaurant>( dtoList.size() );
        for ( RestaurantDTO restaurantDTO : dtoList ) {
            list.add( asEntity( restaurantDTO ) );
        }

        return list;
    }

    @Override
    public List<RestaurantDTO> asDTOList(List<Restaurant> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<RestaurantDTO> list = new ArrayList<RestaurantDTO>( entityList.size() );
        for ( Restaurant restaurant : entityList ) {
            list.add( asDTO( restaurant ) );
        }

        return list;
    }

    protected Driver driverDTOToDriver(DriverDTO driverDTO) {
        if ( driverDTO == null ) {
            return null;
        }

        Driver.DriverBuilder driver = Driver.builder();

        driver.id( driverDTO.getId() );
        driver.name( driverDTO.getName() );

        return driver.build();
    }

    protected Set<Driver> driverDTOSetToDriverSet(Set<DriverDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Driver> set1 = new LinkedHashSet<Driver>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( DriverDTO driverDTO : set ) {
            set1.add( driverDTOToDriver( driverDTO ) );
        }

        return set1;
    }

    protected DriverDTO driverToDriverDTO(Driver driver) {
        if ( driver == null ) {
            return null;
        }

        DriverDTO.DriverDTOBuilder driverDTO = DriverDTO.builder();

        driverDTO.id( driver.getId() );
        driverDTO.name( driver.getName() );

        return driverDTO.build();
    }

    protected Set<DriverDTO> driverSetToDriverDTOSet(Set<Driver> set) {
        if ( set == null ) {
            return null;
        }

        Set<DriverDTO> set1 = new LinkedHashSet<DriverDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Driver driver : set ) {
            set1.add( driverToDriverDTO( driver ) );
        }

        return set1;
    }
}
