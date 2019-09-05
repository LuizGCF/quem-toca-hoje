package com.example.quemtocahoje.DTO;

public class AutenticacaoDTO {
    private String idAutenticacao;
    private String email;
    private String senha;
    private String nome;
    private String tipoUsuario;

    public AutenticacaoDTO(){}

    public AutenticacaoDTO(String idAutenticacao, String email, String senha, String nome, String tipoUsuario) {
        this.idAutenticacao = idAutenticacao;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.tipoUsuario = tipoUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;

    }

    public String getIdAutenticacao() {
        return idAutenticacao;
    }

    public void setIdAutenticacao(String idAutenticacao) {
        this.idAutenticacao = idAutenticacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

}
