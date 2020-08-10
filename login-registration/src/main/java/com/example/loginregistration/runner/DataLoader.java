package com.example.loginregistration.runner;

import com.example.loginregistration.model.Role;
import com.example.loginregistration.model.User;
import com.example.loginregistration.repository.RoleRepository;
import com.example.loginregistration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


public class DataLoader implements ApplicationRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user = new User();
        user.setPassword("123");
        user.setEmail("test@mail");
        user.setLastName("test user lastname");
        user.setName("test user user");

        Set<User> users = new HashSet<>();
        users.add(user);
        Role role = new Role();
        role.setName("member");
        role.setUsers(users);

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        userRepository.save(user);
        roleRepository.save(role);
    }
}
