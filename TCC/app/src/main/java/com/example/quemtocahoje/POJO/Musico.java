package com.example.quemtocahoje.POJO;

import java.util.List;

public class Musico extends Usuario {

    private String nomeArtistico;
    private String celular;

    private List<Banda> bandas;
    private List<Convite> convites;
    private Endereco endereco;


    public Musico(String nomeUsuario, Autenticacao autenticacao, String nomeArtistico, String celular, List<Banda> bandas, List<Convite> convites, Endereco endereco) {
        super(nomeUsuario, autenticacao);
        this.nomeArtistico = nomeArtistico;
        this.celular = celular;
        this.bandas = bandas;
        this.convites = convites;
        this.endereco = endereco;
    }

    public String getNomeArtistico() {
        return nomeArtistico;
    }

    public String getCelular() {
        return celular;
    }

    public List<Banda> getBandas() {
        return bandas;
    }

    public List<Convite> getConvites() {
        return convites;
    }

    public Endereco getEndereco() {
        return endereco;
    }
}
