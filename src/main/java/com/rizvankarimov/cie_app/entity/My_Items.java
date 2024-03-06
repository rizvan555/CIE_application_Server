package com.rizvankarimov.cie_app.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "myItems")
public class My_Items {
    @Id
    private Long id;

    @Column(name = "username", unique = false, nullable = false, length = 100)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "selectedService", nullable = false)
    private String selectedService;

    @Column(name = "selectedTimeStart", nullable = false)
    private String selectedTimeStart;

}
