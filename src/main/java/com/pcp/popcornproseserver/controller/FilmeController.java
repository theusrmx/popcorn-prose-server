package com.pcp.popcornproseserver.controller;

import com.pcp.popcornproseserver.dto.FilmeDTO;
import com.pcp.popcornproseserver.models.Filme;
import com.pcp.popcornproseserver.service.AuthService;
import com.pcp.popcornproseserver.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("review")
public class FilmeController {

    @Autowired
    private AuthService authService;
    @Autowired
    private FilmeService filmeService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/addReview")
    public ResponseEntity<Map<String, String>> cadastrarFilme(@RequestBody Filme filme) {
        filmeService.criarNovaReview(filme);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Filme cadastrado com sucesso!");

        return ResponseEntity.ok(response);
    }

    // Endpoint para obter uma revisão com base nos parâmetros
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/getReview")
    public ResponseEntity<Filme> getReview(
            @RequestParam Long idFilme,
            @RequestParam Long idUser
    ) {
        try {
            // Use o serviço para obter a revisão
            Filme review = filmeService.getReview(idFilme, idUser);

            // Verifique se a revisão existe
            if (review != null) {
                // Retorna a revisão se encontrada
                return ResponseEntity.ok(review);
            } else {
                // Retorna 404 (Not Found) se a revisão não for encontrada
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Se ocorrer um erro, retorne um status de erro interno do servidor (500)
            return ResponseEntity.status(500).build();
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/getAllReviews")
    public ResponseEntity<List<Filme>> getAllReviews(@RequestParam Long idUser) {
        try {
            // Use o serviço para obter a lista de avaliações
            List<Filme> reviews = filmeService.getAllUserReviews(idUser);

            // Verifique se as avaliações existem
            if (reviews != null && !reviews.isEmpty()) {
                // Retorna as avaliações se encontradas
                return ResponseEntity.ok(reviews);
            } else {
                // Retorna 404 (Not Found) se as avaliações não forem encontradas
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Se ocorrer um erro, retorne um status de erro interno do servidor (500)
            return ResponseEntity.status(500).build();
        }
    }



}
