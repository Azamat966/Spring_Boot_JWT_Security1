package com.example.spring_boot_jwt_security.api;


import com.example.spring_boot_jwt_security.configuration.jwt.JwtUtils;
import com.example.spring_boot_jwt_security.dto.request.RequestForRegister;
import com.example.spring_boot_jwt_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        @RestController
        @RequestMapping("/api/users")
        public class UserApi {

            private final JwtUtils jwtUtils;

            private final UserService userService;

            @Autowired
            public UserApi(JwtUtils jwtUtils, UserService userService) {
                this.jwtUtils = jwtUtils;
                this.userService = userService;
            }

            @PostMapping("/register")
            public ResponseEntity<?> registerUser(@RequestBody RequestForRegister request) {
                try {
                    ResponseEntity<String> registrationResponse = userService.create(request);
                    if (registrationResponse.getStatusCode().is2xxSuccessful()) {
                        // If registration is successful, send confirmation code to the provided email
                        String email = request.getEmail();
                        String code = userService.confirmEmailSender(email);
                        if (code != null && !code.isEmpty()) {
                            // If code is sent successfully, generate and return a token
                            String generatedToken = jwtUtils.generateToken(email); // Implement token generation logic
                            return ResponseEntity.ok(generatedToken);
                        } else {
                            return ResponseEntity.badRequest().body("Failed to send verification code to email: " + email);
                        }
                    } else {
                        return registrationResponse; // Return the response from userService.create()
                    }

                } catch (Exception e) {
                    e.printStackTrace(); // Логируем возможные ошибки для дальнейшего анализа
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
                }
            }



            @PutMapping("/confirm-email")
            public ResponseEntity<String> confirmEmail(@RequestParam String code) {
                String result = userService.updateRole(code);
                if (result.equals("Role izmenen update sucseful")) {
                    return ResponseEntity.ok("Email confirmed successfully");
                } else {
                    return ResponseEntity.badRequest().body("Invalid confirmation code");
                }
            }

            @PutMapping("/update-password")
            public ResponseEntity<String> updatePassword(
                    @RequestParam String code,
                    @RequestParam String newPassword) {
                return ResponseEntity.ok(userService.forgotPassword(code, newPassword));
            }

            @PostMapping("/login")
            public ResponseEntity<String> login(
                    @RequestParam String email,
                    @RequestParam String password) {
                // Your login logic here, provide token upon successful login
                // For simplicity, return a dummy token
                String generatedToken = jwtUtils.generateToken(email);
                return ResponseEntity.ok(generatedToken);
            }

            @GetMapping("/send-code-for-update-password")
            public ResponseEntity<String> sendCodeForUpdatePassword() {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String email = authentication.getName(); // Implement logic to get authenticated user's email
                if (email != null && !email.isEmpty()) {
                    String code = userService.confirmEmailSender(email);
                    return ResponseEntity.ok("Verification code sent to email: " + email);
                } else {
                    return ResponseEntity.badRequest().body("Failed to send verification code: user email not found");
                }
            }
        }
