package com.rizvankarimov.cie_app.security.jwt;


import com.rizvankarimov.cie_app.security.CompanyPrincipal;
import com.rizvankarimov.cie_app.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface JwtProvider
{
    String generateToken(UserPrincipal auth);

    String generateTokenCompany(CompanyPrincipal auth);

    String resolveToken(HttpServletRequest request);
    Authentication getAuthentication(HttpServletRequest request);

    boolean isTokenValid(HttpServletRequest request);
}
