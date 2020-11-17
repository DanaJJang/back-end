package com.github.syxxn.DanaJJang.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Word Not Found")
public class WordNotFoundException extends RuntimeException{
}
