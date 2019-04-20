package com.example.quemtocahoje.DTO;

import android.arch.persistence.room.ColumnInfo;

public class AutenticacaoDTO {

    @ColumnInfo(name="idAutenticacao")
    private Long idAutenticacao;

    @ColumnInfo(name="tipoUsuario")
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
