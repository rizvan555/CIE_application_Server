package com.rizvankarimov.cie_app.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "weight", nullable = false)
    private double weight;

    @Column(name = "image", nullable = false,length = 100000)
    private String image;

    @Column(name = "halal", nullable = false)
    private String halal;

    @Column(name = "vegan", nullable = false)
    private String vegan;

    @Column(name = "vegetarian", nullable = false)
    private String vegetarian;

    @Column(name = "alcohol", nullable = false)
    private String alcohol;

    @Column(name = "allergic", nullable = false)
    private String allergic;

}
