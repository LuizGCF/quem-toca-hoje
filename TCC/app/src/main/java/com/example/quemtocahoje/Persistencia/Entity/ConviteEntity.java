package com.example.quemtocahoje.Persistencia.Entity;


public class ConviteEntity {

    private Long idConvite;
    private Long banda_id;

    private String emailConvidado;
    private String statusConvite;
    private String dataCriacao;

    public ConviteEntity(Long banda_id, String emailConvidado, String statusConvite, String dataCriacao) {
        this.banda_id = banda_id;
        this.emailConvidado = emailConvidado;
        this.statusConvite = statusConvite;
        this.dataCriacao = dataCriacao;
    }

    public Long getIdConvite() {
        return idConvite;
    }

    public void setIdConvite(Long idConvite) {
        this.idConvite = idConvite;
    }

    public Long getBanda_id() {
        return banda_id;
    }

    public void setBanda_id(Long banda_id) {
        this.banda_id = banda_id;
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
