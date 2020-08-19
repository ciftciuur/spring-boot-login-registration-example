package com.example.loginregistration.service;

import com.example.loginregistration.model.User;
import com.example.loginregistration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = userRepository.findByEmail(s);
        System.out.println(user.getEmail());
        System.out.println("ttttttt");
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
        System.out.println("size " + grantedAuthorities.size());
        System.out.println(user.getPassword());
        System.out.println(encoder.encode(user.getPassword()));
        System.out.println(user.getRole());
        if (user == null)
            throw new UsernameNotFoundException("Invalid username");

        UserDetails details = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);

        return details;
    }
}
