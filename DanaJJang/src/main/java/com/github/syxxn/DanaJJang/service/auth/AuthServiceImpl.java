package com.github.syxxn.DanaJJang.service.auth;

import com.github.syxxn.DanaJJang.entity.refreshtoken.RefreshTokenRepository;
import com.github.syxxn.DanaJJang.entity.user.User;
import com.github.syxxn.DanaJJang.entity.user.UserRepository;
import com.github.syxxn.DanaJJang.exception.InvalidTokenException;
import com.github.syxxn.DanaJJang.exception.UserNotFoundException;
import com.github.syxxn.DanaJJang.payload.request.SignInRequest;
import com.github.syxxn.DanaJJang.payload.response.AccessTokenResponse;
import com.github.syxxn.DanaJJang.payload.response.TokenResponse;
import com.github.syxxn.DanaJJang.security.JwtTokenProvider;
import com.github.syxxn.DanaJJang.service.refreshtoken.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    @Value("${auth.jwt.exp.refresh}")
    private Long refreshExp;

   public final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;
   private final AuthenticationManager authenticationManager;
   private final JwtTokenProvider tokenProvider;
   private final RefreshTokenService refreshTokenService;
   private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

   @Override
    public TokenResponse signIn(SignInRequest signInRequest){
       User user = userRepository.findByUserId(signInRequest.getUserId())
               .filter(u -> passwordEncoder.matches(signInRequest.getPassword(), u.getPassword()))
               .orElseThrow(UserNotFoundException::new);

       String accessToken = jwtTokenProvider.generateAccessToken(user.getUserId());
       String refreshToken = jwtTokenProvider.generateRefreshToken(user.getUserId());

       return new TokenResponse(accessToken, refreshToken);
   }
    @Override
    public AccessTokenResponse refreshToken(String receivedToken) {
        if (!jwtTokenProvider.isRefreshToken(receivedToken)) {
            throw new InvalidTokenException();
        }

        return refreshTokenRepository.findByRefreshToken(receivedToken)
                .map(refreshToken -> {
                    String generateRefreshToken = jwtTokenProvider.generateRefreshToken(refreshToken.getUserId());
                    return refreshToken.update(generateRefreshToken, refreshExp);
                })
                .map(refreshTokenRepository::save)
                .map(refreshToken -> new AccessTokenResponse(jwtTokenProvider.generateAccessToken(refreshToken.getUserId())))
                .orElseThrow(UserNotFoundException::new);
    }
}

