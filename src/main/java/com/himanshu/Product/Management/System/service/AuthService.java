package com.himanshu.Product.Management.System.service;

import com.himanshu.Product.Management.System.dto.AuthResponse;
import com.himanshu.Product.Management.System.dto.LoginRequest;
import com.himanshu.Product.Management.System.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}