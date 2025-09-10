package com.school.dashboard.config;

import com.school.dashboard.model.User;
import com.school.dashboard.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.findByUsername("admin").isEmpty()) {
                repo.save(new User("admin", encoder.encode("admin123"), "2023-24"));
            }
            if (repo.findByUsername("student").isEmpty()) {
                repo.save(new User("student", encoder.encode("password"), "2024-25"));
            }
        };
    }
}
