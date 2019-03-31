package com.example.quemtocahoje.POJO;

import java.io.Serializable;
import java.util.Date;

public class Usuario implements Serializable {

    private String nomeUsuario;
    private Autenticacao autenticacao;

    public Usuario(String nomeUsuario, Autenticacao autenticacao) {
        this.nomeUsuario = nomeUsuario;
        this.autenticacao = autenticacao;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public Autenticacao getAutenticacao() {
        return autenticacao;
    }

    public void setAutenticacao(Autenticacao autenticacao) {
        this.autenticacao = autenticacao;
    }
}
