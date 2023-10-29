package app.railway.up.controllers;

import app.railway.up.controllers.dto.request.MessageResponseDTO;
import app.railway.up.controllers.dto.request.PedidoDTO;
import app.railway.up.controllers.exceptions.ResourceNotFoundException;
import app.railway.up.domain.pedido.Pedido;
import app.railway.up.service.PedidoService;
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
@RequestMapping("api/v1/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    @Operation(summary = "Criar um novo pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema( description = "Pedido criado com sucesso."),
                    examples = @ExampleObject( value = EXAMPLE_CREATE_SUCESS))}),
            @ApiResponse(responseCode = "403", content = { @Content(schema = @Schema( description = "Sem permissão para criar um pedido."),
                    examples = @ExampleObject( value = EXAMPLE_ACCESS_DENIED))}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema( description = "Cliente ou produto não encontrado."),
                    examples = @ExampleObject( value = EXAMPLE_NOT_FOUND))})

    })
    public ResponseEntity<MessageResponseDTO> create(@RequestBody PedidoDTO pedidoDTO) throws ResourceNotFoundException {
        var message = pedidoService.create(pedidoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping
    @Operation(summary = "Buscar todos os pedidos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema( description = "Pedidos buscados com sucesso."),
                    examples = @ExampleObject( value = EXAMPLE_SUCESSFULLY_FOUND))}),
            @ApiResponse(responseCode = "403", content = { @Content(schema = @Schema( description = "Sem permissão para buscar pedidos."),
                    examples = @ExampleObject( value = EXAMPLE_ACCESS_DENIED))})
    })
    public ResponseEntity<List<Pedido>> findAll() {
        List<Pedido> pedidos = pedidoService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(pedidos);
    }

    @GetMapping("{id}")
    @Operation(summary = "Buscar um pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema( description = "Pedido buscado com sucesso."),
                    examples = @ExampleObject( value = EXAMPLE_SUCESSFULLY_FOUND))}),
            @ApiResponse(responseCode = "403", content = { @Content(schema = @Schema( description = "Sem permissão para buscar pedido."),
                    examples = @ExampleObject( value = EXAMPLE_ACCESS_DENIED))}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema( description = "Pedido não encontrado."),
                    examples = @ExampleObject( value = EXAMPLE_ORDER_NOT_FOUND))})
    })
    public ResponseEntity<Pedido> findById(@PathVariable Long id) throws ResourceNotFoundException {
        Pedido pedido = pedidoService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(pedido);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deletar um pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema( description = "Pedido deletado com sucesso."),
                    examples = @ExampleObject( value = EXAMPLE_DELETE_SUCCESS))}),
            @ApiResponse(responseCode = "403", content = { @Content(schema = @Schema( description = "Sem permissão para deletar pedido."),
                    examples = @ExampleObject( value = EXAMPLE_ACCESS_DENIED))}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema( description = "Pedido não encontrado."),
                    examples = @ExampleObject( value = EXAMPLE_ORDER_NOT_FOUND))})
    })
    public ResponseEntity<MessageResponseDTO> deleteById(Long id) throws ResourceNotFoundException {
        var message = pedidoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    private final String EXAMPLE_ORDER_NOT_FOUND = "{\n" +
                                                   "  \"timestamp\": \"2023-10-28T19:35:52.657+00:00\",\n" +
                                                   "  \"status\": 404,\n" +
                                                   "  \"message\": \"Pedido com id 1 não encontrado.\",\n" +
                                                   "  \"path\": \"/api/v1/pedidos/1\"\n" +
                                                   "}";

    private final String EXAMPLE_NOT_FOUND = "{\n" +
                                             "  \"timestamp\": \"2023-10-28T19:35:52.657+00:00\",\n" +
                                             "  \"status\": 404,\n" +
                                             "  \"message\": \"Cliente com id 1 não encontrado.\",\n" +
                                             "  \"path\": \"/api/v1/pedidos\"\n" +
                                             "},\n" +
                                             "{\n" +
                                             "  \"timestamp\": \"2023-10-27T23:25:47.870+00:00\",\n" +
                                             "  \"status\": 404,\n" +
                                             "  \"message\": \"Produto com id 1 não encontrado.\",\n" +
                                             "  \"path\": \"/api/v1/pedidos\"\n" +
                                             "}";

    private final String EXAMPLE_ACCESS_DENIED = "{\n" +
                                                 "  \"timestamp\": 1698413037110,\n" +
                                                 "  \"status\": 403,\n" +
                                                 "  \"message\": \"Acesso negado.\",\n" +
                                                 "  \"path\": \"/api/v1/pedidos\"\n" +
                                                 "}";

    private final String EXAMPLE_CREATE_SUCESS = "{\n" +
                                                 "  \"status\": \"Created\",\n" +
                                                 "  \"code\": 201,\n" +
                                                 "  \"message\": \"Pedido criado com sucesso.\"\n" +
                                                 "}";

    private final String EXAMPLE_SUCESSFULLY_FOUND = "{\n" +
                                                     "  \"id\": 1,\n" +
                                                     "  \"valorTotal\": 999.9,\n" +
                                                     "  \"dataPedido\": \"2023-10-28T20:45:28.148952\",\n" +
                                                     "  \"items\": [\n" +
                                                     "    {\n" +
                                                     "      \"produto\": {\n" +
                                                     "        \"id\": 1,\n" +
                                                     "        \"nome\": \"produto teste\",\n" +
                                                     "        \"preco\": 99.99,\n" +
                                                     "        \"ean\": \"123456789\",\n" +
                                                     "        \"qtUnit\": 1,\n" +
                                                     "        \"estoque\": 89\n" +
                                                     "      },\n" +
                                                     "      \"quantidade\": 10\n" +
                                                     "    }\n" +
                                                     "  ],\n" +
                                                     "  \"cliente\": {\n" +
                                                     "    \"id\": 3,\n" +
                                                     "    \"nome\": \"Cliente teste\",\n" +
                                                     "    \"email\": \"teste@gmail.com\",\n" +
                                                     "    \"limite\": 0,\n" +
                                                     "    \"status\": \"PEN\",\n" +
                                                     "    \"telefones\": [\n" +
                                                     "      {\n" +
                                                     "        \"ddi\": \"55\",\n" +
                                                     "        \"ddd\": \"66\",\n" +
                                                     "        \"numero\": \"977777777\"\n" +
                                                     "      }\n" +
                                                     "    ],\n" +
                                                     "    \"enderecos\": [\n" +
                                                     "      {\n" +
                                                     "        \"endereco\": \"teste\",\n" +
                                                     "        \"complemento\": \"complemento teste\",\n" +
                                                     "        \"numero\": \"1234\",\n" +
                                                     "        \"cep\": \"99900000\"\n" +
                                                     "      }\n" +
                                                     "    ]\n" +
                                                     "  }\n" +
                                                     "}";

    private final String EXAMPLE_DELETE_SUCCESS = "\n" +
            "  \"status\": \"Ok\",\n" +
            "  \"code\": 200,\n" +
            "  \"message\": \"Pedido deletado com sucesso.\"\n" +
            "}";

}
