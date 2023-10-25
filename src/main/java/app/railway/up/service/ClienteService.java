package app.railway.up.service;

import app.railway.up.controller.dto.request.ClienteDTO;
import app.railway.up.controller.dto.request.MessageResponseDTO;
import app.railway.up.controller.exceptions.ResourceNotFoundException;
import app.railway.up.model.Cliente;

public interface ClienteService {

    MessageResponseDTO create(ClienteDTO clienteDTO);
    Cliente findById(Long id) throws ResourceNotFoundException;

    MessageResponseDTO deleteById(Long id) throws ResourceNotFoundException;

    MessageResponseDTO update(Long id, Cliente cliente) throws ResourceNotFoundException;

    MessageResponseDTO patchLimite(Long id, Cliente cliente) throws ResourceNotFoundException;

    MessageResponseDTO patchStatus(Long id, Cliente cliente) throws ResourceNotFoundException;
}
