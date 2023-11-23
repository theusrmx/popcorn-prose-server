package com.pcp.popcornproseserver.controller;

import com.pcp.popcornproseserver.dto.UsuarioDTO;
import com.pcp.popcornproseserver.models.Usuario;
import com.pcp.popcornproseserver.repository.UsuarioRepository;
import com.pcp.popcornproseserver.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<Object> getIdFromToken(@RequestHeader("Authorization") String token) {
        Long id = authService.getIDFromToken(token);

        if (id != null) {
            return ResponseEntity.ok(id);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido ou expirado");
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/get-surname")
    public ResponseEntity<String> getSurnameFromToken(@RequestHeader("Authorization") String token) {
        String surname = authService.getSurnameFromToken(token);
        if (surname != null) {
            return ResponseEntity.ok(surname);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido ou expirado");
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/adicionar-foto/{userId}")
    public ResponseEntity<String> adicionarFotoPerfil(@PathVariable Long userId, @RequestParam("fotoPerfil") MultipartFile fotoPerfil) {
        try {
            // Verificar se o usuário existe
            Optional<Usuario> optionalUsuario = usuarioRepository.findById(userId);
            if (optionalUsuario.isPresent()) {
                Usuario usuario = optionalUsuario.get();

                // Converter a foto de perfil para um array de bytes
                byte[] bytesFotoPerfil = fotoPerfil.getBytes();

                // Atualizar a foto de perfil do usuário
                usuario.setFotoPerfil(bytesFotoPerfil);
                usuarioRepository.save(usuario);

                return ResponseEntity.ok("Foto de perfil adicionada com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a foto de perfil");
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("getPhoto/{userId}")
    public ResponseEntity<ByteArrayResource> obterFotoPerfil(@PathVariable Long userId) {
        // Buscar o usuário no banco de dados
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(userId);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();

            // Verificar se o usuário tem uma foto de perfil
            if (usuario.getFotoPerfil() != null && usuario.getFotoPerfil().length > 0) {
                // Retornar a foto de perfil como uma resposta de recurso binário
                ByteArrayResource resource = new ByteArrayResource(usuario.getFotoPerfil());
                return ResponseEntity.ok()
                        .contentLength(usuario.getFotoPerfil().length)
                        .header("Content-type", "image/jpeg") // ou o tipo de conteúdo apropriado
                        .header("Content-disposition", "attachment; filename=fotoPerfil.jpg")
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}


