package com.school.dashboard.controller;

import com.school.dashboard.model.User;
import com.school.dashboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DashboardController {

    @Autowired
    private UserRepository repo;

    @GetMapping("/dashboard")
    public String dashboard(Authentication auth) {
        User user = repo.findByUsername(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return "Welcome " + user.getUsername();
    }
}
