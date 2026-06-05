package com.cristiandev.standard_project.router;

import com.cristiandev.standard_project.dto.response.ResponseDTO;
import com.cristiandev.standard_project.exception.StandardProjectException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class StandardProjectControllerAdvice {

    @ExceptionHandler(StandardProjectException.class)
    public ResponseEntity<ResponseDTO> handleStandardProjectException(StandardProjectException ex) {
        ResponseDTO response = ResponseDTO.builder()
                .exceptionCode(ex.getExceptionCode())
                .exceptionMessage(ex.getMessage()).build();
        return new ResponseEntity<>(response, ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleGeneralException(Exception ex) {
        ResponseDTO response = ResponseDTO.builder()
                .exceptionCode("SERVER_ERROR")
                .exceptionMessage(ex.getMessage()).build();
        return new ResponseEntity<>(response, INTERNAL_SERVER_ERROR);
    }

}
