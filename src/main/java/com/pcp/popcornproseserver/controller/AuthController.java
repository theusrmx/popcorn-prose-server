package com.pcp.popcornproseserver.controller;

import com.pcp.popcornproseserver.dto.UsuarioDTO;
import com.pcp.popcornproseserver.models.Usuario;
import com.pcp.popcornproseserver.repository.UsuarioRepository;
import com.pcp.popcornproseserver.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/cadastrar")
    public String cadastrarUsuario(@RequestBody Usuario novoUser) {
        Map<String, String> response = new HashMap<>();

        // Verificar se o nome de usuário já existe
        if (usuarioRepository.findByEmail(novoUser.getEmail()) != null) {
            return "Este email já está cadastrado";
        } else{

            // Criptografar a senha antes de salvar no banco de dados
            String senhaCriptografada = passwordEncoder.encode(novoUser.getSenha());
            novoUser.setSenha(senhaCriptografada);

            // Salvar usuário no banco de dados
            usuarioRepository.save(novoUser);
            return "Usuário cadastrado com sucesso";
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/login")
    public String login(@RequestBody UsuarioDTO usuarioDTO) {
        // Buscar usuário no banco de dados pelo nome de usuário
        Usuario usuario = usuarioRepository.findByEmail(usuarioDTO.getEmail());

        // Verificar se o usuário existe e se a senha está correta
        if (usuario != null && passwordEncoder.matches(usuarioDTO.getSenha(), usuario.getSenha())) {
            // Se as credenciais são válidas, gere um token JWT
            String token = authService.generateToken(usuarioDTO.getEmail());
            return token;
        } else {
            // Tratar erro de autenticação
            return "Credenciais inválidas";
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/get-name")
    public ResponseEntity<String> getNameFromToken(@RequestHeader("Authorization") String token) {
        String name = authService.getNameFromToken(token);

        if (name != null) {
            return ResponseEntity.ok(name);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido ou expirado");
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/get-id")
    public ResponseEntity<String> getIdFromToken(@RequestHeader("Authorization") String token) {
        String id = authService.getNameFromToken(token);

        if (id != null) {
            return ResponseEntity.ok(id);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido ou expirado");
        }
    }
}
