package com.github.syxxn.DanaJJang.controller;

import com.github.syxxn.DanaJJang.payload.request.SignInRequest;
import com.github.syxxn.DanaJJang.payload.response.AccessTokenResponse;
import com.github.syxxn.DanaJJang.payload.response.TokenResponse;
import com.github.syxxn.DanaJJang.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public TokenResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authService.signIn(request);
    }

    @PutMapping
    public AccessTokenResponse refreshToken(@RequestHeader("X-Refresh-Token") String refreshToken) {
        return authService.refreshToken(refreshToken);
    }

}
