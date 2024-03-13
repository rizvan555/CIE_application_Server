package com.rizvankarimov.cie_app.controller;


import com.rizvankarimov.cie_app.entity.Products;
import com.rizvankarimov.cie_app.service.UserItems;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final UserItems userItems;

    @PostMapping("addProducts")
    public ResponseEntity<String> addProducts(@RequestBody Products products) {
        userItems.addService(products);
        return ResponseEntity.ok("Product added successfully");
    }

    @GetMapping("allProducts")
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(userItems.getAllProducts());
    }

    @GetMapping("allProducts/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(userItems.getProductById(id));
    }

    @PutMapping("updateProducts/{id}")
    public ResponseEntity<?> updateProducts(@PathVariable Long id){
        return ResponseEntity.ok(userItems.updateProducts(id));
    }
}
