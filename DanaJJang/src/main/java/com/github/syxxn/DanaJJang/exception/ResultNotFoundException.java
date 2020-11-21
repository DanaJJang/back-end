package com.github.syxxn.DanaJJang.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Result Not Found")
public class ResultNotFoundException extends RuntimeException{
}
