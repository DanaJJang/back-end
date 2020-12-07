package com.github.syxxn.DanaJJang.controller;

import com.github.syxxn.DanaJJang.payload.request.SignUpRequest;
import com.github.syxxn.DanaJJang.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody SignUpRequest signUpRequest){
        userService.signUp(signUpRequest);
    }

}
