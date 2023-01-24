package com.myrestaurant.store.pizzarestaurantservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Builder
@Table(name = "restaurants")
public class Restaurant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long id;

    @NotBlank
    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @NotBlank
    @Size(max = 255)
    @Column(name = "address")
    private String address;

    @NotBlank
    @Size(max = 255)
    @Column(name = "city")
    private String city;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "restaurants_drivers",
            joinColumns = @JoinColumn(
                    name = "restaurant_id",
                    referencedColumnName = "restaurant_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "driver_id",
                    referencedColumnName = "driver_id"
            )
    )
    @JsonIgnore
    private Set<Driver> drivers = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "restaurants_pizzas",
            joinColumns = @JoinColumn(
                    name = "restaurant_id",
                    referencedColumnName = "restaurant_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "pizza_id",
                    referencedColumnName = "pizza_id"
            )
    )
    @JsonIgnore
    private Set<Pizza> pizzas = new HashSet<>();
}
