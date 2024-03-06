package com.rizvankarimov.cie_app.repository;



import com.rizvankarimov.cie_app.entity.My_Items;
import com.rizvankarimov.cie_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceRepository extends JpaRepository<My_Items, Long> {
    Optional<User> findByUsername(String username);
}
