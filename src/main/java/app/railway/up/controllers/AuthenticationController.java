package app.railway.up.controllers;

import app.railway.up.controllers.dto.request.AuthenticationDTO;
import app.railway.up.controllers.dto.request.LoginResponseDTO;
import app.railway.up.controllers.dto.request.MessageResponseDTO;
import app.railway.up.controllers.dto.request.RegisterDTO;
import app.railway.up.domain.usuario.Usuario;
import app.railway.up.security.TokenService;
import app.railway.up.service.AuthorizationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthorizationService authorizationService;

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO authenticationDTO){
        var userNamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.login(),authenticationDTO.password());
        var auth = this.authenticationManager.authenticate(userNamePassword);

        var token  = tokenService.generateToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok().body(new LoginResponseDTO(token));
    }

    @PostMapping("registrar")
    public ResponseEntity<MessageResponseDTO> registrar(@RequestBody @Valid RegisterDTO registerDTO){
        return authorizationService.registrar(registerDTO);
    }
}
