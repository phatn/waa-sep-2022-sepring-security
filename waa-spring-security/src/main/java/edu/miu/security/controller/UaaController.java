package edu.miu.security.controller;

import edu.miu.security.entity.User;
import edu.miu.security.model.LoginRequest;
import edu.miu.security.model.LoginResponse;
import edu.miu.security.model.RefreshTokenRequest;
import edu.miu.security.service.UaaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/uaa")
@RequiredArgsConstructor
public class UaaController {

    private final UaaService uaaService;


    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return uaaService.login(loginRequest);
    }

    @PostMapping("/sign-up")
    public LoginResponse register(@RequestBody User user) {
        return uaaService.register(user);
    }

    @PostMapping("/refresh-token")
    public LoginResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return uaaService.refreshToken(refreshTokenRequest);
    }
}
