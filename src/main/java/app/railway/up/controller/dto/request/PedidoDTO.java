package app.railway.up.controller.dto.request;

import app.railway.up.model.Cliente;
import app.railway.up.model.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PedidoDTO {
    private Cliente cliente;
    private List<Item> items;
}
