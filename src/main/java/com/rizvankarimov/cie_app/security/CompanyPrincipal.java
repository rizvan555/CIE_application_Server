package com.rizvankarimov.cie_app.security;

import com.rizvankarimov.cie_app.entity.Company;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class CompanyPrincipal extends User {
    private final Company company;

    public CompanyPrincipal(String username, String password, Collection<? extends GrantedAuthority> authorities, Company company) {
        super(username, password, authorities);
        this.company = company;
    }
}
