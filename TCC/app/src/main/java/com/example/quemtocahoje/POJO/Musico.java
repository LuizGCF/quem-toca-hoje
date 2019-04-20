package com.example.quemtocahoje.POJO;

import java.util.List;

public class Musico extends Usuario {

    private String nomeArtistico;
    private String nomeMusico;
    private String celular;
    private String login;
    private String senha;
    private String email;
    private Endereco endereco;

    private List<Arquivo> arquivos;
    private List<Banda> bandas;
    private List<Convite> convites;



    public Musico(String nomeUsuario, Autenticacao autenticacao, Arquivo arquivo, String nomeArtistico, String celular, List<Banda> bandas, List<Convite> convites, Endereco endereco, List<Arquivo> arquivos) {
        super(nomeUsuario, autenticacao, arquivo);
        this.nomeArtistico = nomeArtistico;
        this.celular = celular;
        this.bandas = bandas;
        this.convites = convites;
        this.endereco = endereco;
        this.arquivos = arquivos;
    }

    public String getNomeArtistico() {
        return nomeArtistico;
    }

    public void setNomeArtistico(String nomeArtistico) {
        this.nomeArtistico = nomeArtistico;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public List<Arquivo> getArquivos() {
        return arquivos;
    }

    public void setArquivos(List<Arquivo> arquivos) {
        this.arquivos = arquivos;
    }

    public List<Banda> getBandas() {
        return bandas;
    }

    public void setBandas(List<Banda> bandas) {
        this.bandas = bandas;
    }

    public List<Convite> getConvites() {
        return convites;
    }

    public void setConvites(List<Convite> convites) {
        this.convites = convites;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
