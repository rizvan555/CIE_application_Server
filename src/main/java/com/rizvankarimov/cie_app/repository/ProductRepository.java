package com.rizvankarimov.cie_app.repository;



import com.rizvankarimov.cie_app.entity.Products;
import com.rizvankarimov.cie_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
}

