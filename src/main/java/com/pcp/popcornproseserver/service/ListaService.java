package com.pcp.popcornproseserver.service;


import com.pcp.popcornproseserver.models.Lista;
import com.pcp.popcornproseserver.repository.ListaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListaService {
    @Autowired
    private ListaRepository repository;

    //Adicionar filme na lista
    public Lista adicionarFilmeNaLista(Lista novoFilme){
        return repository.save(novoFilme);
    }

    //Deletar filme da lista
    @Transactional
    public void deletarFilmeDaLista(Long filmeId, Long userId){
        repository.deleteByIdFilmeAndIdUser(filmeId, userId);
    }

    //Listar todos os filmes da lista
    public List<Lista> listarTodos(Long userId){
        return repository.findByIdUser(userId);
    }

    public Lista verificarFilme(Long userId, Long filmeId) {
        return  repository.findByIdUserAndIdFilme(userId, filmeId);
    }
}
