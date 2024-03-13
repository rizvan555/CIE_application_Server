package com.rizvankarimov.cie_app.security;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductsPrincipal implements UserDetails {
    private Long id;
    private String company;
    private String brand;
    private String model;
    private String weight;
    private String image;
    private String halal;
    private String vegan;
    private String vegetarian;
    private String alcohol;
    private String allergic;
    private Set<GrantedAuthority> authorities;

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
