package com.github.syxxn.DanaJJang.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {

    private String userId;
    private String password;

    public UsernamePasswordAuthenticationToken getAuthToken(String userId) {
        return new UsernamePasswordAuthenticationToken(userId, password);
    }
}
