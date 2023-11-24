package com.pcp.popcornproseserver.models;

import jakarta.persistence.*;

@Entity
@Table(name = "lista")
public class Lista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lista", nullable = false)
    private Long idLista;
    @Column(name = "id_user", nullable = false)
    private Long idUser;
    @Column(name = "id_filme", nullable = false)
    private Long idFilme;
    @Column(name = "tipo_midia", nullable = false)
    private String tipoMidia;

    //Construtores
    public Lista(){}

    public Lista(Long idLista, Long idUser, Long idFilme, String tipoMidia) {
        this.idLista = idLista;
        this.idUser = idUser;
        this.idFilme = idFilme;
        this.tipoMidia = tipoMidia;
    }

    //Getters e setters
    public Long getIdLista() {
        return idLista;
    }

    public void setIdLista(Long idLista) {
        this.idLista = idLista;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(Long idFilme) {
        this.idFilme = idFilme;
    }

    public String getTipoMidia() {
        return tipoMidia;
    }

    public void setTipoMidia(String tipoMidia) {
        this.tipoMidia = tipoMidia;
    }
}
