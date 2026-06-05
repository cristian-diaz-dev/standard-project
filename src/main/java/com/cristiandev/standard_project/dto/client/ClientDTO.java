package com.cristiandev.standard_project.dto.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientDTO {

    private String id;

    @NotBlank(message = "El nombre no puede ser vacío")
    private String name;

    @Min(value = 18, message = "La edad debe ser mayor a 18 años")
    private int age;

    @Email(message = "El campo email debe cumplir con un formato válido de correo electrónico")
    private String email;

}
