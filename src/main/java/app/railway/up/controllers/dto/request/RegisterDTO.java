package app.railway.up.controllers.dto.request;

import app.railway.up.domain.usuario.UsuarioRole;

public record RegisterDTO(String login, String password, UsuarioRole role) {
}
