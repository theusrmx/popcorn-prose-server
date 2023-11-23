package com.pcp.popcornproseserver.models;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "filme")
public class Filme {

    @Id
    @Column(name = "id_review", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReview;
    @Column(name = "id_filme", nullable = false)
    private Long idFilme;
    @Column(name = "review_filme", nullable = false)
    private String reviewFilme;
    @Column(name = "num_estrelas", nullable = false)
    private int numEstrelas;
    @Column(name = "tipo_midia", nullable = false)
    private String tipoMidia;
    @Column(name = "id_user", nullable = false)
    private int idUser;
    //Construtores
    public Filme(){}

    public Filme(Long idReview, Long idFilme, String reviewFilme, int numEstrelas, String tipoMidia, int idUser) {
        this.idReview = idReview;
        this.idFilme = idFilme;
        this.reviewFilme = reviewFilme;
        this.numEstrelas = numEstrelas;
        this.tipoMidia = tipoMidia;
        this.idUser = idUser;
    }

    // Getters e Setters


    public Long getIdReview() {
        return idReview;
    }

    public void setIdReview(Long idReview) {
        this.idReview = idReview;
    }

    public Long getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(Long idFilme) {
        this.idFilme = idFilme;
    }

    public String getReviewFilme() {
        return reviewFilme;
    }

    public void setReviewFilme(String reviewFilme) {
        this.reviewFilme = reviewFilme;
    }

    public int getNumEstrelas() {
        return numEstrelas;
    }

    public void setNumEstrelas(int numEstrelas) {
        this.numEstrelas = numEstrelas;
    }

    public String getTipoMidia() {
        return tipoMidia;
    }

    public void setTipoMidia(String tipoMidia) {
        this.tipoMidia = tipoMidia;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

}