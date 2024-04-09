package com.example.spring_boot_jwt_security.service;

import com.example.spring_boot_jwt_security.configuration.jwt.JwtUtils;
import com.example.spring_boot_jwt_security.dto.request.RequestForRegister;
import com.example.spring_boot_jwt_security.model.User;
import com.example.spring_boot_jwt_security.model.enums.Role;
import com.example.spring_boot_jwt_security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {
    private PasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;


    private final JavaMailSender sender;
    private final JwtUtils jwtUtils;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder, JavaMailSender sender, JwtUtils
            jwtUtils) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.sender = sender;
        this.jwtUtils = jwtUtils;
    }

    public ResponseEntity<String> create(RequestForRegister request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException(
                    "this email is alread have in"
            );
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        user.setFullName(request.getName());
        user.setRole(Role.GUEST);
        userRepository.save(user);
        String generatedToken = jwtUtils.generateToken(request.getEmail());
        return ResponseEntity.ok(generatedToken);
    }

    public String randomNumber() {
        Random random = new Random();
        return String.valueOf(random.nextInt(0, 9)) +
                random.nextInt(0, 9) +
                random.nextInt(0, 9) +
                random.nextInt(0, 9) +
                random.nextInt(0, 9) +
                random.nextInt(0, 9);
    }

    public String confirmEmailSender(String email) {
        return "kod sucseful";
    }

    public String confirmEmail(String code) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByEmail(authentication.getName());
        boolean confirming = user.get().getCode().equals(code);
        if (confirming) {
            user.get().setCode(null);
            userRepository.save(user.get());
            return "confirmed";
        } else {
            return "no confirmed";
        }

    }

    public String updateRole(String code) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(RuntimeException::new);
        if (user.getCode().equals(code)) {
            user.setRole(Role.USER);
            userRepository.save(user);
            return "Role izmenen update sucseful";
        }
        throw new RuntimeException("It is code invalid");
    }

    public String forgotPassword(String code, String password) {
        Optional<User> user = userRepository.findByCode(code);
        if (user.get().getCode().equals(code)) {
            user.get().setPassword(bCryptPasswordEncoder.encode(password));
            user.get().setCode(null);
            userRepository.save(user.get());
            return "password sucseful chaened";

        } else {
            return "no sucseful code";
        }
    }
    public String login(String email, String encodedPassword) {
        Optional<User> user = userRepository.findByEmail(email);
        boolean encoder = bCryptPasswordEncoder.matches(encodedPassword,user.get().getPassword());
        if (encoder){
        return jwtUtils.generateToken(email);
        }else {
            return "Exception";
        }
    }
}

