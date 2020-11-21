package com.github.syxxn.DanaJJang.service.user;

import com.github.syxxn.DanaJJang.entity.user.User;
import com.github.syxxn.DanaJJang.entity.user.UserRepository;
import com.github.syxxn.DanaJJang.exception.UserAlreadyExistsException;
import com.github.syxxn.DanaJJang.payload.request.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    public final UserRepository userRepository;
    public final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpRequest signUpRequest){
        userRepository.findByUserId(signUpRequest.getUserId())
                .ifPresent(user->{
                 throw new UserAlreadyExistsException();
        });
        userRepository.save(
                User.builder()
                        .userId(signUpRequest.getUserId())
                        .password(signUpRequest.getPassword())
                .build()
        );
    }
}
