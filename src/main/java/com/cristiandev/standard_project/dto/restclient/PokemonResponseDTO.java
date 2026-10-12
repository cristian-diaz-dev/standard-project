package com.cristiandev.standard_project.dto.restclient;

import com.cristiandev.standard_project.dto.pokemon.PokemonDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class PokemonResponseDTO {
    private List<PokemonDTO> results;
}
