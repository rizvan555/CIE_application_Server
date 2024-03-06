package com.rizvankarimov.cie_app.service;


import com.rizvankarimov.cie_app.entity.JwtRefreshToken;
import com.rizvankarimov.cie_app.entity.User;

public interface JwtRefreshTokenService
{
    JwtRefreshToken createRefreshToken(Long userId);

    User generateAccessTokenFromRefreshToken(String refreshTokenId);
}
