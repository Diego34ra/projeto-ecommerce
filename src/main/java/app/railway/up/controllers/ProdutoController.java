package app.railway.up.controllers;

import app.railway.up.controllers.dto.request.MessageResponseDTO;
import app.railway.up.controllers.dto.request.ProdutoDTO;
import app.railway.up.controllers.exceptions.ResourceNotFoundException;
import app.railway.up.domain.item.Produto;
import app.railway.up.service.ProdutoService;
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
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("api/v1/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    @Operation(summary = "Criar um novo produto")
    @ApiResponses({
        @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema( description = "Produto criado com sucesso."),
                                                                                  examples = @ExampleObject( value = EXAMPLE_CREATE_SUCESS))}),
        @ApiResponse(responseCode = "403", content = { @Content(schema = @Schema( description = "Sem permissão para criar um produto."),
                                                                                  examples = @ExampleObject( value = EXAMPLE_ACCESS_DENIED))})
    })
    public ResponseEntity<MessageResponseDTO> create(@RequestBody ProdutoDTO produtoDTO) throws HttpClientErrorException.Forbidden {
        var message = produtoService.create(produtoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("{id}")
    @Operation(summary = "Buscar um produto pelo Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema( description = "Produto buscado com sucesso."),
                    examples = @ExampleObject( value = EXAMPLE_SUCESSFULLY_FOUND))}),
            @ApiResponse(responseCode = "403", content = { @Content(schema = @Schema( description = "Sem permissão para buscar um produto."),
                    examples = @ExampleObject( value = EXAMPLE_ACCESS_DENIED))}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema( description = "Produto não encontrado."),
                    examples = @ExampleObject( value = EXAMPLE_NOT_FOUND))})
    })
    public ResponseEntity<Produto> findById(@PathVariable Long id) throws ResourceNotFoundException {
        Produto produto = produtoService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(produto);
    }

    @GetMapping
    @Operation(summary = "Buscar todos produtos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema( description = "Produtos buscados com sucesso."),
                    examples = @ExampleObject( value = EXAMPLE_SUCESSFULLY_FOUND))}),
            @ApiResponse(responseCode = "403", content = { @Content(schema = @Schema( description = "Sem permissão para buscar produtos."),
                    examples = @ExampleObject( value = EXAMPLE_ACCESS_DENIED))})
    })
    public ResponseEntity<List<Produto>> findAll(){
        List<Produto> produtoList = produtoService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(produtoList);
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualizar um produto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema( description = "Produto atualizado com sucesso."),
                    examples = @ExampleObject( value = EXAMPLE_SUCESSFULLY_FOUND))}),
            @ApiResponse(responseCode = "403", content = { @Content(schema = @Schema( description = "Sem permissão para atualizar o produto."),
                    examples = @ExampleObject( value = EXAMPLE_ACCESS_DENIED))}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema( description = "Produto não encontrado."),
                    examples = @ExampleObject( value = EXAMPLE_NOT_FOUND))})
    })
    public ResponseEntity<MessageResponseDTO> update(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) throws ResourceNotFoundException {
        var message = produtoService.update(id,produtoDTO);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @PatchMapping("{id}/preco")
    @Operation(summary = "Atualizar o preço de um produto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema( description = "Produto atualizado com sucesso."),
                    examples = @ExampleObject( value = EXAMPLE_UPDATE_PRICE))}),
            @ApiResponse(responseCode = "403", content = { @Content(schema = @Schema( description = "Sem permissão para atualizar o produto."),
                    examples = @ExampleObject( value = EXAMPLE_ACCESS_DENIED))}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema( description = "Produto não encontrado."),
                    examples = @ExampleObject( value = EXAMPLE_NOT_FOUND))})
    })
    public ResponseEntity<MessageResponseDTO> patchPreco(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) throws ResourceNotFoundException {
        var message = produtoService.patchPreco(id,produtoDTO);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @PatchMapping("{id}/estoque")
    @Operation(summary = "Atualizar o estoque de um produto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema( description = "Produto atualizado com sucesso."),
                    examples = @ExampleObject( value = EXAMPLE_UPDATE_STOCK))}),
            @ApiResponse(responseCode = "403", content = { @Content(schema = @Schema( description = "Sem permissão para atualizar o produto."),
                    examples = @ExampleObject( value = EXAMPLE_ACCESS_DENIED))}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema( description = "Produto não encontrado."),
                    examples = @ExampleObject( value = EXAMPLE_NOT_FOUND))})
    })
    public ResponseEntity<MessageResponseDTO> patchEstoque(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) throws ResourceNotFoundException {
        var message = produtoService.patchEstoque(id,produtoDTO);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deletar um produto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema( description = "Produto deletado com sucesso."),
                    examples = @ExampleObject( value = EXAMPLE_DELETE_PRODUCT))}),
            @ApiResponse(responseCode = "403", content = { @Content(schema = @Schema( description = "Sem permissão para deletar o produto."),
                    examples = @ExampleObject( value = EXAMPLE_ACCESS_DENIED))}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema( description = "Produto não encontrado."),
                    examples = @ExampleObject( value = EXAMPLE_NOT_FOUND))})
    })
    public ResponseEntity<MessageResponseDTO> deleteById(@PathVariable Long id) throws ResourceNotFoundException {
        var message = produtoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    private final String EXAMPLE_CREATE_SUCESS = "{\n" +
            "  \"status\": \"Created\",\n" +
            "  \"code\": 201,\n" +
            "  \"message\": \"Produto criado com sucesso.\"\n" +
            "}";
    private final String EXAMPLE_ACCESS_DENIED = "{\n" +
            "  \"timestamp\": 1698413037110,\n" +
            "  \"status\": 403,\n" +
            "  \"message\": \"Acesso negado.\",\n" +
            "  \"path\": \"/api/v1/produtos\"\n" +
            "}";
    private final String EXAMPLE_NOT_FOUND = "{\n" +
            "  \"timestamp\": \"2023-10-27T23:25:47.870+00:00\",\n" +
            "  \"status\": 404,\n" +
            "  \"message\": \"Produto com id 1 não encontrado.\",\n" +
            "  \"path\": \"/api/v1/produtos/1\"\n" +
            "}";
    private final String EXAMPLE_SUCESSFULLY_FOUND = "{\n" +
            "  \"id\": 1,\n" +
            "  \"nome\": \"Produto Teste\",\n" +
            "  \"preco\": 99.99,\n" +
            "  \"ean\": \"123456789\",\n" +
            "  \"qtUnit\": 1,\n" +
            "  \"estoque\": 99\n" +
            "}";
    private final String EXAMPLE_UPDATE_PRICE = "\n" +
                                                "  \"status\": \"Ok\",\n" +
                                                "  \"code\": 200,\n" +
                                                "  \"message\": \"Preço atualizado com sucesso.\"\n" +
                                                "}";
    private final String EXAMPLE_UPDATE_STOCK = "\n" +
            "  \"status\": \"Ok\",\n" +
            "  \"code\": 200,\n" +
            "  \"message\": \"Estoque atualizado com sucesso.\"\n" +
            "}";

    private final String EXAMPLE_DELETE_PRODUCT = "\n" +
            "  \"status\": \"Ok\",\n" +
            "  \"code\": 200,\n" +
            "  \"message\": \"Produto deletado com sucesso.\"\n" +
            "}";
}
