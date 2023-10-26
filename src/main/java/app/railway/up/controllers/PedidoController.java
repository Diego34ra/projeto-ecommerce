package app.railway.up.controllers;

import app.railway.up.controllers.dto.request.MessageResponseDTO;
import app.railway.up.controllers.dto.request.PedidoDTO;
import app.railway.up.controllers.exceptions.ResourceNotFoundException;
import app.railway.up.model.Pedido;
import app.railway.up.service.PedidoService;
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
    public ResponseEntity<MessageResponseDTO> create(@RequestBody PedidoDTO pedidoDTO) throws ResourceNotFoundException {
        var message = pedidoService.create(pedidoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> findAll() {
        List<Pedido> pedidos = pedidoService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(pedidos);
    }

    @GetMapping("{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Long id) throws ResourceNotFoundException {
        Pedido pedido = pedidoService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(pedido);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<MessageResponseDTO> deleteById(Long id) throws ResourceNotFoundException {
        var message = pedidoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

}
