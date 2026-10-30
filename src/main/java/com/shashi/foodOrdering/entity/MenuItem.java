package com.shashi.foodOrdering.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "menu_items")
@Getter
@Setter
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuItemId;

    private String foodName;

    private String description;

    private Double price;

    private Boolean available;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")  // foreign key in menu_items table
    private Restaurant restaurant;
}