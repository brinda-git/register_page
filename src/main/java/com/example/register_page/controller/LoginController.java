package com.example.register_page.controller;

import com.example.register_page.dto.Login;
import com.example.register_page.model.Register;
import com.example.register_page.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // allows frontend to access backend
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login loginRequest) {
        Optional<Register> user = loginService.login(
                loginRequest.getEmail(), loginRequest.getPassword()
        );

        if (user.isPresent()) {
            return ResponseEntity.ok("Login successful for: " + user.get().getName());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password");
        }
    }
}

