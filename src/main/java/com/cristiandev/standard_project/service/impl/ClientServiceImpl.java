package com.cristiandev.standard_project.service.impl;

import com.cristiandev.standard_project.dto.ClientDTO;
import com.cristiandev.standard_project.entity.ClientEntity;
import com.cristiandev.standard_project.exception.StandardProjectException;
import com.cristiandev.standard_project.mapper.ClientMapper;
import com.cristiandev.standard_project.repository.ClientsRepository;
import com.cristiandev.standard_project.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.cristiandev.standard_project.utils.ExceptionConstants.*;
import static com.cristiandev.standard_project.utils.LogConstants.NOT_SAVED_ENTITY_MSG;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.FAILED_DEPENDENCY;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientMapper clientMapper;
    private final ClientsRepository clientsRepository;

    @Override
    public ClientDTO save(ClientDTO client) throws StandardProjectException {
        String clientId = validateAndGetClientId(client.getNombre(), client.getEmail());
        client.setId(clientId);
        ClientEntity clientEntity = clientMapper.toEntity(client);

        try {
            clientsRepository.save(clientEntity);
        } catch (Exception e) {
            log.error(NOT_SAVED_ENTITY_MSG, e.getMessage());
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
