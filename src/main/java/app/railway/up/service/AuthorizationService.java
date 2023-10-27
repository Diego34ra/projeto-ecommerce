package app.railway.up.service;

import app.railway.up.controllers.dto.request.AuthenticationDTO;
import app.railway.up.controllers.dto.request.LoginResponseDTO;
import app.railway.up.controllers.dto.request.MessageResponseDTO;
import app.railway.up.controllers.dto.request.RegisterDTO;
import org.springframework.http.ResponseEntity;

public interface AuthorizationService {

    ResponseEntity<MessageResponseDTO> registrar(RegisterDTO registerDTO);
}
