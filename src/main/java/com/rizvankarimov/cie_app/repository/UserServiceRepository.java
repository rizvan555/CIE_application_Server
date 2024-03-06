package com.rizvankarimov.cie_app.repository;

import com.rizvankarimov.cie_app.entity.User_Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserServiceRepository extends JpaRepository<User_Items, Long> {
    //Optional<UserServices> addUserServices(String serviceName);

}
