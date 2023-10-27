package app.railway.up.service;

import app.railway.up.controllers.dto.request.MessageResponseDTO;
import app.railway.up.controllers.dto.request.PedidoDTO;
import app.railway.up.controllers.exceptions.ResourceNotFoundException;
import app.railway.up.domain.pedido.Pedido;

import java.util.List;

public interface PedidoService {

    MessageResponseDTO create(PedidoDTO pedidoDTO) throws ResourceNotFoundException;
    List<Pedido> findAll();
    Pedido findById(Long id) throws ResourceNotFoundException;
    MessageResponseDTO deleteById(Long id) throws ResourceNotFoundException;
}
