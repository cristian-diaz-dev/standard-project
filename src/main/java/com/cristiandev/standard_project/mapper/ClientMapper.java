package com.cristiandev.standard_project.mapper;

import com.cristiandev.standard_project.dto.client.PlayerDTO;
import com.cristiandev.standard_project.entity.PlayerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    PlayerEntity toEntity(PlayerDTO playerDTO);
    PlayerDTO toDto(PlayerEntity clienteDTO);
}
