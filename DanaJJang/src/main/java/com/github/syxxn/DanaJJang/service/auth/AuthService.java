package com.github.syxxn.DanaJJang.service.auth;


import com.github.syxxn.DanaJJang.payload.request.SignInRequest;
import com.github.syxxn.DanaJJang.payload.response.AccessTokenResponse;
import com.github.syxxn.DanaJJang.payload.response.TokenResponse;

public interface AuthService {
    TokenResponse signIn(SignInRequest signInRequest);
    AccessTokenResponse refreshToken(String refreshToken);
}
