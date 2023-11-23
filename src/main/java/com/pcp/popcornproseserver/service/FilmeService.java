package com.pcp.popcornproseserver.service;

import com.pcp.popcornproseserver.models.Filme;
import com.pcp.popcornproseserver.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    // Método para criar uma nova review
    public Filme criarNovaReview(Filme novaReview){
        return filmeRepository.save(novaReview);
    }


    // Método para obter uma review com base nos parâmetros
    public Filme getReview(Long idFilme, Long idUser) {
        return filmeRepository.findByIdFilmeAndIdUser(idFilme, idUser).orElse(null);
    }

    // Método para obter todos os reviews relacionados a um usuario
    public List<Filme> getAllUserReviews(Long idUser){
        return filmeRepository.findByIdUser(idUser);
    }

}
