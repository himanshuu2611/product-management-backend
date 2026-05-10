package com.himanshu.Product.Management.System.service.impl;

import com.himanshu.Product.Management.System.dto.AuthResponse;
import com.himanshu.Product.Management.System.dto.LoginRequest;
import com.himanshu.Product.Management.System.dto.RegisterRequest;
import com.himanshu.Product.Management.System.entity.User;
import com.himanshu.Product.Management.System.repository.UserRepository;
import com.himanshu.Product.Management.System.security.JwtUtil;
import com.himanshu.Product.Management.System.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {

            throw new RuntimeException(
                    "Email already exists"
            );
        }

        User user = User.builder()

                .name(request.getName())

                .email(request.getEmail())

                .password(
                        passwordEncoder.encode(
                                request.getPassword()
                        )
                )

                // IMPORTANT
                .role("USER")

                .build();

        userRepository.save(user);

        String token = jwtUtil.generateToken(
                user.getEmail()
        );

        return new AuthResponse(
                token,
                user.getRole()
        );
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(
                        request.getEmail()
                )
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found"
                        )
                );

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {

            throw new RuntimeException(
                    "Invalid password"
            );
        }

        String token = jwtUtil.generateToken(
                user.getEmail()
        );

        return new AuthResponse(
                token,
                user.getRole()
        );
    }
}