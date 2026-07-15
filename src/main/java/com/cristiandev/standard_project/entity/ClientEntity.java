package com.cristiandev.standard_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "clientes")
public class ClientEntity {

    @Id
    private String id;
    private String nombre;
    private int edad;
    private String email;
}
