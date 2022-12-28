package com.laonworks.shop.api.controller.common;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientResponseException;

@Controller
@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler
    public ResponseEntity<String> handle(RestClientResponseException ex) {
        return ResponseEntity.status(ex.getRawStatusCode()).body(ex.getStatusText());
    }
}
