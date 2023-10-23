package app.railway.up.service;

import app.railway.up.controller.dto.request.MessageResponseDTO;
import app.railway.up.controller.exceptions.ResourceNotFoundException;
import app.railway.up.model.Produto;

import java.util.List;

public interface ProdutoService {

    MessageResponseDTO create(Produto produto);

    Produto findById(Long id) throws ResourceNotFoundException;

    List<Produto> findAll();

    void atualizaPreco(Produto produto);
}
