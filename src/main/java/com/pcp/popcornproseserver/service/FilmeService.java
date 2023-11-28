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
        return filmeRepository.findByIdFilmeAndIdUser(idFilme, idUser);
    }

    // Método para obter todos os reviews relacionados a um usuario
    public List<Filme> getAllUserReviews(Long idUser){
        return filmeRepository.findByIdUser(idUser);
    }

    public void deleteReview(Long idFilme, Long idUser) {
        // Lógica para excluir uma review com base nos parâmetros
        Filme filme = filmeRepository.findByIdFilmeAndIdUser(idFilme, idUser);
        if (filme != null) {
            filmeRepository.delete(filme);
        }
    }

    public void editReview(Long idFilme, Long idUser, Filme novaReview) {
        // Lógica para editar uma review com base nos parâmetros
        Filme filmeExistente = filmeRepository.findByIdFilmeAndIdUser(idFilme, idUser);
        if (filmeExistente != null) {
            // Atualizar os campos da review
            filmeExistente.setReviewFilme(novaReview.getReviewFilme());
            filmeExistente.setNumEstrelas(novaReview.getNumEstrelas());

            // Salve a review atualizada no banco de dados
            filmeRepository.save(filmeExistente);
        }
    }

}
