package app.railway.up.controller;

import app.railway.up.controller.dto.request.MessageResponseDTO;
import app.railway.up.controller.dto.request.ProdutoDTO;
import app.railway.up.controller.exceptions.ResourceNotFoundException;
import app.railway.up.model.Produto;
import app.railway.up.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<MessageResponseDTO> create(@RequestBody ProdutoDTO produtoDTO){
        var message = produtoService.create(produtoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id) throws ResourceNotFoundException {
        Produto produto = produtoService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(produto);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> findAll(){
        List<Produto> produtoList = produtoService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(produtoList);
    }

    @PutMapping("{id}")
    public ResponseEntity<MessageResponseDTO> update(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) throws ResourceNotFoundException {
        var message = produtoService.update(id,produtoDTO);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @PatchMapping("{id}/preco")
    public ResponseEntity<MessageResponseDTO> patchPreco(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) throws ResourceNotFoundException {
        var message = produtoService.patchPreco(id,produtoDTO);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @PatchMapping("{id}/estoque")
    public ResponseEntity<MessageResponseDTO> patchEstoque(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) throws ResourceNotFoundException {
        var message = produtoService.patchEstoque(id,produtoDTO);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @DeleteMapping
    public ResponseEntity<MessageResponseDTO> deleteById(@PathVariable Long id) throws ResourceNotFoundException {
        var message = produtoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
