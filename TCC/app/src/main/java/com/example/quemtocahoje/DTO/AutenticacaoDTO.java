package com.example.quemtocahoje.DTO;

import java.io.Serializable;

public class AutenticacaoDTO implements Serializable {
    private String idAutenticacao;
    private String nome;
    private String email;
    private String tipoUsuario;

    public AutenticacaoDTO(){}

    public AutenticacaoDTO(String idAutenticacao, String nome, String tipoUsuario, String email) {
        this.idAutenticacao = idAutenticacao;
        this.nome = nome;
        this.tipoUsuario = tipoUsuario;
        this.email = email;

    }

    public String getIdAutenticacao() {
        return idAutenticacao;
    }

    public void setIdAutenticacao(String idAutenticacao) {
        this.idAutenticacao = idAutenticacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "AutenticacaoDTO{" +
                "idAutenticacao='" + idAutenticacao + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", tipoUsuario='" + tipoUsuario + '\'' +
                '}';
    }
}
