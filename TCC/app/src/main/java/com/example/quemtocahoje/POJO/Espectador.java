package com.example.quemtocahoje.POJO;

import java.util.Date;

public class Espectador {
    private int idEspectador;
    private String NomeEspectador;
    private Date DataCriacao;
    private String TipoUsuario;

    public Espectador(){};

    public Espectador(int idEspectador, String nomeEspectador, Date dataCriacao, String tipoUsuario) {
        this.idEspectador = idEspectador;
        NomeEspectador = nomeEspectador;
        DataCriacao = dataCriacao;
        TipoUsuario = tipoUsuario;
    }

    public void setIdEspectador(int idEspectador) {
        this.idEspectador = idEspectador;
    }

    public void setNomeEspectador(String nomeEspectador) {
        NomeEspectador = nomeEspectador;
    }

    public void setDataCriacao(Date dataCriacao) {
        DataCriacao = dataCriacao;
    }

    public void setTipoUsuario(String tipoUsuario) {
        TipoUsuario = tipoUsuario;
    }

    public int getIdEspectador() {
        return idEspectador;
    }

    public String getNomeEspectador() {
        return NomeEspectador;
    }

    public Date getDataCriacao() {
        return DataCriacao;
    }

    public String getTipoUsuario() {
        return TipoUsuario;
    }
}
