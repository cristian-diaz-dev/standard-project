package com.cristiandev.standard_project.router;

import com.cristiandev.standard_project.dto.response.ResponseDTO;
import com.cristiandev.standard_project.exception.StandardProjectException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.cristiandev.standard_project.utils.ExceptionConstants.INTERNAL_SERVER_ERROR_MSG;
import static com.cristiandev.standard_project.utils.ExceptionConstants.SERVER_ERROR_CODE;
import static com.cristiandev.standard_project.utils.LogConstants.EXCEPTION_LOG_MSG;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@ControllerAdvice
public class StandardProjectControllerAdvice {

    @ExceptionHandler(StandardProjectException.class)
    public ResponseEntity<ResponseDTO> handleStandardProjectException(StandardProjectException ex) {
        ResponseDTO response = buildResponse(ex.getExceptionCode(), ex.getMessage(), ex);
        return new ResponseEntity<>(response, ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleGeneralException(Exception ex) {
        ResponseDTO response = buildResponse(SERVER_ERROR_CODE, INTERNAL_SERVER_ERROR_MSG, ex);
        return new ResponseEntity<>(response, INTERNAL_SERVER_ERROR);
    }

    private ResponseDTO buildResponse(String code, String message, Exception ex) {
        log.error(EXCEPTION_LOG_MSG, ex);
        return ResponseDTO.builder()
                .exceptionCode(code)
                .exceptionMessage(message).build();
    }

}
