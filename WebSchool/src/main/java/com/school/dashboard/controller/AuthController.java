package com.school.dashboard.controller;

import com.school.dashboard.model.User;
import com.school.dashboard.repository.UserRepository;
import com.school.dashboard.service.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

record LoginRequest(String username, String password) {}
record JwtResponse(String token) {}

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = repo.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (encoder.matches(request.password(), user.getPassword())) {
            String token = jwtUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(new JwtResponse(token));
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
