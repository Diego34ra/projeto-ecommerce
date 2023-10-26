package app.railway.up.service.impl;

import app.railway.up.controllers.dto.mapper.PedidoMAPPER;
import app.railway.up.controllers.dto.request.MessageResponseDTO;
import app.railway.up.controllers.dto.request.PedidoDTO;
import app.railway.up.controllers.exceptions.ResourceNotFoundException;
import app.railway.up.model.Cliente;
import app.railway.up.model.Item;
import app.railway.up.model.Pedido;
import app.railway.up.model.Produto;
import app.railway.up.repository.PedidoRepository;
import app.railway.up.service.ClienteService;
import app.railway.up.service.PedidoService;
import app.railway.up.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private PedidoMAPPER pedidoMAPPER;
    @Override
    public MessageResponseDTO create(PedidoDTO pedidoDTO) throws ResourceNotFoundException {
        Pedido pedido = pedidoMAPPER.toPedido(pedidoDTO);
        Cliente cliente = clienteService.findById(pedido.getCliente().getId());
        pedido.setCliente(cliente);
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setValorTotal(getValorTotal(pedido.getItems()));
        pedidoRepository.save(pedido);
        return MessageResponseDTO
                .builder()
                .code(201)
                .status("Created")
                .message("Pedido criado com sucesso.")
                .build();
    }

    @Override
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido findById(Long id) throws ResourceNotFoundException {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        return pedidoOptional.orElseThrow(() -> new ResourceNotFoundException("Pedido com id "+id+ " não encontrado."));
    }

    @Override
    public MessageResponseDTO deleteById(Long id) throws ResourceNotFoundException {
        findById(id);
        pedidoRepository.deleteById(id);
        return MessageResponseDTO
                .builder()
                .code(200)
                .status("Ok")
                .message("Pedido deletado com sucesso.")
                .build();
    }

    public BigDecimal getValorTotal(List<Item> items){
        return items.stream()
                .map(item -> {
                    Produto produto;
                    try {
                        produto = produtoService.findById(item.getProduto().getId());
                    } catch (ResourceNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    produto.setEstoque(produto.getEstoque() - item.getQuantidade());
                    produtoService.updateEstoque(produto);
                    return produto.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
