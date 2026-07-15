package com.cristiandev.standard_project.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientDTO {

    private String id;
    private String nombre;
    private int edad;
    private String email;

}
