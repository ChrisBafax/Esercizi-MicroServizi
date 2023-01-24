package com.myrestaurant.store.PizzaService.mapper;

import com.myrestaurant.store.PizzaService.dto.PizzaDTO;
import com.myrestaurant.store.PizzaService.dto.ToppingDTO;
import com.myrestaurant.store.PizzaService.model.Pizza;
import com.myrestaurant.store.PizzaService.model.Topping;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-24T15:02:47+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class PizzaMapperImpl implements PizzaMapper {

    @Override
    public Pizza asEntity(PizzaDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Pizza.PizzaBuilder pizza = Pizza.builder();

        pizza.id( dto.getId() );
        pizza.name( dto.getName() );
        pizza.favourite( dto.isFavourite() );
        pizza.toppings( toppingDTOSetToToppingSet( dto.getToppings() ) );

        return pizza.build();
    }

    @Override
    public PizzaDTO asDTO(Pizza entity) {
        if ( entity == null ) {
            return null;
        }

        PizzaDTO.PizzaDTOBuilder pizzaDTO = PizzaDTO.builder();

        if ( entity.getId() != null ) {
            pizzaDTO.id( entity.getId() );
        }
        pizzaDTO.name( entity.getName() );
        pizzaDTO.favourite( entity.isFavourite() );
        pizzaDTO.toppings( toppingSetToToppingDTOSet( entity.getToppings() ) );

        return pizzaDTO.build();
    }

    @Override
    public List<Pizza> asEntityList(List<PizzaDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Pizza> list = new ArrayList<Pizza>( dtoList.size() );
        for ( PizzaDTO pizzaDTO : dtoList ) {
            list.add( asEntity( pizzaDTO ) );
        }

        return list;
    }

    @Override
    public List<PizzaDTO> asDTOList(List<Pizza> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PizzaDTO> list = new ArrayList<PizzaDTO>( entityList.size() );
        for ( Pizza pizza : entityList ) {
            list.add( asDTO( pizza ) );
        }

        return list;
    }

    protected Topping toppingDTOToTopping(ToppingDTO toppingDTO) {
        if ( toppingDTO == null ) {
            return null;
        }

        Topping.ToppingBuilder topping = Topping.builder();

        if ( toppingDTO.getId() != null ) {
            topping.id( toppingDTO.getId() );
        }
        topping.name( toppingDTO.getName() );

        return topping.build();
    }

    protected Set<Topping> toppingDTOSetToToppingSet(Set<ToppingDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Topping> set1 = new LinkedHashSet<Topping>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ToppingDTO toppingDTO : set ) {
            set1.add( toppingDTOToTopping( toppingDTO ) );
        }

        return set1;
    }

    protected ToppingDTO toppingToToppingDTO(Topping topping) {
        if ( topping == null ) {
            return null;
        }

        ToppingDTO.ToppingDTOBuilder toppingDTO = ToppingDTO.builder();

        toppingDTO.id( topping.getId() );
        toppingDTO.name( topping.getName() );

        return toppingDTO.build();
    }

    protected Set<ToppingDTO> toppingSetToToppingDTOSet(Set<Topping> set) {
        if ( set == null ) {
            return null;
        }

        Set<ToppingDTO> set1 = new LinkedHashSet<ToppingDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Topping topping : set ) {
            set1.add( toppingToToppingDTO( topping ) );
        }

        return set1;
    }
}
