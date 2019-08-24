package com.example.quemtocahoje.DTO;

public class AutenticacaoDTO {

    private Long idAutenticacao;
    private String tipoUsuario;

    public AutenticacaoDTO(Long idAutenticacao, String tipoUsuario) {
        this.idAutenticacao = idAutenticacao;
        this.tipoUsuario = tipoUsuario;
    }

    public Long getIdAutenticacao() {
        return idAutenticacao;
    }

    public void setIdAutenticacao(Long idAutenticacao) {
        this.idAutenticacao = idAutenticacao;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
