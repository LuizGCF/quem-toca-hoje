package com.example.quemtocahoje.Persistencia.Entity;


import java.io.Serializable;

public class ConviteEntity implements Serializable {

    private String emailConvidado;
    private String statusConvite;
    private String dataCriacao;

    public ConviteEntity(String emailConvidado, String statusConvite, String dataCriacao) {
        this.emailConvidado = emailConvidado;
        this.statusConvite = statusConvite;
        this.dataCriacao = dataCriacao;
    }


    public String getEmailConvidado() {
        return emailConvidado;
    }

    public void setEmailConvidado(String emailConvidado) {
        this.emailConvidado = emailConvidado;
    }

    public String getStatusConvite() {
        return statusConvite;
    }

    public void setStatusConvite(String statusConvite) {
        this.statusConvite = statusConvite;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
