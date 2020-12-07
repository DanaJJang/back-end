package com.github.syxxn.DanaJJang.service.user;

import com.github.syxxn.DanaJJang.entity.user.User;
import com.github.syxxn.DanaJJang.entity.user.UserRepository;
import com.github.syxxn.DanaJJang.exception.UserAlreadyExistsException;
import com.github.syxxn.DanaJJang.payload.request.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpRequest signUpRequest){
        String id = signUpRequest.getUserId();
        String password = signUpRequest.getPassword();

        Optional<User> user = userRepository.findByUserId(id);

        if(user.isPresent())
            throw new UserAlreadyExistsException();

        userRepository.save(
                User.builder()
                        .userId(id)
                        .password(passwordEncoder.encode(password))
                .build()
        );
    }
}