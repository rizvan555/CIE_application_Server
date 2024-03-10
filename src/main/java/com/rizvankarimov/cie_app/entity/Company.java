package com.rizvankarimov.cie_app.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "companies")
public class Company
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false, length = 100)
    private String username;

    @Column(name = "password", unique = true, nullable = false)
    private String password;

    @Column(name = "company_name", unique = true, nullable = false)
    private String company_name;

    @Column(name = "phone", unique = true, nullable = false)
    private String phone;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "address", unique = true, nullable = false)
    private String address;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    private String accessToken;
    private String refreshToken;
}