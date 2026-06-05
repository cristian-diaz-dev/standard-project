package com.cristiandev.standard_project.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
public class ResponseDTO {
    private Object result;
    private String exceptionCode;
    private String exceptionMessage;
}
