package com.github.syxxn.DanaJJang.entity.refreshtoken;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Column;

@Getter
@RedisHash(value = "refresh_token")
@AllArgsConstructor
public class RefreshToken {

    @Id
    private String userId;

    @Indexed
    private String refreshToken;

    @Column(nullable = false)
    private Long refreshExp;

    public RefreshToken update(String refreshToken, Long refreshExp) {
        this.userId = refreshToken;
        this.refreshExp = refreshExp;
        return this;
    }
    
}
