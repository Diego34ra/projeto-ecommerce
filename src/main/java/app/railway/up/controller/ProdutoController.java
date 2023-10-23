package app.railway.up.controller;

import app.railway.up.controller.dto.request.MessageResponseDTO;
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
    public ResponseEntity<MessageResponseDTO> create(@RequestBody Produto produto){
        var message = produtoService.create(produto);
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
}
