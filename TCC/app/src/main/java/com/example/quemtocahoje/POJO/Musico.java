package com.example.quemtocahoje.POJO;

import java.util.List;

public class Musico {

    private Integer idMusico;
    private String nomeMusico;
    private String nomeArtMusico;
    private Integer idConvite;
    private String dataCriacao;
    private String telMusico;
    private Integer idAutenticacao;
    private Integer idArquivo;
    private Integer idEndereco;
    private Arquivo arquivo;
    private Autenticacao autenticacao;
    private Convite convite;
    private Endereco endereco;
    private List<Banda> banda = null;

    public Integer getIdMusico() {
        return idMusico;
    }

    public void setIdMusico(Integer idMusico) {
        this.idMusico = idMusico;
    }

    public String getNomeMusico() {
        return nomeMusico;
    }

    public void setNomeMusico(String nomeMusico) {
        this.nomeMusico = nomeMusico;
    }

    public String getNomeArtMusico() {
        return nomeArtMusico;
    }

    public void setNomeArtMusico(String nomeArtMusico) {
        this.nomeArtMusico = nomeArtMusico;
    }

    public Integer getIdConvite() {
        return idConvite;
    }

    public void setIdConvite(Integer idConvite) {
        this.idConvite = idConvite;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getTelMusico() {
        return telMusico;
    }

    public void setTelMusico(String telMusico) {
        this.telMusico = telMusico;
    }

    public Integer getIdAutenticacao() {
        return idAutenticacao;
    }

    public void setIdAutenticacao(Integer idAutenticacao) {
        this.idAutenticacao = idAutenticacao;
    }

    public Integer getIdArquivo() {
        return idArquivo;
    }

    public void setIdArquivo(Integer idArquivo) {
        this.idArquivo = idArquivo;
    }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public Arquivo getArquivo() {
        return arquivo;
    }

    public void setArquivo(Arquivo arquivo) {
        this.arquivo = arquivo;
    }

    public Autenticacao getAutenticacao() {
        return autenticacao;
    }

    public void setAutenticacao(Autenticacao autenticacao) {
        this.autenticacao = autenticacao;
    }

    public Convite getConvite() {
        return convite;
    }

    public void setConvite(Convite convite) {
        this.convite = convite;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Banda> getBanda() {
        return banda;
    }

    public void setBanda(List<Banda> banda) {
        this.banda = banda;
    }

}