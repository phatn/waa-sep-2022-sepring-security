package edu.miu.security.service.impl;

import edu.miu.security.entity.Role;
import edu.miu.security.entity.User;
import edu.miu.security.model.LoginRequest;
import edu.miu.security.model.LoginResponse;
import edu.miu.security.model.RefreshTokenRequest;
import edu.miu.security.repository.UserRepository;
import edu.miu.security.security.JwtHelper;
import edu.miu.security.service.RoleService;
import edu.miu.security.service.UaaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UaaServiceImpl implements UaaService {

    private final AuthenticationManager authenticationManager;

    private final RoleService roleService;

    private final JwtHelper jwtHelper;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            var usernamePasswordAuthenticationToken =  new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                    loginRequest.getPassword());
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch(BadCredentialsException e) {
            log.error("Bad Credentials: ", e);
        }

        final String accessToken = jwtHelper.generateToken(loginRequest.getEmail());
        final String refreshToken = jwtHelper.generateRefreshToken(loginRequest.getEmail());
        var loginResponse = new LoginResponse(accessToken, refreshToken, loginRequest.getEmail());
        return loginResponse;
    }

    @Override
    public LoginResponse register(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role role = roleService.getByName("USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);

        final String accessToken = jwtHelper.generateToken(user.getEmail());
        final String refreshToken = jwtHelper.generateRefreshToken(user.getEmail());
        var loginResponse = new LoginResponse(accessToken, refreshToken, user.getEmail());
        return loginResponse;
    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        boolean isRefreshTokenValid = jwtHelper.validateToken(refreshTokenRequest.getRefreshToken());
        if (isRefreshTokenValid) {
            final String email = jwtHelper.getUsernameFromToken(refreshTokenRequest.getRefreshToken());
            final String accessToken = jwtHelper.generateToken(jwtHelper.getSubject(refreshTokenRequest.getRefreshToken()));
            var loginResponse = new LoginResponse(accessToken, refreshTokenRequest.getRefreshToken(), email);
            return loginResponse;
        }
        return new LoginResponse();
    }
}
