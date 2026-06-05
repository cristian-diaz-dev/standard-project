package com.cristiandev.standard_project.router.cliente;

import com.cristiandev.standard_project.dto.client.ClientDTO;
import com.cristiandev.standard_project.exception.StandardProjectException;
import com.cristiandev.standard_project.router.cliente.base.ClientRouterBase;
import com.cristiandev.standard_project.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "clientes", description = "Endpoint para la administración y registro de clientes.")
@RestController
@RequiredArgsConstructor
public class ClientRouter extends ClientRouterBase {

    private final ClientService clientService;

    @PostMapping(SAVE)
    @Operation(
            summary = "Registrar un nuevo cliente",
            description = "Recibe los datos del cliente, genera un ID determinista basado en su nombre y email, y persiste el cliente en la base de datos."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente guardado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClientDTO.class))),
            @ApiResponse(responseCode = "409", description = "No fue posible guardar el cliente"),
            @ApiResponse(
                    responseCode = "500", description = "Error interno del servidor"
            )
    })
    public ResponseEntity<ClientDTO> save(@Valid @RequestBody ClientDTO cliente) throws StandardProjectException {
        return ResponseEntity.ok(clientService.save(cliente));
    }
}
