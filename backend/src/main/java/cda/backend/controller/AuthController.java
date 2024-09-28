package cda.backend.controller;

import cda.backend.model.User;
import cda.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.status(201).body("User registered successfully!"); // 201 Created
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        boolean success = userService.authenticateUser(user.getUsername(), user.getPassword());
        return success ? ResponseEntity.ok("Login successful!") : ResponseEntity.status(401).body("Login failed!"); // 401 Unauthorized
    }
}
