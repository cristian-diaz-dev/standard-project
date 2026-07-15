package com.cristiandev.standard_project.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class StandardProjectException extends Exception {

    private HttpStatus httpStatus;
    private String exceptionCode;

    public StandardProjectException(String message,
                                    HttpStatus httpStatus,
                                    String exceptionCode) {
        super(message);
        this.httpStatus = httpStatus;
        this.exceptionCode = exceptionCode;
    }

}
