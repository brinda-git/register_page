package com.example.register_page.service;

import com.example.register_page.model.Register;

import java.util.Optional;

public interface LoginService {
    Optional<Register> login(String email, String password);
}

