package app.railway.up.service.impl;

import app.railway.up.controller.dto.mapper.ProdutoMAPPER;
import app.railway.up.controller.dto.request.MessageResponseDTO;
import app.railway.up.controller.dto.request.PedidoDTO;
import app.railway.up.controller.dto.request.ProdutoDTO;
import app.railway.up.controller.exceptions.ResourceNotFoundException;
import app.railway.up.model.Produto;
import app.railway.up.repository.ProdutoRepository;
import app.railway.up.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoMAPPER produtoMAPPER;


    @Override
    public MessageResponseDTO create(ProdutoDTO produtoDTO) {
        Produto produto = produtoMAPPER.toProduto(produtoDTO);
        produtoRepository.save(produto);
        return MessageResponseDTO
                .builder()
                .code(201)
                .status("Created")
                .message("Produto criado com sucesso.")
                .build();
    }

    @Override
    public Produto findById(Long id) throws ResourceNotFoundException {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        return produtoOptional.orElseThrow(() -> new ResourceNotFoundException("Produto com id "+id+" não encontrado."));
    }

    @Override
    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    @Override
    public MessageResponseDTO update(Long id, ProdutoDTO produtoUpdate) throws ResourceNotFoundException {
        Produto produto = findById(id);
        produto.setEan(produtoUpdate.getEan());
        produto.setEstoque(produtoUpdate.getEstoque());
        produto.setNome(produtoUpdate.getNome());
        produto.setPreco(produtoUpdate.getPreco());
        produto.setQtUnit(produtoUpdate.getQtUnit());
        produtoRepository.save(produto);
        return MessageResponseDTO
                .builder()
                .code(200)
                .status("Ok")
                .message("Produto atualizado com sucesso.")
                .build();
    }

    @Override
    public void updateEstoque(Produto produto){
        produtoRepository.save(produto);
    }

    @Override
    public MessageResponseDTO patchPreco(Long id, ProdutoDTO produtoDTO) throws ResourceNotFoundException {
        Produto produto = findById(id);
        produto.setPreco(produtoDTO.getPreco());
        produtoRepository.save(produto);
        return MessageResponseDTO
                .builder()
                .code(200)
                .status("Ok")
                .message("Preço atualizado com sucesso.")
                .build();
    }

    @Override
    public MessageResponseDTO patchEstoque(Long id, ProdutoDTO produtoDTO) throws ResourceNotFoundException {
        Produto produto = findById(id);
        produto.setEstoque(produtoDTO.getEstoque());
        produtoRepository.save(produto);
        return MessageResponseDTO
                .builder()
                .code(200)
                .status("Ok")
                .message("Estoque atualizado com sucesso.")
                .build();
    }

    @Override
    public MessageResponseDTO deleteById(Long id) throws ResourceNotFoundException {
        findById(id);
        produtoRepository.deleteById(id);
        return MessageResponseDTO
                .builder()
                .code(200)
                .status("Ok")
                .message("Produto deletado com sucesso.")
                .build();
    }
}
