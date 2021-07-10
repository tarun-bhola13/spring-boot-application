package com.tarun.testdeployment.advice;

import com.tarun.testdeployment.response.RequestException;
import com.tarun.testdeployment.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {


    @ExceptionHandler
    public ResponseEntity<Response> handler(RequestException exception){
        var response = new Response(exception.getResponse().getMessage(), exception.getResponse().getDate());
        return new ResponseEntity<>(response,exception.getHttpStatus());
    }
}
