package app.railway.up.controller;

import app.railway.up.controller.dto.request.MessageResponseDTO;
import app.railway.up.controller.dto.request.PedidoDTO;
import app.railway.up.controller.exceptions.ResourceNotFoundException;
import app.railway.up.model.Pedido;
import app.railway.up.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
