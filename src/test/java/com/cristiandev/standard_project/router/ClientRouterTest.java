package com.cristiandev.standard_project.router;

import com.cristiandev.standard_project.dto.client.ClientDTO;
import com.cristiandev.standard_project.router.cliente.ClientRouter;
import com.cristiandev.standard_project.service.ClientService;
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
public class ClientRouterTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientRouter clientRouter;

    @Test
    void saveOkWhenClientSavedSuccess() throws Exception {

        InputStream requestJson = new ClassPathResource("/router/client/ClientSuccessDTO.json").getInputStream();
        InputStream responseJson = new ClassPathResource("/router/client/ClientResponseSuccess.json").getInputStream();
        String request = new String(requestJson.readAllBytes(), UTF_8);
        String response = new String(responseJson.readAllBytes(), UTF_8);
        ClientDTO clientRequest = new ObjectMapper().readValue(request, ClientDTO.class);
        ClientDTO clientResponse = new ObjectMapper().readValue(response, ClientDTO.class);

        when(clientService.save(any(ClientDTO.class))).thenReturn(clientResponse);

        ResponseEntity<ClientDTO> responseRouter = clientRouter.save(clientRequest);

        assertNotNull(response);
        assertEquals(OK, responseRouter.getStatusCode());
        assertEquals(clientResponse, responseRouter.getBody());

        verify(clientService).save(any(ClientDTO.class));
    }
}
