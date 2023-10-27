package app.railway.up.service;

import app.railway.up.controllers.dto.request.ClienteDTO;
import app.railway.up.controllers.dto.request.MessageResponseDTO;
import app.railway.up.controllers.exceptions.ResourceNotFoundException;
import app.railway.up.domain.cliente.Cliente;

import java.util.List;

public interface ClienteService {

    MessageResponseDTO create(ClienteDTO clienteDTO);
    List<Cliente> findAll();
    Cliente findById(Long id) throws ResourceNotFoundException;
    MessageResponseDTO deleteById(Long id) throws ResourceNotFoundException;
    MessageResponseDTO update(Long id, Cliente cliente) throws ResourceNotFoundException;
    MessageResponseDTO patchLimite(Long id, Cliente cliente) throws ResourceNotFoundException;
    MessageResponseDTO patchStatus(Long id, Cliente cliente) throws ResourceNotFoundException;
}
