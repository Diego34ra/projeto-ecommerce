package app.railway.up.service;

import app.railway.up.controller.dto.request.MessageResponseDTO;
import app.railway.up.controller.dto.request.PedidoDTO;
import app.railway.up.controller.exceptions.ResourceNotFoundException;
import app.railway.up.model.Pedido;

public interface PedidoService {

    MessageResponseDTO create(PedidoDTO pedidoDTO) throws ResourceNotFoundException;

    Pedido findById(Long id) throws ResourceNotFoundException;

    MessageResponseDTO deleteById(Long id) throws ResourceNotFoundException;
}
