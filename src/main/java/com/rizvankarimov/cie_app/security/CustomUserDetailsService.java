package com.rizvankarimov.cie_app.security;


import com.rizvankarimov.cie_app.entity.User;
import com.rizvankarimov.cie_app.service.UserItems;
import com.rizvankarimov.cie_app.utils.SecurityUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserItems userItems;

    public CustomUserDetailsService(@Lazy UserItems userItems) {
        this.userItems = userItems;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userItems.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        Set<GrantedAuthority> authorities = Set.of(SecurityUtils.convertToAuthority(Arrays.toString(user.getRole().name().toCharArray())));

        return UserPrincipal.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .user(user)
                .email(user.getEmail())
                .accessToken(user.getAccessToken())
                .authorities(authorities)
                .build();
    }


}
