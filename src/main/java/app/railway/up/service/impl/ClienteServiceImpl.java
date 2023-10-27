package app.railway.up.service.impl;

import app.railway.up.controllers.dto.mapper.ClienteMAPPER;
import app.railway.up.controllers.dto.request.ClienteDTO;
import app.railway.up.controllers.dto.request.MessageResponseDTO;
import app.railway.up.controllers.exceptions.ResourceNotFoundException;
import app.railway.up.domain.cliente.Cliente;
import app.railway.up.domain.cliente.ClienteStatus;
import app.railway.up.repository.ClienteRepository;
import app.railway.up.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMAPPER clienteMAPPER;

    @Override
    public MessageResponseDTO create(ClienteDTO clienteDTO) {
        Cliente cliente = clienteMAPPER.toCliente(clienteDTO);
        cliente.setStatus(ClienteStatus.valueOf("PEN"));
        clienteRepository.save(cliente);
        return MessageResponseDTO.builder()
                .code(201)
                .status("Created")
                .message("Cliente cadastrado com sucesso.")
                .build();
    }

    @Override
    public List<Cliente> findAll(){
        return clienteRepository.findAll();
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

    @Override
    public MessageResponseDTO update(Long id, Cliente clienteUpdate) throws ResourceNotFoundException {
        Cliente cliente = findById(id);
        cliente.setNome(clienteUpdate.getNome());
        cliente.setLimite(clienteUpdate.getLimite());
        cliente.setEmail(clienteUpdate.getEmail());
        cliente.setStatus(clienteUpdate.getStatus());
        cliente.setEnderecos(clienteUpdate.getEnderecos());
        clienteRepository.save(cliente);
        return MessageResponseDTO
                .builder()
                .code(200)
                .status("Ok")
                .message("Cliente atualizado com sucesso.")
                .build();
    }

    @Override
    public MessageResponseDTO patchLimite(Long id, Cliente clienteUpdate) throws ResourceNotFoundException {
        Cliente cliente = findById(id);
        cliente.setLimite(clienteUpdate.getLimite());
        clienteRepository.save(cliente);
        return MessageResponseDTO
                .builder()
                .code(200)
                .status("Ok")
                .message("Limite atualizado com sucesso.")
                .build();
    }

    @Override
    public MessageResponseDTO patchStatus(Long id, Cliente clienteUpdate) throws ResourceNotFoundException {
        Cliente cliente = findById(id);
        cliente.setStatus(clienteUpdate.getStatus());
        clienteRepository.save(cliente);
        return MessageResponseDTO
                .builder()
                .code(200)
                .status("Ok")
                .message("Status atualizado com sucesso.")
                .build();
    }

}
