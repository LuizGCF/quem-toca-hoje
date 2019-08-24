package com.example.quemtocahoje.Persistencia.Entity;

import java.io.Serializable;

public class TokenEntity implements Serializable {

    private Long idToken;
    private Long autenticacao_id;

    private String email;
    private String token;
    private String status;
    private String dataCriacao;

    public TokenEntity(Long autenticacao_id, String email, String token, String status, String dataCriacao) {
        this.autenticacao_id = autenticacao_id;
        this.email = email;
        this.token = token;
        this.status = status;
        this.dataCriacao = dataCriacao;
    }

    public Long getIdToken() {
        return idToken;
    }

    public void setIdToken(Long idToken) {
        this.idToken = idToken;
    }

    public Long getAutenticacao_id() {
        return autenticacao_id;
    }

    public void setAutenticacao_id(Long autenticacao_id) {
        this.autenticacao_id = autenticacao_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
