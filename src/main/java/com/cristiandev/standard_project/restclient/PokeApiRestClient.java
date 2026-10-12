package com.cristiandev.standard_project.restclient;

import com.cristiandev.standard_project.dto.restclient.PokemonResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "pokeApiRestClient", url = "${restclient.pokeapi.base.url}")
public interface PokeApiRestClient {

    @GetMapping("/pokemon?offset={offset}&limit={limit}")
    PokemonResponseDTO getPokemons(@RequestParam("offset") int offset, @RequestParam("limit") int limit);
}
