package com.github.syxxn.DanaJJang.service.refreshtoken;

import com.github.syxxn.DanaJJang.entity.refreshtoken.RefreshToken;
import com.github.syxxn.DanaJJang.entity.refreshtoken.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Async
    @Override
    public void save(RefreshToken refreshToken) {
        refreshTokenRepository.save(refreshToken);
    }

}
