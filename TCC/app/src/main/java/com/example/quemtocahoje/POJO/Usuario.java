package com.example.quemtocahoje.POJO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Usuario implements Serializable {

    private String nomeUsuario;
    private Autenticacao autenticacao;

    private Arquivo arquivo;

    public Usuario(String nomeUsuario, Autenticacao autenticacao, Arquivo arquivo) {
        this.nomeUsuario = nomeUsuario;
        this.autenticacao = autenticacao;
        this.arquivo = arquivo;
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

    public Arquivo getArquivo() {
        return arquivo;
    }

    public void setArquivo(Arquivo arquivo) {
        this.arquivo = arquivo;
    }
}
