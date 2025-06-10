package com.example.register_page.service.service.impl;

import com.example.register_page.model.Register;
import com.example.register_page.repository.RegisterRepository;
import com.example.register_page.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RegisterRepository registerRepository;

    @Override
    public Optional<Register> login(String email, String password) {
        return registerRepository.findByEmailAndPassword(email, password);
    }
}

