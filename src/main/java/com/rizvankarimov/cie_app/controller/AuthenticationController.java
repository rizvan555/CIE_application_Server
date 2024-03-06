package com.rizvankarimov.cie_app.controller;

import com.rizvankarimov.cie_app.entity.User;
import com.rizvankarimov.cie_app.security.jwt.JwtProvider;
import com.rizvankarimov.cie_app.service.AuthenticationService;
import com.rizvankarimov.cie_app.service.JwtRefreshTokenService;
import com.rizvankarimov.cie_app.service.UserItems;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/authentication")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserItems userService;
    private final JwtRefreshTokenService jwtRefreshTokenService;
    private final JwtProvider jwtProvider;

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return new ResponseEntity<>(authenticationService.signInAndReturnJWT(user), HttpStatus.OK);
    }

    @PostMapping("refresh-token")
    public ResponseEntity<?> refreshToken(@RequestParam String token) {
        return ResponseEntity.ok(jwtRefreshTokenService.generateAccessTokenFromRefreshToken(token));
    }

    @GetMapping("logout")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }
}



