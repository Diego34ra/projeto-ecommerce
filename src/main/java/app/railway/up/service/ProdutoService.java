package app.railway.up.service;

import app.railway.up.controllers.dto.request.MessageResponseDTO;
import app.railway.up.controllers.dto.request.ProdutoDTO;
import app.railway.up.controllers.exceptions.ResourceNotFoundException;
import app.railway.up.domain.item.Produto;

import java.util.List;

public interface ProdutoService {

    MessageResponseDTO create(ProdutoDTO produtoDTO);

    Produto findById(Long id) throws ResourceNotFoundException;

    List<Produto> findAll();

    MessageResponseDTO update(Long id, ProdutoDTO produtoDTO) throws ResourceNotFoundException;

    void updateEstoque(Produto produto);

    MessageResponseDTO patchPreco(Long id, ProdutoDTO produtoDTO) throws ResourceNotFoundException;

    MessageResponseDTO patchEstoque(Long id, ProdutoDTO produtoDTO) throws ResourceNotFoundException;

    MessageResponseDTO deleteById(Long id) throws ResourceNotFoundException;
}
