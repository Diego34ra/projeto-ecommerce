package app.railway.up.service.impl;

import app.railway.up.controllers.dto.request.MessageResponseDTO;
import app.railway.up.controllers.dto.request.RegisterDTO;
import app.railway.up.domain.usuario.Usuario;
import app.railway.up.repository.UsuarioRepository;
import app.railway.up.security.TokenService;
import app.railway.up.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationService,UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(username);
    }

    @Override
    public ResponseEntity<MessageResponseDTO> registrar(RegisterDTO registerDTO) {
        if(this.usuarioRepository.findByLogin(registerDTO.login()) != null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MessageResponseDTO
                    .builder()
                    .code(400)
                    .status("Bad Request")
                    .message("Erro ao registrar o novo usuário.")
                    .build());

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        Usuario newUser = new Usuario(registerDTO.login(), encryptedPassword, registerDTO.role());

        this.usuarioRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(MessageResponseDTO
                .builder()
                .code(201)
                .status("Created")
                .message("Usuário criado com sucesso.")
                .build());
    }
}
