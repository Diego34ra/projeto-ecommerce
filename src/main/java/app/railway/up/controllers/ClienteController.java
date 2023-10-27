package app.railway.up.controllers;

import app.railway.up.controllers.dto.request.ClienteDTO;
import app.railway.up.controllers.dto.request.MessageResponseDTO;
import app.railway.up.controllers.exceptions.ResourceNotFoundException;
import app.railway.up.domain.cliente.Cliente;
import app.railway.up.service.ClienteService;
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
    public ResponseEntity<MessageResponseDTO> create(@RequestBody ClienteDTO clienteDTO){
        var message = clienteService.create(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll(){
        List<Cliente> clientes = clienteService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(clientes);
    }


    @GetMapping("{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) throws ResourceNotFoundException {
        Cliente cliente = clienteService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @PutMapping("{id}")
    public ResponseEntity<MessageResponseDTO> update(@PathVariable Long id, @RequestBody Cliente cliente) throws ResourceNotFoundException {
        var message = clienteService.update(id,cliente);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @PatchMapping("{id}/limite")
    public ResponseEntity<MessageResponseDTO> patchLimite(@PathVariable Long id, @RequestBody Cliente cliente) throws ResourceNotFoundException {
        var message = clienteService.patchLimite(id,cliente);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @PatchMapping("{id}/status")
    public ResponseEntity<MessageResponseDTO> patchStatus(@PathVariable Long id, @RequestBody Cliente cliente) throws ResourceNotFoundException {
        var message = clienteService.patchStatus(id,cliente);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<MessageResponseDTO> deleteById(@PathVariable Long id) throws ResourceNotFoundException {
        var message = clienteService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
