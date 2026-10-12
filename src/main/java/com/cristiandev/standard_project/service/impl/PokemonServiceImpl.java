package com.cristiandev.standard_project.service.impl;


import com.cristiandev.standard_project.dto.pokemon.PokemonDTO;
import com.cristiandev.standard_project.dto.restclient.PokemonResponseDTO;
import com.cristiandev.standard_project.exception.StandardProjectException;
import com.cristiandev.standard_project.restclient.PokeApiRestClient;
import com.cristiandev.standard_project.service.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.cristiandev.standard_project.utils.ExceptionConstants.POKEMON_FETCH_ERROR;
import static com.cristiandev.standard_project.utils.ExceptionConstants.POKEMON_FETCH_MSG;
import static com.cristiandev.standard_project.utils.PokemonConstants.LIMIT;
import static com.cristiandev.standard_project.utils.PokemonConstants.OFFSET;
import static org.springframework.http.HttpStatus.FAILED_DEPENDENCY;

@Service
@RequiredArgsConstructor
public class PokemonServiceImpl implements PokemonService {

    private final PokeApiRestClient pokeApiRestClient;

    @Override
    public PokemonDTO getPokemon() throws StandardProjectException{

        try {
            PokemonResponseDTO response = pokeApiRestClient.getPokemons(OFFSET, LIMIT);
            int index = (int) (Math.random() * response.getResults().size());
            PokemonDTO clientPokemon = response.getResults().get(index);
            return clientPokemon;
        } catch (Exception e) {
            throw new StandardProjectException(POKEMON_FETCH_MSG, FAILED_DEPENDENCY, POKEMON_FETCH_ERROR);
        }
    }
}
