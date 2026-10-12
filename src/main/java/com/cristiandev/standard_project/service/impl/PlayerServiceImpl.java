package com.cristiandev.standard_project.service.impl;

import com.cristiandev.standard_project.dto.client.PlayerDTO;
import com.cristiandev.standard_project.dto.pokemon.PokemonDTO;
import com.cristiandev.standard_project.entity.PlayerEntity;
import com.cristiandev.standard_project.exception.StandardProjectException;
import com.cristiandev.standard_project.mapper.ClientMapper;
import com.cristiandev.standard_project.repository.ClientsRepository;
import com.cristiandev.standard_project.service.PlayerService;
import com.cristiandev.standard_project.service.PokemonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.cristiandev.standard_project.utils.ExceptionConstants.*;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FAILED_DEPENDENCY;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final ClientMapper clientMapper;
    private final ClientsRepository clientsRepository;
    private final PokemonService pokemonService;

    @Override
    public PlayerDTO save(PlayerDTO client) throws StandardProjectException {
        String clientId = validateAndGetClientId(client.getName(), client.getEmail());
        client.setId(clientId);
        PokemonDTO pokemonBuddy = pokemonService.getPokemon();
        client.setPokemonBuddy(pokemonBuddy.getName());
        PlayerEntity playerEntity = clientMapper.toEntity(client);

        try {
            clientsRepository.saveAndFlush(playerEntity);
        } catch (Exception e) {
            throw new StandardProjectException(CLIENT_NOT_CREATED_MSG, FAILED_DEPENDENCY,
                    CLIENT_NOT_CREATED_CODE);
        }

        return client;
    }

    private String validateAndGetClientId(String name, String email) throws StandardProjectException {
        String clientId = UUID.nameUUIDFromBytes(name.concat(email).getBytes()).toString();
        if (clientsRepository.existsById(clientId)) {
            throw new StandardProjectException(CLIENT_ALREADY_EXIST_MSG, CONFLICT, CLIENT_ALREADY_EXIST_CODE);
        }
        return clientId;
    }



}
