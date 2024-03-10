package com.rizvankarimov.cie_app.service;

import com.rizvankarimov.cie_app.entity.*;
import com.rizvankarimov.cie_app.repository.CompanyRepository;
import com.rizvankarimov.cie_app.repository.ServiceRepository;
import com.rizvankarimov.cie_app.repository.UserRepository;
import com.rizvankarimov.cie_app.repository.UserServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserItemsImpl implements UserItems
{
    private final UserRepository userRepository;
    private final ServiceRepository serviceRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserServiceRepository userServiceRepository;
    private final CompanyRepository companyRepository;



    public void createAdminUser() {
        User adminUser = new User();
        adminUser.setUsername("rizvan");
        adminUser.setPassword(passwordEncoder.encode("111111"));
        adminUser.setRole(Role.ADMIN);
        adminUser.setCreateTime(LocalDateTime.now());

        userRepository.save(adminUser);
    }

    @Override
    public void addService() {

    }
    @Override
    @Transactional
    public void addService(My_Items myItems) {
        serviceRepository.save(myItems);
    }

    @Override
    public void addUserServices(UserItems userItems) {

    }

    @Override
    public void addUserServices(User_Items userItems) {
        userServiceRepository.save(userItems);
    }

    @Override
    public User saveUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setCreateTime(LocalDateTime.now());

        return userRepository.save(user);
    }

    @Override
    public Company saveCompany(Company company) {
        company.setPassword(passwordEncoder.encode(company.getPassword()));
        company.setCreateTime(LocalDateTime.now());

        return companyRepository.save(company);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }


    @Override
    @Transactional
    public void changeRole(Role newRole, String username)
    {
        userRepository.updateUserRole(username, newRole);
    }

    @Override
    public List<User> findAllUsers()
    {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<My_Items> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public My_Items getServiceById(Long id) {
        return serviceRepository.findById(id).get();
    }

    @Override
    public Object updateService(Long id) {
     return serviceRepository.findById(id).get();
    }

}