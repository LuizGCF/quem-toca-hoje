package com.example.quemtocahoje.DTO;

import java.io.Serializable;

public class AutenticacaoDTO implements Serializable {
    private String idAutenticacao;
    private String nome;
    private String tipoUsuario;

    public AutenticacaoDTO(){}

    public AutenticacaoDTO(String idAutenticacao, String nome, String tipoUsuario) {
        this.idAutenticacao = idAutenticacao;
        this.nome = nome;
        this.tipoUsuario = tipoUsuario;
        this.nome = nome;

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

}
