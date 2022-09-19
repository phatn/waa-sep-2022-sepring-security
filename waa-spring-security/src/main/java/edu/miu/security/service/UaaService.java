package edu.miu.security.service;

import edu.miu.security.entity.User;
import edu.miu.security.model.LoginRequest;
import edu.miu.security.model.LoginResponse;
import edu.miu.security.model.RefreshTokenRequest;

public interface UaaService {

    LoginResponse login(LoginRequest loginRequest);

    LoginResponse register(User user);

    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
