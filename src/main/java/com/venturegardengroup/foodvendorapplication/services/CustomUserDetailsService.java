package com.venturegardengroup.foodvendorapplication.services;

import com.venturegardengroup.foodvendorapplication.models.Auth;
import com.venturegardengroup.foodvendorapplication.repositories.AuthRepository;
import com.venturegardengroup.foodvendorapplication.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Auth userAuth = authRepository.findByEmail(email);
        String role = userAuth.getRoleId().getName();

        if (userAuth == null) {
            throw new UsernameNotFoundException("No such email: " + email);
        }

        return new org.springframework.security.core.userdetails.User(
                userAuth.getEmail(),
                userAuth.getPassword(),
                true,
                true,
                true,
                true,
//                Arrays.asList(new SimpleGrantedAuthority("USER")));
                Arrays.asList(new SimpleGrantedAuthority(role)));
    }
}
