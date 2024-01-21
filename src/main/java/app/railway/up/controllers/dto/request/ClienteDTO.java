package app.railway.up.controllers.dto.request;

import app.railway.up.domain.cliente.Endereco;
import app.railway.up.domain.cliente.Telefone;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ClienteDTO {

    private String nome;

    private String email;

    private List<Endereco> enderecos;

    private List<Telefone> telefones;

    private BigDecimal limite;
}
