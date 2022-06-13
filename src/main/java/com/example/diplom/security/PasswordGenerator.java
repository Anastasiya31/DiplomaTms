package com.example.diplom.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String pass = "irina2000";
        String encPass = bCryptPasswordEncoder.encode(pass);

        System.out.println(encPass);
    }
}
