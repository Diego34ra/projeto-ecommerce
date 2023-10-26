package app.railway.up.controllers.dto.request;

import app.railway.up.model.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
