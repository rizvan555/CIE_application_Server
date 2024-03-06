package com.rizvankarimov.cie_app.repository;


import com.rizvankarimov.cie_app.entity.JwtRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtRefreshTokenRepository extends JpaRepository<JwtRefreshToken, String>
{
}
