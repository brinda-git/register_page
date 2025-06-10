package com.example.register_page.dto;

public class Login {
    String email;
    String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public Login() {
    }

}
