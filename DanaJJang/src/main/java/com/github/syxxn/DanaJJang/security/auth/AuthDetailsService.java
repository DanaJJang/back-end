package com.github.syxxn.DanaJJang.security.auth;

import com.github.syxxn.DanaJJang.entity.user.UserRepository;
import com.github.syxxn.DanaJJang.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public AuthDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        return userRepository.findByUserId(userId)
                .map(AuthDetails::new)
                .orElseThrow(UserNotFoundException::new);
    }
}
