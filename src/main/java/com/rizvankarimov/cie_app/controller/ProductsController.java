package com.rizvankarimov.cie_app.controller;

import com.rizvankarimov.cie_app.entity.Products;
import com.rizvankarimov.cie_app.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/api/products/lastUsedId")
    public ResponseEntity<Long> getLastUsedId() {
        Iterable<Products> allProducts = productRepository.findAll();
        long lastUsedId = 0;
        for (Products product : allProducts) {
            if (product.getId() > lastUsedId) {
                lastUsedId = product.getId();
            }
        }
        return ResponseEntity.ok(lastUsedId);
    }

    @PostMapping("/api/products/addProducts")
    public ResponseEntity<?> addOrUpdateProducts(@RequestBody Products products) {
        Optional<Products> existingProductOptional = productRepository.findById(products.getId());

        if (existingProductOptional.isPresent()) {
            return ResponseEntity.badRequest().body("The product already exists, it cannot be updated.");
        } else {
            productRepository.save(products);
            return ResponseEntity.ok("The product was added successfully.");
        }
    }
}
