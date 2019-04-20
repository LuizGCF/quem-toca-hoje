package com.example.quemtocahoje.Persistencia.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "autenticacao")
public class AutenticacaoEntity implements Serializable {

    @PrimaryKey
    private Long idAutenticacao;

    private String login;
    private String senha;
    private String tipoUsuario;
    private String dataCriacao;
    private String dataUltimoLogin;



    public AutenticacaoEntity(String login, String senha, String tipoUsuario, String dataCriacao, String dataUltimoLogin) {
        this.login = login;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.dataCriacao = dataCriacao;
        this.dataUltimoLogin = dataUltimoLogin;
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
