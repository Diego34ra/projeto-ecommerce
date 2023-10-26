package app.railway.up.controllers.dto.request;

import app.railway.up.model.Endereco;
import app.railway.up.model.Telefone;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ClienteDTO {

    private String nome;

    private String email;

    private List<Endereco> enderecos;

    private List<Telefone> telefones;

    private BigDecimal limite;
}
