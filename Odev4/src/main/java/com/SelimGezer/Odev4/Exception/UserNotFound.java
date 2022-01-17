package com.SelimGezer.Odev4.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "User not found")
public class UserNotFound extends RuntimeException {
    public UserNotFound(String message) {
        super(message);
    }
}
