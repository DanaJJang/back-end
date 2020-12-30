package com.github.syxxn.DanaJJang.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    @NotNull
    private String userId;

    @NotNull
    private String password;

}
