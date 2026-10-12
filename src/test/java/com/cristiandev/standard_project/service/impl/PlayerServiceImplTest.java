package com.cristiandev.standard_project.service.impl;

import com.cristiandev.standard_project.dto.client.PlayerDTO;
import com.cristiandev.standard_project.entity.PlayerEntity;
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
public class PlayerServiceImplTest {

    @Mock
    private ClientMapper clientMapper;

    @Mock
    private ClientsRepository clientsRepository;

    @InjectMocks
    private PlayerServiceImpl clientService;

    @Test
    public void saveClientTest() throws IOException, StandardProjectException {

        InputStream requestJson = new ClassPathResource("/service/client/ClientServiceImpl/ClientSuccessDTO.json").getInputStream();
        String request = new String(requestJson.readAllBytes(), UTF_8);
        PlayerDTO client = new ObjectMapper().readValue(request, PlayerDTO.class);

        PlayerEntity dummyPlayerEntity = new PlayerEntity();

        when(clientMapper.toEntity(any(PlayerDTO.class))).thenReturn(dummyPlayerEntity);
        when(clientsRepository.saveAndFlush(any(PlayerEntity.class))).thenReturn(new PlayerEntity());

        assertDoesNotThrow(() -> clientService.save(client));


    }
}
