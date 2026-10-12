package com.cristiandev.standard_project.router.cliente;

import com.cristiandev.standard_project.dto.client.PlayerDTO;
import com.cristiandev.standard_project.exception.StandardProjectException;
import com.cristiandev.standard_project.router.cliente.base.PlayerRouterBase;
import com.cristiandev.standard_project.service.PlayerService;
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

@Tag(name = "jugadores", description = "Endpoint para la administración y registro de jugadores.")
@RestController
@RequiredArgsConstructor
public class PlayerRouter extends PlayerRouterBase {

    private final PlayerService playerService;

    @PostMapping(SAVE)
    @Operation(
            summary = "Registrar un nuevo jugador",
            description = "Recibe los datos del jugador, genera un ID determinista basado en su nombre y email, y persiste el jugador en la base de datos."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jugador guardado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlayerDTO.class))),
            @ApiResponse(responseCode = "409", description = "No fue posible guardar el jugador"),
            @ApiResponse(
                    responseCode = "500", description = "Error interno del servidor"
            )
    })
    public ResponseEntity<PlayerDTO> save(@Valid @RequestBody PlayerDTO jugador) throws StandardProjectException {
        return ResponseEntity.ok(playerService.save(jugador));
    }
}
