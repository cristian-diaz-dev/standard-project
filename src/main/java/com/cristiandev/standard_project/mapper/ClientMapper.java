package com.cristiandev.standard_project.mapper;

import com.cristiandev.standard_project.dto.client.ClientDTO;
import com.cristiandev.standard_project.entity.ClientEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientEntity toEntity(ClientDTO clientDTO);
    ClientDTO toDto(ClientEntity clienteDTO);
}
