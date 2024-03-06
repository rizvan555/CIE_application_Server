package com.rizvankarimov.cie_app.controller;


import com.rizvankarimov.cie_app.entity.My_Items;
import com.rizvankarimov.cie_app.service.UserItems;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/services")
public class ServiceController {

    private final UserItems userItems;

    @PostMapping("addServices")
    public ResponseEntity<String> addService(@RequestBody My_Items myItems) {
        userItems.addService(myItems);
        return ResponseEntity.ok("Service added successfully");
    }

    @GetMapping("allServices")
    public ResponseEntity<?> getAllServices() {
        return ResponseEntity.ok(userItems.getAllServices());
    }

    @GetMapping("allServices/{id}")
    public ResponseEntity<?> getServiceById(@PathVariable Long id) {
        return ResponseEntity.ok(userItems.getServiceById(id));
    }


    @PutMapping("updateService/{id}")
    public ResponseEntity<?> updateService(@PathVariable Long id){
        return ResponseEntity.ok(userItems.updateService(id));
    }
}
