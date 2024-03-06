package com.rizvankarimov.cie_app.controller;


import com.rizvankarimov.cie_app.service.UserItems;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/userServices")
public class UserServicesController {

private final UserItems userItems;
    @PostMapping("addUserServices")
    public ResponseEntity<String> addUserServices(@RequestBody UserItems userItems) {
        userItems.addUserServices(userItems);
        return ResponseEntity.ok("Service added successfully");
    }
}
