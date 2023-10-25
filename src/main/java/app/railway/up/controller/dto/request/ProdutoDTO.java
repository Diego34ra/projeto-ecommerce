package app.railway.up.controller.dto.request;

import app.railway.up.model.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
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
