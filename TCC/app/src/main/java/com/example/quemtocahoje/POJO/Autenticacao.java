package com.example.quemtocahoje.POJO;

import java.io.Serializable;

public class Autenticacao implements Serializable {
    //campos com o nome igual ao que está recebendo do get da api
    private Integer idAutenticacao;
    private String emailAutenticacao;
    private String loginAutenticacao;
    private String senhaAutenticacao;
    private String tipoUsuarioAutenticacao;
    private String dataCriacao;
    private String dataUltimoLogin;
    private String registro;



    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public Integer getIdAutenticacao() {
        return idAutenticacao;
    }

    public void setIdAutenticacao(Integer idAutenticacao) {
        this.idAutenticacao = idAutenticacao;
    }

    public String getEmailAutenticacao() {
        return emailAutenticacao;
    }

    public void setEmailAutenticacao(String emailAutenticacao) {
        this.emailAutenticacao = emailAutenticacao;
    }

    public String getLoginAutenticacao() {
        return loginAutenticacao;
    }

    public void setLoginAutenticacao(String loginAutenticacao) {
        this.loginAutenticacao = loginAutenticacao;
    }

    public String getSenhaAutenticacao() {
        return senhaAutenticacao;
    }

    public void setSenhaAutenticacao(String senhaAutenticacao) {
        this.senhaAutenticacao = senhaAutenticacao;
    }

    public String getTipoUsuarioAutenticacao() {
        return tipoUsuarioAutenticacao;
    }

    public void setTipoUsuarioAutenticacao(String tipoUsuarioAutenticacao) {
        this.tipoUsuarioAutenticacao = tipoUsuarioAutenticacao;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDataUltimoLogin() {
        return dataUltimoLogin;
    }

    public void setDataUltimoLogin(String dataUltimoLogin) {
        this.dataUltimoLogin = dataUltimoLogin;
    }

}