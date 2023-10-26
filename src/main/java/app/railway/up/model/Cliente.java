package app.railway.up.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity(name = "tb_cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    @Column(precision = 10,scale = 2)
    private BigDecimal limite;

    private String status;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Telefone> telefones;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Endereco> enderecos;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Pedido> pedidos;

}
