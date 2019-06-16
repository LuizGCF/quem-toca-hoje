package com.example.quemtocahoje.DTO;

import java.io.Serializable;

public class EspectadorCreateDTO implements Serializable {

    private Integer idAutenticacao;
    private String nome;

    public EspectadorCreateDTO(Integer idAutenticacao, String nome) {
        this.idAutenticacao = idAutenticacao;
        this.nome = nome;
    }

    public Integer getIdAutenticacao() {
        return idAutenticacao;
    }

    public void setIdAutenticacao(Integer idAutenticacao) {
        this.idAutenticacao = idAutenticacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
