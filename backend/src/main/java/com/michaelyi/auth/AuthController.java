package com.michaelyi.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("v1/auth")
public class AuthController {
    private final AuthService service;

    @PostMapping("login")
    public String login(@RequestBody AuthLoginRequest req) {
        return service.login(req.email());
    }

    @GetMapping("validate-token")
    public void validateToken(
            @RequestHeader("Authorization") String bearerToken) {
        service.validateToken(bearerToken);
    }
}
