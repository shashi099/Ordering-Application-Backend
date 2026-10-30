package com.shashi.foodOrdering.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name="restaurants")
@Getter
@Setter
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantId;
    private String restaurantName;
    private String restaurantAddress;
    private String restaurantImage;
    private Double restaurantRating;

    @JsonIgnore
    @OneToMany(
            mappedBy = "restaurant",
            cascade = CascadeType.ALL)
    private List<MenuItem> menuItems;


}
