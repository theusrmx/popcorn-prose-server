package com.pcp.popcornproseserver.repository;

import com.pcp.popcornproseserver.models.Lista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListaRepository extends JpaRepository<Lista, Long> {
    void deleteByIdFilmeAndIdUser(Long filmeId, Long userId);
    List<Lista> findByIdUser(Long userId);

    Lista findByIdUserAndIdFilme(Long userId, Long filmeId);
}
