package com.pm.authservice.service;

import com.pm.authservice.dto.LoginRequestDTO;

import java.util.Optional;

public interface AuthService {
    public Optional<String> authenticate(LoginRequestDTO loginRequestDTO);
    public boolean validateToken(String token);
}
