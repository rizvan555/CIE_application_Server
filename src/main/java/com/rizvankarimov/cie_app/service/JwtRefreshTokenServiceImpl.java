package com.rizvankarimov.cie_app.service;

import com.rizvankarimov.cie_app.entity.JwtRefreshToken;
import com.rizvankarimov.cie_app.entity.User;
import com.rizvankarimov.cie_app.repository.JwtRefreshTokenRepository;
import com.rizvankarimov.cie_app.repository.UserRepository;
import com.rizvankarimov.cie_app.security.jwt.JwtProvider;
import com.rizvankarimov.cie_app.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
import com.rizvankarimov.cie_app.security.UserPrincipal;




@Service
@RequiredArgsConstructor
public class JwtRefreshTokenServiceImpl implements JwtRefreshTokenService
{
    @Value("${app.jwt.refresh-expiration-in-ms}")
    private Long REFRESH_EXPIRATION_IN_MS;

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final JwtRefreshTokenRepository jwtRefreshTokenRepository;

    @Override
    public JwtRefreshToken createRefreshToken(Long userId)
    {
        JwtRefreshToken jwtRefreshToken = new JwtRefreshToken();

        jwtRefreshToken.setTokenId(UUID.randomUUID().toString());
        jwtRefreshToken.setUserId(userId);
        jwtRefreshToken.setCreateDate(LocalDateTime.now());
        jwtRefreshToken.setExpirationDate(LocalDateTime.now().plus(REFRESH_EXPIRATION_IN_MS, ChronoUnit.MILLIS));

        return jwtRefreshTokenRepository.save(jwtRefreshToken);
    }
    
    @Override
    public User generateAccessTokenFromRefreshToken(String refreshTokenId) {
        JwtRefreshToken jwtRefreshToken = jwtRefreshTokenRepository.findById(refreshTokenId)
                .orElseThrow(() -> new RuntimeException("JWT-Auffrischungstoken ist ungültig."));

        if (jwtRefreshToken.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("JWT-Auffrischungstoken ist nicht gültig.");
        }

        User user = userRepository.findById(jwtRefreshToken.getUserId())
                .orElseThrow(() -> new RuntimeException("Benutzer nicht gefunden."));

        UserPrincipal userPrincipal = UserPrincipal.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(Set.of(SecurityUtils.convertToAuthority(Arrays.toString(user.getRole().name().toCharArray()))))
                .build();

        String accessToken = jwtProvider.generateToken(userPrincipal);

        user.setAccessToken(accessToken);
        user.setRefreshToken(refreshTokenId);

        return user;
    }

}