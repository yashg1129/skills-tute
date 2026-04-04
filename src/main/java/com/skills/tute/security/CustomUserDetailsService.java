package com.skills.tute.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private String token;

    @Autowired
    private JwtService jwtService;

//    private final UserRepository userRepository;
//
//    public CustomUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        String role = jwtService.extractRole(token);

        List<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("ROLE_" + role)
        );

        return new org.springframework.security.core.userdetails.User(
                email,
                null,
                authorities
        );

//        return new org.springframework.security.core.userdetails.User(
//                email,
//                user.getPassword(),
//                authorities
//        );
    }

    public void setToken(String token) {
        this.token = token;
    }
}