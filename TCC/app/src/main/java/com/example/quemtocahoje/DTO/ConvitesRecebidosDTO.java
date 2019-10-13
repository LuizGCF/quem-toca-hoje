package com.example.quemtocahoje.DTO;

import java.io.Serializable;

public class ConvitesRecebidosDTO implements Serializable {

    private String idRemetente;
    private String nomeBanda;
    private String generos;
    private String dataConvite;

    public ConvitesRecebidosDTO(String idRemetente, String nomeBanda, String generos, String dataConvite) {
        this.idRemetente = idRemetente;
        this.nomeBanda = nomeBanda;
        this.generos = generos;
        this.dataConvite = dataConvite;
    }

    public String getIdRemetente() {
        return idRemetente;
    }

    public void setIdRemetente(String idRemetente) {
        this.idRemetente = idRemetente;
    }

    public String getNomeBanda() {
        return nomeBanda;
    }

    public void setNomeBanda(String nomeBanda) {
        this.nomeBanda = nomeBanda;
    }

    public String getGeneros() {
        return generos;
    }

    public void setGeneros(String generos) {
        this.generos = generos;
    }

    public String getDataConvite() {
        return dataConvite;
    }

    public void setDataConvite(String dataConvite) {
        this.dataConvite = dataConvite;
    }

    @Override
    public String toString() {
        return "ConvitesRecebidosDTO{" +
                "idRemetente='" + idRemetente + '\'' +
                ", nomeBanda='" + nomeBanda + '\'' +
                ", generos='" + generos + '\'' +
                ", dataConvite='" + dataConvite + '\'' +
                '}';
    }
}
