package com.example.quemtocahoje.Persistencia.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


import java.io.Serializable;
import java.util.Date;

public class AutenticacaoEntity implements Serializable {

    private Long idAutenticacao;

    private String email;
    private String login;
    private String senha;
    private String tipoUsuario;
    private String dataCriacao;
    private String dataUltimoLogin;



    public AutenticacaoEntity(String email, String login, String senha, String tipoUsuario, String dataCriacao, String dataUltimoLogin) {
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.dataCriacao = dataCriacao;
        this.dataUltimoLogin = dataUltimoLogin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getIdAutenticacao() {
        return idAutenticacao;
    }

    public void setIdAutenticacao(Long idAutenticacao) {
        this.idAutenticacao = idAutenticacao;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
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
