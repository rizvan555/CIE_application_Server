package com.rizvankarimov.cie_app.security;

import com.rizvankarimov.cie_app.entity.Company;
import com.rizvankarimov.cie_app.entity.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class CompanyPrincipal implements UserDetails {
        private Long id;
        private String username;
        private String password;
        private String phone;
        private String company_name;
        private Company company;
        private String address;
        private String email;
        private String accessToken;
        private Set<GrantedAuthority> authorities;

        public CompanyPrincipal(Company testUser) {
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
        }

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
