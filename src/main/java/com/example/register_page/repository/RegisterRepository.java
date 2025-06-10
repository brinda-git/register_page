package com.example.register_page.repository;

import com.example.register_page.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisterRepository extends JpaRepository<Register, String> {
    //Optional<Register> findByEmailAndPassword(String email, String password);
    Optional<Register> findByEmailAndPassword(String email, String password);

    Optional<Register> findByEmail(String email);

    Optional<Register> findByResetToken(String resetToken);


}
