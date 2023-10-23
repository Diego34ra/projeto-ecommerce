package app.railway.up.service.impl;

import app.railway.up.controller.dto.mapper.PedidoMAPPER;
import app.railway.up.controller.dto.request.MessageResponseDTO;
import app.railway.up.controller.dto.request.PedidoDTO;
import app.railway.up.controller.exceptions.ResourceNotFoundException;
import app.railway.up.model.Cliente;
import app.railway.up.model.Item;
import app.railway.up.model.Pedido;
import app.railway.up.model.Produto;
import app.railway.up.repository.PedidoRepository;
import app.railway.up.service.ClienteService;
import app.railway.up.service.ItemService;
import app.railway.up.service.PedidoService;
import app.railway.up.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private PedidoMAPPER pedidoMAPPER;
    @Override
    public MessageResponseDTO create(PedidoDTO pedidoDTO) throws ResourceNotFoundException {
        Pedido pedido = pedidoMAPPER.toPedidoCreate(pedidoDTO);
        Cliente cliente = clienteService.findById(pedido.getCliente().getId());
        pedido.setCliente(cliente);
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setValorTotal(getValorTotal(pedido.getItems()));
//        createItems(pedido.getItems(),pedido);
        pedidoRepository.save(pedido);
        return MessageResponseDTO
                .builder()
                .code(201)
                .status("Created")
                .message("Pedido criado com sucesso.")
                .build();
    }

    @Override
    public Pedido findById(Long id) throws ResourceNotFoundException {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        return pedidoOptional.orElseThrow(() -> new ResourceNotFoundException("Pedido com id "+id+ " não encontrado."));
    }

    public void createItems(List<Item> items, Pedido pedido){
//        items = items.stream().peek(item -> item.setPedido(pedido)).collect(Collectors.toList());
        itemService.create(items);
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
                    produtoService.atualizaPreco(produto);
                    return produto.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
