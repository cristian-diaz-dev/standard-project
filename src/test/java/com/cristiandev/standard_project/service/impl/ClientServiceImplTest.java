package com.cristiandev.standard_project.service.impl;

import com.cristiandev.standard_project.dto.client.ClientDTO;
import com.cristiandev.standard_project.entity.ClientEntity;
import com.cristiandev.standard_project.exception.StandardProjectException;
import com.cristiandev.standard_project.mapper.ClientMapper;
import com.cristiandev.standard_project.repository.ClientsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {

    @Mock
    private ClientMapper clientMapper;

    @Mock
    private ClientsRepository clientsRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    public void saveClientTest() throws IOException, StandardProjectException {

        InputStream requestJson = new ClassPathResource("/service/client/ClientServiceImpl/ClientSuccessDTO.json").getInputStream();
        String request = new String(requestJson.readAllBytes(), UTF_8);
        ClientDTO client = new ObjectMapper().readValue(request, ClientDTO.class);

        ClientEntity dummyClientEntity = new ClientEntity();

        when(clientMapper.toEntity(any(ClientDTO.class))).thenReturn(dummyClientEntity);
        when(clientsRepository.saveAndFlush(any(ClientEntity.class))).thenReturn(new ClientEntity());

        assertDoesNotThrow(() -> clientService.save(client));


    }
}
