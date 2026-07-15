package com.cristiandev.standard_project.router;

import com.cristiandev.standard_project.dto.ResponseDTO;
import com.cristiandev.standard_project.exception.StandardProjectException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.cristiandev.standard_project.utils.ExceptionConstants.PROJECT_INTERNAL_ERROR;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class StandardProjectControllerAdvice {

    @ExceptionHandler(StandardProjectException.class)
    public ResponseEntity<StandardProjectException> handleStandardProjectException(StandardProjectException ex) {
        return new ResponseEntity<>(ex, ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleGeneralException(Exception ex) {
        ResponseDTO response = generateResponse(INTERNAL_SERVER_ERROR, ex.getMessage(), PROJECT_INTERNAL_ERROR, null);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    private ResponseDTO generateResponse(HttpStatus httpStatus, String message, String processCode, Object result) {
        return ResponseDTO.builder().httpStatus(httpStatus).message(message).processCode(processCode).build();
    }
}
