package app.railway.up.service;

import app.railway.up.controller.dto.request.MessageResponseDTO;
import app.railway.up.controller.dto.request.ProdutoDTO;
import app.railway.up.controller.exceptions.ResourceNotFoundException;
import app.railway.up.model.Produto;

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
