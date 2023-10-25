package app.railway.up.controller.dto.request;

import app.railway.up.model.Endereco;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ClienteDTO {

    private String nome;

    private String email;

    private List<Endereco> endereco;

    private BigDecimal limite;
}
