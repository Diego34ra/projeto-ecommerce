package app.railway.up.controller.dto.mapper;

import app.railway.up.controller.dto.request.PedidoDTO;
import app.railway.up.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PedidoMAPPER {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public Pedido toPedido(PedidoDTO pedidoDTO){
        return MODEL_MAPPER.map(pedidoDTO, Pedido.class);
    }
}
