package com.SelimGezer.Odev4.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Debt Not found")
public class DebtNotFound extends RuntimeException {
    public DebtNotFound(String message) {
        super(message);
    }
}
