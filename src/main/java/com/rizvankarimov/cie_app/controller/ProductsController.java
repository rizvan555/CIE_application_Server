package com.rizvankarimov.cie_app.controller;

import com.rizvankarimov.cie_app.entity.Products;
import com.rizvankarimov.cie_app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/api/products/addProducts")
    public ResponseEntity<?> addProducts(@RequestBody Products products) {
        productRepository.save(products);
        return ResponseEntity.ok("Product added successfully.");
    }
}
