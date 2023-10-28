package app.railway.up.controllers;

import app.railway.up.controllers.dto.request.ClienteDTO;
import app.railway.up.controllers.dto.request.MessageResponseDTO;
import app.railway.up.controllers.exceptions.ResourceNotFoundException;
import app.railway.up.domain.cliente.Cliente;
import app.railway.up.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @PostMapping
    @Operation(summary = "Criar um novo cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema( description = "Cliente criado com sucesso."),
                    examples = @ExampleObject( value = EXAMPLE_CREATE_SUCESS))}),
            @ApiResponse(responseCode = "403", content = { @Content(schema = @Schema( description = "Sem permissão para criar um cliente."),
                    examples = @ExampleObject( value = EXAMPLE_ACCESS_DENIED))})
    })
    public ResponseEntity<MessageResponseDTO> create(@RequestBody ClienteDTO clienteDTO){
        var message = clienteService.create(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping
    @Operation(summary = "Buscar todos os cliente")
    public ResponseEntity<List<Cliente>> findAll(){
        List<Cliente> clientes = clienteService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(clientes);
    }


    @GetMapping("{id}")
    @Operation(summary = "Buscar um cliente")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) throws ResourceNotFoundException {
        Cliente cliente = clienteService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualizar um cliente")
    public ResponseEntity<MessageResponseDTO> update(@PathVariable Long id, @RequestBody Cliente cliente) throws ResourceNotFoundException {
        var message = clienteService.update(id,cliente);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @PatchMapping("{id}/limite")
    @Operation(summary = "Atualizar o limite de um cliente")
    public ResponseEntity<MessageResponseDTO> patchLimite(@PathVariable Long id, @RequestBody Cliente cliente) throws ResourceNotFoundException {
        var message = clienteService.patchLimite(id,cliente);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @PatchMapping("{id}/status")
    @Operation(summary = "Atualizar o status de um cliente")
    public ResponseEntity<MessageResponseDTO> patchStatus(@PathVariable Long id, @RequestBody Cliente cliente) throws ResourceNotFoundException {
        var message = clienteService.patchStatus(id,cliente);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deletar um cliente")
    public ResponseEntity<MessageResponseDTO> deleteById(@PathVariable Long id) throws ResourceNotFoundException {
        var message = clienteService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    private final String EXAMPLE_CREATE_SUCESS = "{\n" +
            "  \"status\": \"Created\",\n" +
            "  \"code\": 201,\n" +
            "  \"message\": \"Cliente criado com sucesso.\"\n" +
            "}";
    private final String EXAMPLE_ACCESS_DENIED = "{\n" +
            "  \"timestamp\": 1698413037110,\n" +
            "  \"status\": 403,\n" +
            "  \"message\": \"Acesso negado.\",\n" +
            "  \"path\": \"/api/v1/clientes\"\n" +
            "}";
}
