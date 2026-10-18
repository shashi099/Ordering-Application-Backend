package com.shashi.foodOrdering.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="restaurants")
@Getter
@Setter
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurant_id;
    private String restaurant_name;
    private String restaurant_address;
    private Double restaurant_rating;
    private String restaurant_image;

}
