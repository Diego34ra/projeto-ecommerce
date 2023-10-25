package app.railway.up.controller.dto.mapper;

import app.railway.up.controller.dto.request.ProdutoDTO;
import app.railway.up.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMAPPER {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public Produto toProduto(ProdutoDTO produtoDTO){
        return MODEL_MAPPER.map(produtoDTO,Produto.class);
    }
}
