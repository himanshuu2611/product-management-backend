package com.himanshu.Product.Management.System.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;

    private String password;
}