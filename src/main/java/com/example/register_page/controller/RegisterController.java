package com.example.register_page.controller;

import com.example.register_page.model.Register;
import com.example.register_page.repository.RegisterRepository;
import com.example.register_page.service.EmailService;
import com.example.register_page.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class RegisterController {

    @Value("${app.frontend.url}")
    private String frontendUrl;

    @Autowired
    RegisterService registerService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RegisterRepository registerRepository;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

//    @PostMapping("/forgot-password")
//    public ResponseEntity<String> fforgotPassword(@RequestParam String email) {
//        Optional<Register> userOptional = registerRepository.findByEmail(email);
//        if (userOptional.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//        }
//
//        Register user = userOptional.get();
//
//        // Generate 6-digit OTP
//        String otp = String.valueOf(100000 + new Random().nextInt(900000));
//        user.setOtp(otp);
//        user.setOtpExpiryTime(LocalDateTime.now().plusMinutes(5));
//
//        registerRepository.save(user);
//
//        // Send OTP to email
//        emailService.sendOtpEmail(user.getEmail(), otp);
//
//        return ResponseEntity.ok("OTP sent to your email");
//    }

//    @PostMapping("/forgot-password")
//    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
//        String otp;
//
//
//        try {
//            Optional<Register> userOptional = registerRepository.findByEmail(email);
//            if (userOptional.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//            }
//
//            Register user = userOptional.get();
//            otp = String.valueOf(100000 + new Random().nextInt(900000));
//            user.setOtp(otp);
//            System.out.println("Generated OTP: " + otp);
//
//            user.setOtpExpiryTime(LocalDateTime.now().plusMinutes(5));
//            registerRepository.save(user);
//
//            emailService.sendOtpEmail(user.getEmail(), otp);
//
//            return ResponseEntity.ok("OTP sent to your email");
//        } catch (Exception e) {
//            e.printStackTrace(); // this prints error in console
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
//        }
//
//    }
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        try {
            Optional<Register> userOptional = registerRepository.findByEmail(email);
            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            Register user = userOptional.get();
            String otp = String.valueOf(100000 + new Random().nextInt(900000));
            System.out.println("Generated OTP: " + otp); // ✅ Now it's safe

            user.setOtp(otp);
            user.setOtpExpiryTime(LocalDateTime.now().plusMinutes(5));
            registerRepository.save(user);

            emailService.sendOtpEmail(user.getEmail(), otp);

            return ResponseEntity.ok("OTP sent to your email");
        } catch (Exception e) {
            e.printStackTrace(); // This prints error in console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }


    @PostMapping("/register")
    public ResponseEntity<String> createRegister(@RequestBody Register register) {

        if (registerRepository.existsById(register.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("User with ID " + register.getId() + " already exists");
        }

        if (!register.getPassword().equals(register.getConfirmpassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Password and Confirm Password do not match");
        }

        registerRepository.save(register);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Registration successful for ID: " + register.getId());

    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String email,
                                                @RequestParam String otp,
                                                @RequestParam String newPassword) {
        Optional<Register> userOptional = registerRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        Register user = userOptional.get();

        if (user.getOtp() == null || user.getOtpExpiryTime() == null ||
                !user.getOtp().equals(otp) ||
                user.getOtpExpiryTime().isBefore(LocalDateTime.now())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired OTP");
        }

        // Reset password
        user.setPassword(newPassword); // Ideally hash it!
        user.setOtp(null);
        user.setOtpExpiryTime(null);
        registerRepository.save(user);

        return ResponseEntity.ok("Password reset successfully");
    }


    @GetMapping
    public List<Register> getAllRegisters() {
        return registerRepository.findAll();  // This returns List<Register>
    }

}


//@RestController
//@RequestMapping("/api")
//@CrossOrigin(origins="*")
//@Value("${app.frontend.url}")
//private String frontendUrl;
//public class RegisterController {
//    @Autowired
//    //created
//    RegisterService registerService;
//
//    @Autowired
//    private EmailService emailService;
//
//
//
//    public RegisterController(RegisterService registerService) {
//        this.registerService = registerService;
//    }
//
//    @Autowired
//    private RegisterRepository registerRepository;
//
//    //    private static final List<Register> users = new ArrayList<>();
////
////    static {
////        // Adding some dummy data to the list
////        users.add(new Register("user1", "a", "a@a", "a1", "a1"));
////        users.add(new Register("user2", "b", "b@b", "b@b", "b1"));
////        users.add(new Register("user3", "c", "c@c", "c@c", "c1"));
////        users.add(new Register("user4", "d", "d@d", "d@d", "d1"));
////        users.add(new Register("user5", "e", "e@e", "e@e", "e1"));
////    }
//
////    @GetMapping("/{id}")
////    public ResponseEntity<Register> getRegisterDetails(@PathVariable String id) {
////        Optional<Register> user = users.stream()
////                .filter(register -> register.getId().equals(id))
////                .findFirst();
////
////        if (user.isPresent()) {
////            return ResponseEntity.ok(user.get());
////        } else {
////            return ResponseEntity.notFound().build();
////        }
////    }
//
////    @PostMapping
////    public ResponseEntity<String> createRegister1(@RequestBody Register register) {
////        // Check if user with same ID already exists
////        boolean exists = users.stream()
////                .anyMatch(existing -> existing.getId().equals(register.getId()));
////
////        if (exists) {
////            return ResponseEntity.status(HttpStatus.CONFLICT)
////                    .body("User with ID " + register.getId() + " already exists");
////        }
////
////        users.add(register);
////        return ResponseEntity.status(HttpStatus.CREATED)
////                .body("Registration successful for ID: " + register.getId());
////    }
//
//
//    //    @GetMapping
////    public List<Register> getAllRegisters() {
////        return new ArrayList<>(register);
////        }
//    @GetMapping
//    public List<Register> getAllRegisters() {
//        return registerRepository.findAll();  // This returns List<Register>
//    }
//
//
//
//
////    @PostMapping("/forgot-password")
////    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
////        Optional<Register> userOptional = registerRepository.findByEmail(email);
////        if (userOptional.isEmpty()) {
////            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
////        }
////
////        Register user = userOptional.get();
////        String token = UUID.randomUUID().toString();
////        user.setResetToken(token);
////        user.setTokenExpiryTime(LocalDateTime.now().plusMinutes(15));
////        registerRepository.save(user);
////
////        String resetLink = "http://localhost:3000/reset-password?token=" + token;
////        emailService.sendResetEmail(user.getEmail(), resetLink);
////
////        return ResponseEntity.ok("Reset link sent to your email.");
////    }
//
////    @PostMapping("/reset-password")
////    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
////        Optional<Register> userOptional = registerRepository.findByResetToken(token);
////        if (userOptional.isEmpty()) {
////            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token.");
////        }
////
////        Register user = userOptional.get();
////        if (user.getTokenExpiryTime().isBefore(LocalDateTime.now())) {
////            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token expired.");
////        }
////
////        user.setPassword(newPassword); // Encrypt if needed
////        user.setResetToken(null);
////        user.setTokenExpiryTime(null);
////        registerRepository.save(user);
////
////        return ResponseEntity.ok("Password reset successfully.");
////    }
////    @PostMapping("/reset-password")
////    public ResponseEntity<String> rresetPassword(@RequestParam String token, @RequestParam String newPassword) {
////        Optional<Register> userOptional = registerRepository.findByResetToken(token);
////        if (userOptional.isEmpty()) {
////            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token.");
////        }
////
////        Register user = userOptional.get();
////        if (user.getTokenExpiryTime().isBefore(LocalDateTime.now())) {
////            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token expired.");
////        }
////
////        user.setPassword(passwordEncoder.encode(newPassword));  // Encrypt password
////        user.setResetToken(null);
////        user.setTokenExpiryTime(null);
////        registerRepository.save(user);
////
////        return ResponseEntity.ok("Password reset successfully.");
////}
//@PostMapping("/forgot-password")
//public ResponseEntity<String> forgotPassword(@RequestParam String email) {
//    Optional<Register> userOptional = registerRepository.findByEmail(email);
//    if (userOptional.isEmpty()) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//    }
//
//    Register user = userOptional.get();
//    String token = UUID.randomUUID().toString();
//    user.setResetToken(token);
//    user.setTokenExpiryTime(LocalDateTime.now().plusMinutes(15));
//    registerRepository.save(user);
//
//    String resetLink = frontendUrl + "/reset-password?token=" + token;
//
//    emailService.sendResetEmail(user.getEmail(), resetLink);
//
//    return ResponseEntity.ok("Reset link sent to your email.");
//}
//
//}
//










