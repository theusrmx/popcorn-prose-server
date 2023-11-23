package com.pcp.popcornproseserver.dto;

public class FilmeDTO {
    private Long idFilme;
    private String reviewFilme;
    private int numEstrelas;
    private String tipoMidia;
    private Long idUser;

    // Getters e Setters
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

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
}