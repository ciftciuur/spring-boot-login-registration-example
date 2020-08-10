package com.example.loginregistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication
@EnableJpaRepositories("com.example.loginregistration.repository")
public class LoginRegistrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginRegistrationApplication.class, args);
    }


}
