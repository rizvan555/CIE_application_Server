package com.rizvankarimov.cie_app.service;

import com.rizvankarimov.cie_app.entity.Company;
import com.rizvankarimov.cie_app.entity.User;
import com.rizvankarimov.cie_app.repository.UserRepository;
import com.rizvankarimov.cie_app.security.CompanyPrincipal;
import com.rizvankarimov.cie_app.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final JwtRefreshTokenService jwtRefreshTokenService;
    private final UserRepository userRepository;

    @Override
    public Object signInAndReturnJWT(Object signInRequest) {
        if (signInRequest instanceof User) {
            return signInAndReturnJWT((User) signInRequest);
        } else if (signInRequest instanceof Company) {
            return signInAndReturnJWT((Company) signInRequest);
        } else {
            throw new RuntimeException("Geçersiz nesne türü girişi");
        }
    }

    public User signInAndReturnJWT(User signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
        );
        com.rizvankarimov.cie_app.security.UserPrincipal userPrincipal = (com.rizvankarimov.cie_app.security.UserPrincipal) authentication.getPrincipal();
        String jwt = jwtProvider.generateToken(userPrincipal);
        User signInUser = userPrincipal.getUser();
        signInUser.setAccessToken(jwt);
        signInUser.setRefreshToken(jwtRefreshTokenService.createRefreshToken(signInUser.getId()).getTokenId());
        return signInUser;
    }

    public Company signInAndReturnJWT(Company signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
        );
        com.rizvankarimov.cie_app.security.CompanyPrincipal companyPrincipal = (CompanyPrincipal) authentication.getPrincipal();
        String jwt = jwtProvider.generateTokenCompany(companyPrincipal);
        Company signInCompany = companyPrincipal.getCompany();
        signInCompany.setAccessToken(jwt);
        signInCompany.setRefreshToken(jwtRefreshTokenService.createRefreshToken(signInCompany.getId()).getTokenId());
        return signInCompany;
    }


    @Override
    public Object findUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }
}
