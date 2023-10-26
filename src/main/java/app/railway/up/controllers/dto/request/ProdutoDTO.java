package app.railway.up.controllers.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoDTO {

    private String nome;

    private BigDecimal preco;

    private String ean;

    private Long qtUnit;

    private Long estoque;
}
