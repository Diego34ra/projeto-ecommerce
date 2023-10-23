package app.railway.up.service.impl;

import app.railway.up.controller.dto.request.MessageResponseDTO;
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


    @Override
    public MessageResponseDTO create(Produto produto) {
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
    public void atualizaPreco(Produto produto){
        produtoRepository.save(produto);
    }
}
