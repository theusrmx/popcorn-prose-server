package com.pcp.popcornproseserver.controller;


import com.pcp.popcornproseserver.models.Lista;
import com.pcp.popcornproseserver.service.ListaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("lista-desejos")
public class ListaController {
    @Autowired
    private ListaService listaService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/adicionar")
    public ResponseEntity<Map<String, Object>> adicionarLista(
            @RequestBody Lista lista) {
        try {
            // Lógica para validar o token se necessário

            Lista filmeAdicionado = listaService.adicionarFilmeNaLista(lista);

            Map<String, Object> response = new HashMap<>();
            response.put("mensagem", "Filme adicionado com sucesso");
            response.put("id_filme_adicionado", filmeAdicionado.getIdFilme());

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            // Se ocorrer algum erro, retorne uma resposta apropriada
            Map<String, Object> response = new HashMap<>();
            response.put("mensagem", "Erro ao adicionar filme à lista");
            response.put("erro", e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/deletar/{filmeId}/{userId}")
    public ResponseEntity<Map<String, Object>> deletarFilmeDaLista(
            @PathVariable Long filmeId,
            @PathVariable Long userId) {
        try {
            // Lógica para validar o token se necessário

            listaService.deletarFilmeDaLista(filmeId, userId);

            Map<String, Object> response = new HashMap<>();
            response.put("mensagem", "Filme removido com sucesso");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Se ocorrer algum erro, retorne uma resposta apropriada
            Map<String, Object> response = new HashMap<>();
            response.put("mensagem", "Erro ao remover filme da lista");
            response.put("erro", e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/listar/{userId}")
    public ResponseEntity<List<Lista>> listarTodosFilmesDaLista(
            @PathVariable Long userId,
            @RequestHeader(name = "Authorization") String token) {
        try {
            // Lógica para validar o token se necessário

            List<Lista> filmesNaLista = listaService.listarTodos(userId);
            return ResponseEntity.ok(filmesNaLista);
        } catch (Exception e) {
            // Se ocorrer algum erro, retorne uma resposta apropriada
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("verificar/{userId}/{filmeId}")
    public ResponseEntity<?> verificarFilme(
            @PathVariable Long userId,
            @PathVariable Long filmeId) {
        try {
            // Lógica para validar o token se necessário
            Lista filmesNaLista = listaService.verificarFilme(userId, filmeId);

            if (filmesNaLista != null) {
                return ResponseEntity.ok(filmesNaLista);
            } else {
                // Retorne uma resposta indicando que o filme não foi encontrado
                Map<String, Object> response = new HashMap<>();
                response.put("mensagem", "Filme não encontrado na lista de desejos");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            // Se ocorrer algum erro, retorne uma resposta apropriada
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
