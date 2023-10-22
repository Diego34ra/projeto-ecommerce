package app.railway.up.service.impl;

import app.railway.up.controller.dto.request.MessageResponseDTO;
import app.railway.up.controller.exceptions.ResourceNotFoundException;
import app.railway.up.model.Cliente;
import app.railway.up.repository.ClienteRepository;
import app.railway.up.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public MessageResponseDTO create(Cliente cliente) {
        clienteRepository.save(cliente);
        return MessageResponseDTO.builder()
                .code(201)
                .status("Created")
                .message("Cliente cadastrado com sucesso.")
                .build();
    }

    @Override
    public Cliente findById(Long id) throws ResourceNotFoundException {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        return clienteOptional.orElseThrow(() -> new ResourceNotFoundException("Cliente com id " + id + " não encontrado."));
    }

    @Override
    public MessageResponseDTO deleteById(Long id) throws ResourceNotFoundException {
        Cliente cliente = findById(id);
        clienteRepository.delete(cliente);
        return MessageResponseDTO
                .builder()
                .code(200)
                .status("Ok")
                .message("Cliente deletado com sucesso.")
                .build();
    }

}
