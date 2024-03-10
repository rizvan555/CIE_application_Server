package com.rizvankarimov.cie_app.service;


import com.rizvankarimov.cie_app.entity.Company;
import com.rizvankarimov.cie_app.entity.User;

public interface AuthenticationService
{

    User signInAndReturnJWT(User signInRequest);

    Company signInAndReturnJWT(Company signInRequest);

    Object findUserById(long id);
}