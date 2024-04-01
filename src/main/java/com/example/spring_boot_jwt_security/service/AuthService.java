package com.example.spring_boot_jwt_security.service;

import com.example.spring_boot_jwt_security.configuration.jwt.JwtUtils;
import com.example.spring_boot_jwt_security.dto.request.AuthRequest;
import com.example.spring_boot_jwt_security.dto.request.LoginRequest;
import com.example.spring_boot_jwt_security.dto.response.JWTResponse;
import com.example.spring_boot_jwt_security.model.User;
import com.example.spring_boot_jwt_security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public JWTResponse registerUser(AuthRequest userRegisterRequest) {
        User user = new User( );
        user.setFullName( userRegisterRequest.getName() );
        user.setEmail( userRegisterRequest.getEmail() );
        user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));

        if (repository.existsByEmail(userRegisterRequest.getEmail()))
            throw new RuntimeException("The email " + userRegisterRequest.getEmail() + " is already in use!");

        User savedUser = repository.save(user);
        String token = jwtUtils.generateToken(userRegisterRequest.getEmail());

        return new JWTResponse(
                savedUser.getEmail(),
                token,
                "Azamat",
                savedUser.getRole()

        );
}
    public JWTResponse authenticate(LoginRequest loginRequest) {
        User user = repository.findByEmail(loginRequest.getEmail()).orElseThrow(() ->
                new RuntimeException("User with email: " + loginRequest.getEmail() + " not found!"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        String token = jwtUtils.generateToken(user.getEmail());
        return new JWTResponse(
                user.getEmail(),
                token,
                "Azamat",
                user.getRole()

        );
    }
}