package com.example.register_page.service;

public interface EmailService {
        //void sendResetEmail(String toEmail, String resetLink);
        void sendOtpEmail(String toEmail, String otp);

}

