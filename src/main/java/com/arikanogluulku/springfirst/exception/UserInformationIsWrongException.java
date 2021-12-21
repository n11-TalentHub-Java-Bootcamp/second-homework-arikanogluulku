package com.arikanogluulku.springfirst.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserInformationIsWrongException extends RuntimeException  {
    public UserInformationIsWrongException(String message) {
        super(message);
    }
}

