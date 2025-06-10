package com.example.register_page.service.impl;

import com.example.register_page.model.Register;
import com.example.register_page.repository.RegisterRepository;
import com.example.register_page.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterRepository registerRepository;



    @Override
    public String createRegister(Register register) {
        // Placeholder logic – replace with your real registration logic
        return "User " + register.getName() + " registered successfully!";
    }




}
