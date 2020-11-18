package com.github.syxxn.DanaJJang.controller;

import com.github.syxxn.DanaJJang.payload.request.SignUpRequest;
import com.github.syxxn.DanaJJang.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserControllder {

    private final UserService userService;

    @PostMapping
    public void signUp(@RequestBody SignUpRequest signUpRequest){
        userService.signUp(signUpRequest);
    }

}
