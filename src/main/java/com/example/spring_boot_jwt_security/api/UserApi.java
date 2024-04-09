package com.example.spring_boot_jwt_security.api;


import com.example.spring_boot_jwt_security.configuration.jwt.JwtUtils;
import com.example.spring_boot_jwt_security.dto.request.RequestForRegister;
import com.example.spring_boot_jwt_security.model.User;
import com.example.spring_boot_jwt_security.repository.UserRepository;
import com.example.spring_boot_jwt_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserApi {

    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Autowired
    public UserApi(JwtUtils jwtUtils, UserRepository userRepository, PasswordEncoder passwordEncoder, UserService userService) {
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RequestForRegister request) {
        try {
            // Сохраняем данные пользователя в базе данных
            ResponseEntity<String> registrationResponse = userService.create(request);
            if (registrationResponse.getStatusCode().is2xxSuccessful()) {
                // Если регистрация успешна, отправляем код подтверждения на указанный email
                String email = request.getEmail();
                String code = userService.confirmEmailSender(email); // Метод confirmEmailSender теперь также сохраняет код в базу данных
                if (code != null && !code.isEmpty()) {
                    // Если код успешно отправлен, генерируем и возвращаем токен
                    String generatedToken = jwtUtils.generateToken(email); // Реализуйте логику генерации токена
                    return ResponseEntity.ok(generatedToken);
                } else {
                    return ResponseEntity.badRequest().body("Failed to send verification code to email: " + email);
                }
            } else {
                return registrationResponse; // Возвращаем ответ от userService.create()
            }
        } catch (Exception e) {
            e.printStackTrace();
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
    public String login(
            @RequestParam String email,
            @RequestParam String password) {
        return userService.login(email,password);
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
