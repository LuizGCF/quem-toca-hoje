package com.example.quemtocahoje.POJO;

import com.example.quemtocahoje.Enum.TipoUsuario;

import java.io.Serializable;
import java.util.Date;

public class Autenticacao implements Serializable {

    private Integer id;
    private String login;
    private String senha;
    private TipoUsuario tipoUsuario;
    private Date dataCriacao;
    private Date dataUltimoLogin;

    public Autenticacao(String login, String senha, TipoUsuario tipoUsuario, Date dataCriacao, Date dataUltimoLogin) {
        this.login = login;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.dataCriacao = dataCriacao;
        this.dataUltimoLogin = dataUltimoLogin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataUltimoLogin() {
        return dataUltimoLogin;
    }

    public void setDataUltimoLogin(Date dataUltimoLogin) {
        this.dataUltimoLogin = dataUltimoLogin;
    }
}
