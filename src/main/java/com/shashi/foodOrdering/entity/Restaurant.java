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
    private String restaurant_name;
    private String restaurant_address;
    private Double restaurant_rating;
    private String restaurant_image;

    @JsonIgnore
    @OneToMany(
            mappedBy = "restaurant",
            cascade = CascadeType.ALL)
    private List<MenuItem> menuItems;


}
