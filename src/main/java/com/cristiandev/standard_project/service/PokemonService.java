package com.cristiandev.standard_project.service;

import com.cristiandev.standard_project.dto.pokemon.PokemonDTO;
import com.cristiandev.standard_project.exception.StandardProjectException;

public interface PokemonService {

    PokemonDTO getPokemon()throws StandardProjectException;
}
