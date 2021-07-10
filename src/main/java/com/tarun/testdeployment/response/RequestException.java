package com.tarun.testdeployment.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestException extends Exception{

    private Response response;
    private HttpStatus httpStatus;

}
