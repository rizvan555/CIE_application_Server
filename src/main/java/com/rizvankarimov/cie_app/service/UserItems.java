package com.rizvankarimov.cie_app.service;


import com.rizvankarimov.cie_app.entity.My_Items;
import com.rizvankarimov.cie_app.entity.Role;
import com.rizvankarimov.cie_app.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface UserItems {

    void addService();

    @Transactional
    void addService(My_Items myItems);

    @Transactional
    void addUserServices(UserItems userItems);

    User saveUser(User user);

    void updateUser(User user);

    void createAdminUser();

    Optional<User> findByUsername(String username);

    void changeRole(Role newRole, String username);

    List<User> findAllUsers();

    User findUserById(Long id);

    List<My_Items> getAllServices();

    My_Items getServiceById(Long id);

    Object updateService(Long id);
}
