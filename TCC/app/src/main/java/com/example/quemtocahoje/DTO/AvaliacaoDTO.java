package com.example.quemtocahoje.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AvaliacaoDTO implements Serializable {

    private String imagemURI;
    private String loginUsuario;
    private String nomePerfil;
    private String idUsuario;
    private String descricaoGenero;
    private String tipoUsuario;
    private List listaAvaliacoes;

    public AvaliacaoDTO(String imagemURI,String loginUsuario, String nomePerfil, String idUsuario, String descricaoGenero, String tipoUsuario, List listaAvaliacoes) {
        this.imagemURI = imagemURI;
        this.loginUsuario = loginUsuario;
        this.nomePerfil = nomePerfil;
        this.idUsuario = idUsuario;
        this.descricaoGenero = descricaoGenero;
        this.tipoUsuario = tipoUsuario;
        this.listaAvaliacoes = listaAvaliacoes;
    }

    public String getImagemURI() {
        return imagemURI;
    }

    public void setImagemURI(String imagemURI) {
        this.imagemURI = imagemURI;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public String getNomePerfil() {
        return nomePerfil;
    }

    public void setNomePerfil(String nomePerfil) {
        this.nomePerfil = nomePerfil;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDescricaoGenero() {
        return descricaoGenero;
    }

    public void setDescricaoGenero(String descricaoGenero) {
        this.descricaoGenero = descricaoGenero;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List getListaAvaliacoes() {
        return listaAvaliacoes;
    }

    public void setListaAvaliacoes(List listaAvaliacoes) {
        this.listaAvaliacoes = listaAvaliacoes;
    }

    @Override
    public String toString() {
        return "AvaliacaoDTO{" +
                "imagemURI='" + imagemURI + '\'' +
                ", nomePerfil='" + nomePerfil + '\'' +
                ", idUsuario='" + idUsuario + '\'' +
                ", descricaoGenero='" + descricaoGenero + '\'' +
                ", tipoUsuario='" + tipoUsuario + '\'' +
                ", listaAvaliacoes=" + listaAvaliacoes +
                '}';
    }
}
