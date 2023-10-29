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
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema( description = "Clientes buscados com sucesso."),
                    examples = @ExampleObject( value = EXAMPLE_SUCESSFULLY_FOUND))}),
            @ApiResponse(responseCode = "403", content = { @Content(schema = @Schema( description = "Sem permissão para buscar clientes."),
                    examples = @ExampleObject( value = EXAMPLE_ACCESS_DENIED))})
    })
    public ResponseEntity<List<Cliente>> findAll(){
        List<Cliente> clientes = clienteService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(clientes);
    }


    @GetMapping("{id}")
    @Operation(summary = "Buscar um cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema( description = "Cliente buscado com sucesso."),
                    examples = @ExampleObject( value = EXAMPLE_SUCESSFULLY_FOUND))}),
            @ApiResponse(responseCode = "403", content = { @Content(schema = @Schema( description = "Sem permissão para buscar clientes."),
                    examples = @ExampleObject( value = EXAMPLE_ACCESS_DENIED))}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema( description = "Cliente não encontrado."),
                    examples = @ExampleObject( value = EXAMPLE_NOT_FOUND))})
    })
    public ResponseEntity<Cliente> findById(@PathVariable Long id) throws ResourceNotFoundException {
        Cliente cliente = clienteService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualizar um cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema( description = "Cliente atualizado com sucesso."),
                    examples = @ExampleObject( value = EXAMPLE_UPDATE_CUSTOMER))}),
            @ApiResponse(responseCode = "403", content = { @Content(schema = @Schema( description = "Sem permissão para atualizar o cliente."),
                    examples = @ExampleObject( value = EXAMPLE_ACCESS_DENIED))}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema( description = "Cliente não encontrado."),
                    examples = @ExampleObject( value = EXAMPLE_NOT_FOUND))})
    })
    public ResponseEntity<MessageResponseDTO> update(@PathVariable Long id, @RequestBody Cliente cliente) throws ResourceNotFoundException {
        var message = clienteService.update(id,cliente);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @PatchMapping("{id}/limite")
    @Operation(summary = "Atualizar o limite de um cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema( description = "Limite atualizado com sucesso."),
                    examples = @ExampleObject( value = EXAMPLE_UPDATE_VOUCHER))}),
            @ApiResponse(responseCode = "403", content = { @Content(schema = @Schema( description = "Sem permissão para atualizar limite do cliente."),
                    examples = @ExampleObject( value = EXAMPLE_ACCESS_DENIED))}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema( description = "Cliente não encontrado."),
                    examples = @ExampleObject( value = EXAMPLE_NOT_FOUND))})
    })
    public ResponseEntity<MessageResponseDTO> patchLimite(@PathVariable Long id, @RequestBody Cliente cliente) throws ResourceNotFoundException {
        var message = clienteService.patchLimite(id,cliente);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
    @PatchMapping("{id}/status")
    @Operation(summary = "Atualizar o status de um cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema( description = "Status atualizado com sucesso."),
                    examples = @ExampleObject( value = EXAMPLE_UPDATE_STATUS))}),
            @ApiResponse(responseCode = "403", content = { @Content(schema = @Schema( description = "Sem permissão para atualizar status do cliente."),
                    examples = @ExampleObject( value = EXAMPLE_ACCESS_DENIED))}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema( description = "Cliente não encontrado."),
                    examples = @ExampleObject( value = EXAMPLE_NOT_FOUND))})
    })
    public ResponseEntity<MessageResponseDTO> patchStatus(@PathVariable Long id, @RequestBody Cliente cliente) throws ResourceNotFoundException {
        var message = clienteService.patchStatus(id,cliente);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deletar um cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema( description = "Cliente deletado com sucesso."),
                    examples = @ExampleObject( value = EXAMPLE_DELETE_SUCCESS))}),
            @ApiResponse(responseCode = "403", content = { @Content(schema = @Schema( description = "Sem permissão para deletar o cliente."),
                    examples = @ExampleObject( value = EXAMPLE_ACCESS_DENIED))}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema( description = "Cliente não encontrado."),
                    examples = @ExampleObject( value = EXAMPLE_NOT_FOUND))})
    })
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

    private final String EXAMPLE_SUCESSFULLY_FOUND = "{\n" +
            "  \"id\": 1,\n" +
            "  \"nome\": \"Cliente Teste\",\n" +
            "  \"email\": \"teste@gmail.com\",\n" +
            "  \"limite\": 0,\n" +
            "  \"status\": \"PEN\",\n" +
            "  \"telefones\": [\n" +
            "    {\n" +
            "      \"ddi\": \"55\",\n" +
            "      \"ddd\": \"31\",\n" +
            "      \"numero\": \"999999999\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"enderecos\": [\n" +
            "    {\n" +
            "      \"endereco\": \"teste\",\n" +
            "      \"complemento\": \"complemento teste\",\n" +
            "      \"numero\": \"123\",\n" +
            "      \"cep\": \"79000000\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";
    private final String EXAMPLE_NOT_FOUND = "{\n" +
            "  \"timestamp\": \"2023-10-28T19:35:52.657+00:00\",\n" +
            "  \"status\": 404,\n" +
            "  \"message\": \"Cliente com id 1 não encontrado.\",\n" +
            "  \"path\": \"/api/v1/clientes/1\"\n" +
            "}";

    private final String EXAMPLE_UPDATE_CUSTOMER = "\n" +
            "  \"status\": \"Ok\",\n" +
            "  \"code\": 200,\n" +
            "  \"message\": \"Cliente atualizado com sucesso.\"\n" +
            "}";

    private final String EXAMPLE_UPDATE_VOUCHER = "\n" +
            "  \"status\": \"Ok\",\n" +
            "  \"code\": 200,\n" +
            "  \"message\": \"Limite atualizado com sucesso.\"\n" +
            "}";

    private final String EXAMPLE_UPDATE_STATUS = "\n" +
            "  \"status\": \"Ok\",\n" +
            "  \"code\": 200,\n" +
            "  \"message\": \"Status atualizado com sucesso.\"\n" +
            "}";

    private final String EXAMPLE_DELETE_SUCCESS = "\n" +
            "  \"status\": \"Ok\",\n" +
            "  \"code\": 200,\n" +
            "  \"message\": \"Cliente deletado com sucesso.\"\n" +
            "}";
}
