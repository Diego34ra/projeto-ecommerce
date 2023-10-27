package app.railway.up.controllers.dto.request;

import app.railway.up.domain.cliente.Cliente;
import app.railway.up.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PedidoDTO {
    private Cliente cliente;
    private List<Item> items;
}
