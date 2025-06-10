package com.example.register_page.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Register {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String confirmpassword;
    private String phonenumber;
    private String bloodGroup;
    private String role;
    private String address;
    private String permanentAddress;
    private String otp;
    private LocalDateTime otpExpiryTime;

    // Added fields for password reset functionality
    private String resetToken;
    private LocalDateTime resetTokenExpiry;

    public Register() {
    }

    // All-args constructor (including new fields)
    public Register(String id, String name, String email, String password, String confirmpassword,
                    String phonenumber, String bloodGroup, String role, String address,
                    String permanentAddress, String otp, LocalDateTime otpExpiryTime,
                    String resetToken, LocalDateTime resetTokenExpiry) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmpassword = confirmpassword;
        this.phonenumber = phonenumber;
        this.bloodGroup = bloodGroup;
        this.role = role;
        this.address = address;
        this.permanentAddress = permanentAddress;
        this.otp = otp;
        this.otpExpiryTime = otpExpiryTime;
        this.resetToken = resetToken;
        this.resetTokenExpiry = resetTokenExpiry;
    }

    // Getters and setters

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }
    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public String getPhonenumber() {
        return phonenumber;
    }
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }
    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getOtp() {
        return otp;
    }
    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getOtpExpiryTime() {
        return otpExpiryTime;
    }
    public void setOtpExpiryTime(LocalDateTime otpExpiryTime) {
        this.otpExpiryTime = otpExpiryTime;
    }

    // New getters and setters for resetToken and resetTokenExpiry
    public String getResetToken() {
        return resetToken;
    }
    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public LocalDateTime getResetTokenExpiry() {
        return resetTokenExpiry;
    }
    public void setResetTokenExpiry(LocalDateTime resetTokenExpiry) {
        this.resetTokenExpiry = resetTokenExpiry;
    }
}