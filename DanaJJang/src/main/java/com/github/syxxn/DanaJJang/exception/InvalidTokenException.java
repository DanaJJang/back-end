package com.github.syxxn.DanaJJang.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "Invalid Token Exception")
public class InvalidTokenException extends RuntimeException{
}
