package app.railway.up.service.impl;

import app.railway.up.controllers.dto.mapper.ClienteMAPPER;
import app.railway.up.controllers.dto.request.ClienteDTO;
import app.railway.up.controllers.dto.request.MessageResponseDTO;
import app.railway.up.controllers.exceptions.ResourceNotFoundException;
import app.railway.up.domain.cliente.Cliente;
import app.railway.up.domain.cliente.ClienteStatus;
import app.railway.up.domain.cliente.Endereco;
import app.railway.up.domain.cliente.Telefone;
import app.railway.up.domain.pedido.Pedido;
import app.railway.up.repository.ClienteRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteMAPPER clienteMAPPER;

    @Autowired
    @InjectMocks
    private ClienteServiceImpl clienteService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Cliente sendo cadastrado com sucesso")
    public void createTest(){
        List<Endereco> enderecos = new ArrayList<>();
        List<Telefone> telefones = new ArrayList<>();
        ClienteDTO clienteDTO = new ClienteDTO("cliente","cliente@gmail.com",enderecos,telefones,new BigDecimal(0));
        var message = clienteService.create(clienteDTO);

        verify(clienteRepository,times(1)).save(any());

        assertEquals("Cliente cadastrado com sucesso.",message.getMessage());
    }

    @Test
    @DisplayName("Clientes buscados com sucesso")
    public void findAllTest() {
        List<Endereco> enderecos = new ArrayList<>();
        List<Telefone> telefones = new ArrayList<>();
        List<Pedido> pedidos = new ArrayList<>();
        List<Cliente> clientes = Arrays.asList(
                new Cliente(1L,"cliente1", "cliente1@gmail.com", new BigDecimal(0), ClienteStatus.valueOf("PEN"),telefones,enderecos,pedidos),
                new Cliente(2L,"cliente2", "cliente2@gmail.com", new BigDecimal(0), ClienteStatus.valueOf("PEN"),telefones,enderecos,pedidos)
        );
        when(clienteRepository.findAll()).thenReturn(clientes);

        List<Cliente> clientesRetornados = clienteService.findAll();

        assertEquals(clientes, clientesRetornados);
    }

    @Test
    @DisplayName("Buscando cliente pelo id com sucesso")
    public void findByIdClienteEncontrado() throws ResourceNotFoundException {
        List<Endereco> enderecos = new ArrayList<>();
        List<Telefone> telefones = new ArrayList<>();
        List<Pedido> pedidos = new ArrayList<>();

        Cliente clienteEsperado = new Cliente(1L,"cliente1", "cliente1@gmail.com", new BigDecimal(0), ClienteStatus.valueOf("PEN"),telefones,enderecos,pedidos);

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(clienteEsperado));

        Cliente clienteRetornado = clienteService.findById(1L);

        assertEquals(clienteEsperado, clienteRetornado);
    }

    @Test
    @DisplayName("Buscando cliente pelo id, esperando um erro")
    public void findByIdClienteNaoEncontrado() throws ResourceNotFoundException {

        when(clienteRepository.findById(2L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> clienteService.findById(2L));

        assertEquals("Cliente com id 2 não encontrado.",exception.getMessage());
    }

    @Test
    @DisplayName("Deletando cliente com sucesso")
    public void deleteByIdSucesso() throws ResourceNotFoundException {
        List<Endereco> enderecos = new ArrayList<>();
        List<Telefone> telefones = new ArrayList<>();
        List<Pedido> pedidos = new ArrayList<>();
        Cliente clienteEsperado = new Cliente(1L,"cliente1", "cliente1@gmail.com", new BigDecimal(0), ClienteStatus.valueOf("PEN"),telefones,enderecos,pedidos);

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(clienteEsperado));

        MessageResponseDTO messageResponse = clienteService.deleteById(1L);

        verify(clienteRepository,times(1)).delete(any());

        assertEquals(200, messageResponse.getCode());
        assertEquals("Ok", messageResponse.getStatus());
        assertEquals("Cliente deletado com sucesso.", messageResponse.getMessage());
    }
}