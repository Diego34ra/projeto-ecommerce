package app.railway.up.service;

import app.railway.up.controller.dto.request.MessageResponseDTO;
import app.railway.up.controller.exceptions.ResourceNotFoundException;
import app.railway.up.model.Cliente;

public interface ClienteService {

    MessageResponseDTO create(Cliente cliente);
    Cliente findById(Long id) throws ResourceNotFoundException;

    MessageResponseDTO deleteById(Long id) throws ResourceNotFoundException;
}
