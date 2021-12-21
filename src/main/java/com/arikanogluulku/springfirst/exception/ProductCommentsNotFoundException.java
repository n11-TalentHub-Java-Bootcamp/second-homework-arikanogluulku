package com.arikanogluulku.springfirst.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductCommentsNotFoundException extends RuntimeException {
    public ProductCommentsNotFoundException(String message) {
        super(message);
    }
}
