package com.rizvankarimov.cie_app.controller;

import com.rizvankarimov.cie_app.entity.Role;
import com.rizvankarimov.cie_app.service.UserItems;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.rizvankarimov.cie_app.entity.User;
import com.rizvankarimov.cie_app.security.UserPrincipal;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController
{
    private final UserItems userItems;

    @GetMapping("{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id)
    {
        return ResponseEntity.ok(userItems.findUserById(id));
    }

    @PutMapping("change/{role}")
    public ResponseEntity<?> changeRole(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Role role)
    {
        if (userPrincipal != null) {
            userItems.changeRole(role, userPrincipal.getUsername());
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(500).body("UserPrincipal is null");
        }
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userItems.findAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("authUser")
    public ResponseEntity<User> getAuthenticatedUser() {
        //Burada istifadeci adi ve sifresi ile gelen isteklerde istifadeci adini ve sifresini veritabaninda axtarir ve qaytarir
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //Burada eger istifadeci adi ve sifresi ile gelen isteklerde istifadeci adi ve sifresi veritabaninda varsa istifadecini qaytarir
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserPrincipal userPrincipal) {
                Optional<User> user = userItems.findByUsername(userPrincipal.getUsername());
                return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User updatedUser, @PathVariable Long id) {
        try {
            // Istifadecini veritabanından al
            User existingUser = userItems.findUserById(id);

            // "Update" olunmali bölümler kontrol et ve null değilse "update" et"
            if (updatedUser.getUsername() != null) {
                existingUser.setUsername(updatedUser.getUsername());
            }
            if (updatedUser.getPhone() != null) {
                existingUser.setPhone(updatedUser.getPhone());
            }
            if (updatedUser.getEmail() != null) {
                existingUser.setEmail(updatedUser.getEmail());
            }

            // "Update" olunmus istifadecini qeyde
            userItems.updateUser(existingUser);

            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user");
        }
    }
}
