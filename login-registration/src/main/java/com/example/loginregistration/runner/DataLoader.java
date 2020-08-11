package com.example.loginregistration.runner;

import com.example.loginregistration.model.User;
import com.example.loginregistration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.HashSet;
import java.util.Set;


public class DataLoader implements ApplicationRunner {
    @Autowired
    private UserRepository userRepository;



    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user = new User();
        user.setPassword("123");
        user.setEmail("test@mail");
        user.setLastName("test user lastname");
        user.setName("test user user");

        userRepository.save(user);
    }
}
