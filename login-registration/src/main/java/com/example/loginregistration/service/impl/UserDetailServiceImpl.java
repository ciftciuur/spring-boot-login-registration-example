package com.example.loginregistration.service.impl;

import com.example.loginregistration.details.CurrentUserDetail;
import com.example.loginregistration.model.User;
import com.example.loginregistration.repository.UserRepository;
import com.example.loginregistration.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailServiceImpl implements UserDetailService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s);
        if (user == null) throw new UsernameNotFoundException(s);
        else {
            CurrentUserDetail currentUserDetail = new CurrentUserDetail(user);
            return org.springframework.security.core.userdetails.User.builder()
                    .username(currentUserDetail.getUsername())
                    //change here to store encoded password in db
                    .password(bCryptPasswordEncoder.encode(currentUserDetail.getPassword()))
                    .disabled(currentUserDetail.isEnabled())
                    .accountExpired(currentUserDetail.isAccountNonExpired())
                    .accountLocked(currentUserDetail.isAccountNonLocked())
                    .credentialsExpired(currentUserDetail.isCredentialsNonExpired())
                    .roles(currentUserDetail.getAuthorities().toString())
                    .build();
        }
    }
}
