package com.github.syxxn.DanaJJang.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtTokenFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request); //토큰 해석(JWT)
        if (token != null && jwtTokenProvider.validateToken(token)) { //토큰 유효성 검사
            Authentication auth = jwtTokenProvider.getAuthentication(token); // 인증 객체를 생성
            SecurityContextHolder.getContext().setAuthentication(auth); // 요청에 인증객체를 담아줌
        }
        chain.doFilter(request, response);
    }

}
