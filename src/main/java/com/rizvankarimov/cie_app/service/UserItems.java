package com.rizvankarimov.cie_app.service;


import com.rizvankarimov.cie_app.entity.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface UserItems {

    @Transactional
    void addService(Products myItems);

    @Transactional
    void addUserServices(UserItems userItems);

    @Transactional
    void addProducts(Products products);

    void addUserServices(User_Items userItems);

    User saveUser(User user);

    Company saveCompany(Company company);

    void updateUser(User user);

    void createAdminUser();

    Optional<User> findByUsername(String username);

    void changeRole(Role newRole, String username);

    List<User> findAllUsers();

    User findUserById(Long id);

    List<Products> getAllProducts();

    Products getProductById(Long id);

    Object updateProducts(Long id);
}
