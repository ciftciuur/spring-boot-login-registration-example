package com.example.loginregistration.service;

import com.example.loginregistration.service.impl.UserDetailServiceImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserDetailService extends UserDetailsService {
}
