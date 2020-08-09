package com.example.loginregistration.service;

import com.example.loginregistration.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    void save(User user);

    User findByEmail(String mail);

    public void autoLogin(String username, String password);
}
