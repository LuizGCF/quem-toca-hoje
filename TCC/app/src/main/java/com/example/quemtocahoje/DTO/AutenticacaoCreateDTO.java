package com.example.quemtocahoje.DTO;


public class AutenticacaoCreateDTO {

    private String email;
    private String registro;
    private String login;
    private String senha;
    private String tipoUsuario;

    public AutenticacaoCreateDTO() {
    }

    public AutenticacaoCreateDTO(String email, String registro, String loginAutenticacao, String senhaAutenticacao, String tipoUsuarioAutenticacao) {
        this.email = email;
        this.registro = registro;
        this.login = loginAutenticacao;
        this.senha= senhaAutenticacao;
        this.tipoUsuario= tipoUsuarioAutenticacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String loginAutenticacao) {
        this.login = loginAutenticacao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipoUsuarioAutenticacao() {
        return tipoUsuario;
    }

    public void setTipoUsuarioAutenticacao(String tipoUsuarioAutenticacao) {
        this.tipoUsuario = tipoUsuarioAutenticacao;
    }

}