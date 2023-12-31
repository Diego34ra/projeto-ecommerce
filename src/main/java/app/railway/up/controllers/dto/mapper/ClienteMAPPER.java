package app.railway.up.controllers.dto.mapper;

import app.railway.up.controllers.dto.request.ClienteDTO;
import app.railway.up.domain.cliente.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ClienteMAPPER {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public Cliente toCliente(ClienteDTO clienteDTO){
        return MODEL_MAPPER.map(clienteDTO, Cliente.class);
    }
}
