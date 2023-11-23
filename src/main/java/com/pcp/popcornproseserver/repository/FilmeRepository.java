package com.pcp.popcornproseserver.repository;

import com.pcp.popcornproseserver.models.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
    Optional<Filme> findByIdFilmeAndIdUser(Long idFilme, Long idUser);

    List<Filme> findByIdUser(Long idUser);
}
