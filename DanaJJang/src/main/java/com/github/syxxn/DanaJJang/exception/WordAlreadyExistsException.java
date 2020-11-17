package com.github.syxxn.DanaJJang.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "Word Already Exists Exception")
public class WordAlreadyExistsException extends RuntimeException{
}
