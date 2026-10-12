package com.cristiandev.standard_project.router;

import com.cristiandev.standard_project.dto.client.PlayerDTO;
import com.cristiandev.standard_project.router.cliente.PlayerRouter;
import com.cristiandev.standard_project.service.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;

import java.io.InputStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
public class PlayerRouterTest {

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private PlayerRouter playerRouter;

    @Test
    void saveOkWhenClientSavedSuccess() throws Exception {

        InputStream requestJson = new ClassPathResource("/router/client/ClientRequestSuccess.json").getInputStream();
        InputStream responseJson = new ClassPathResource("/router/client/ClientResponseSuccess.json").getInputStream();
        String request = new String(requestJson.readAllBytes(), UTF_8);
        String response = new String(responseJson.readAllBytes(), UTF_8);
        PlayerDTO clientRequest = new ObjectMapper().readValue(request, PlayerDTO.class);
        PlayerDTO clientResponse = new ObjectMapper().readValue(response, PlayerDTO.class);

        when(playerService.save(any(PlayerDTO.class))).thenReturn(clientResponse);

        ResponseEntity<PlayerDTO> responseRouter = playerRouter.save(clientRequest);

        assertNotNull(response);
        assertEquals(OK, responseRouter.getStatusCode());
        assertEquals(clientResponse, responseRouter.getBody());

        verify(playerService).save(any(PlayerDTO.class));
    }
}
