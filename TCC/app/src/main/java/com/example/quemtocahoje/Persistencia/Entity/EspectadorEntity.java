package com.example.quemtocahoje.Persistencia.Entity;


import java.io.Serializable;

public class EspectadorEntity implements Serializable {

    private String idEspectador;
    private String autenticacao_id;

    private String nomeEspectador;
    private String dataCriacao;

    public EspectadorEntity(String autenticacao_id, String nomeEspectador, String dataCriacao) {
        this.autenticacao_id = autenticacao_id;
        this.nomeEspectador = nomeEspectador;
        this.dataCriacao = dataCriacao;
    }


    public String getIdEspectador() {
        return idEspectador;
    }

    public void setIdEspectador(String idEspectador) {
        this.idEspectador = idEspectador;
    }

    public String getAutenticacao_id() {
        return autenticacao_id;
    }

    public void setAutenticacao_id(String autenticacao_id) {
        this.autenticacao_id = autenticacao_id;
    }

    public String getNomeEspectador() {
        return nomeEspectador;
    }

    public void setNomeEspectador(String nomeEspectador) {
        this.nomeEspectador = nomeEspectador;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
