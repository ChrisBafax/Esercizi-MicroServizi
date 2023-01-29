package com.myrestaurant.store.PizzaService.model;

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
@Table(name = "pizzas")
public class Pizza implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pizza_id")
    private Long id;

    @NotBlank
    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @Column(name = "favourite")
    private boolean favourite;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "pizzas_toppings",
            joinColumns = @JoinColumn(
                    name = "pizza_id",
                    referencedColumnName = "pizza_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "topping_id",
                    referencedColumnName = "topping_id"
            )
    )
    @JsonIgnore
    private Set<Topping> toppings = new HashSet<>();
}