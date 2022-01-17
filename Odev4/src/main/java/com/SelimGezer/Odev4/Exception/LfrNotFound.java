package com.SelimGezer.Odev4.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Late fee rate not fund")
public class LfrNotFound extends RuntimeException {

    public LfrNotFound(String message) {
        super(message);
    }
}
