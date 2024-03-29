package app.railway.up.domain.cliente;

import app.railway.up.domain.pedido.Pedido;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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

    @ColumnDefault("'PEN'")
    private ClienteStatus status;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Telefone> telefones;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Endereco> enderecos;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Pedido> pedidos;

}
