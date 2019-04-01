package com.example.quemtocahoje.POJO;

import com.example.quemtocahoje.Enum.StatusConvite;

import java.io.Serializable;
import java.util.Date;

public class Convite implements Serializable {

    private Integer id;
    private String emailConvidado;
    private StatusConvite statusConvite;
    private String tokenConvite;
    private Date dataCriacao;
    private Integer idBanda;
    private Integer idMusico;

    public Convite(Integer id, String emailConvidado, StatusConvite statusConvite, String tokenConvite, Date dataCriacao, Integer idBanda) {
        this.id = id;
        this.emailConvidado = emailConvidado;
        this.statusConvite = statusConvite;
        this.tokenConvite = tokenConvite;
        this.dataCriacao = dataCriacao;
        this.idBanda = idBanda;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmailConvidado() {
        return emailConvidado;
    }

    public void setEmailConvidado(String emailConvidado) {
        this.emailConvidado = emailConvidado;
    }

    public StatusConvite getStatusConvite() {
        return statusConvite;
    }

    public void setStatusConvite(StatusConvite statusConvite) {
        this.statusConvite = statusConvite;
    }

    public String getTokenConvite() {
        return tokenConvite;
    }

    public void setTokenConvite(String tokenConvite) {
        this.tokenConvite = tokenConvite;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Integer getIdBanda() {
        return idBanda;
    }

    public void setIdBanda(Integer idBanda) {
        this.idBanda = idBanda;
    }

    public Integer getIdMusico() {
        return idMusico;
    }

    public void setIdMusico(Integer idMusico) {
        this.idMusico = idMusico;
    }
}
