package com.cristiandev.standard_project.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@Builder
public class ResponseDTO {
    private HttpStatus httpStatus;
    private String message;
    private String processCode;
}
