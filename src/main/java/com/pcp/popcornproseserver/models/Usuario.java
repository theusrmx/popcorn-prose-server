package com.pcp.popcornproseserver.models;


import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_user;
    private String nome;
    private String sobrenome;
    @Column(name = "data_nascimento", nullable = false)
    private Date dataNascimento;
    private String email;
    private String senha;
    private String genero;
    private String nacionalidade;
    @Column(name = "foto_perfil", nullable = true)
    private byte[] fotoPerfil;

    // Construtores, getters e setters

    public Usuario() {
    }

    public Usuario(String nome, Date dataNascimento, String email, String senha, String genero, String nacionalidade, byte[] fotoPerfil) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.senha = senha;
        this.genero = genero;
        this.nacionalidade = nacionalidade;
        this.fotoPerfil = fotoPerfil;
    }

    // Getters e Setters

    public Long getID_user() {
        return ID_user;
    }

    public void setID_user(Long ID_user) {
        this.ID_user = ID_user;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public byte[] getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(byte[] fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
}

