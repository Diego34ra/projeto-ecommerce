package app.railway.up.domain.cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ClienteStatus {

    PEN("pen"),
    APV("apv"),
    RPV("rpv");

    private String status;
}
