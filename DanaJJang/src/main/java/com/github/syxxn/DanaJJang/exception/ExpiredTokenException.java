package com.github.syxxn.DanaJJang.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.GONE,reason = "Expired Token Exception")
public class ExpiredTokenException extends RuntimeException{
}
