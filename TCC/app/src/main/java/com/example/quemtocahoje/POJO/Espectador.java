package com.example.quemtocahoje.POJO;


import com.example.quemtocahoje.POJO.Autenticacao;

public class Espectador {

    private Integer idEspectador;
    private String nomeEspectador;
    private Integer idAutenticacao;
    private Autenticacao autenticacao;

    public Integer getIdEspectador() {
        return idEspectador;
    }

    public void setIdEspectador(Integer idEspectador) {
        this.idEspectador = idEspectador;
    }

    public String getNomeEspectador() {
        return nomeEspectador;
    }

    public void setNomeEspectador(String nomeEspectador) {
        this.nomeEspectador = nomeEspectador;
    }

    public Integer getIdAutenticacao() {
        return idAutenticacao;
    }

    public void setIdAutenticacao(Integer idAutenticacao) {
        this.idAutenticacao = idAutenticacao;
    }

    public Autenticacao getAutenticacao() {
        return autenticacao;
    }

    public void setAutenticacao(Autenticacao autenticacao) {
        this.autenticacao = autenticacao;
    }

}