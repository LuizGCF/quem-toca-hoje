package com.example.quemtocahoje.POJO;

import android.util.EventLog;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Banda implements Serializable {

    private Integer id;
    private String nome;
    private String descricao;
    private Date dataCriacao;

    private List<Arquivo> arquivos;
    private List<String> generos;
    private List<Musico> membros;
    private List<Convite> convites;
    private List<Evento> eventos;
    private List<Avaliacao> avaliacoes;

    public Banda(Integer id, String nome, String descricao, Date dataCriacao, List<Arquivo> arquivos, List<String> generos, List<Musico> membros, List<Convite> convites, List<Evento> eventos, List<Avaliacao> avaliacoes) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.arquivos = arquivos;
        this.generos = generos;
        this.membros = membros;
        this.convites = convites;
        this.eventos = eventos;
        this.avaliacoes = avaliacoes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<String> getGeneros() {
        return generos;
    }

    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }

    public List<Musico> getMembros() {
        return membros;
    }

    public void setMembros(List<Musico> membros) {
        this.membros = membros;
    }

    public List<Convite> getConvites() {
        return convites;
    }

    public void setConvites(List<Convite> convites) {
        this.convites = convites;
    }

    public List<Arquivo> getArquivos() {
        return arquivos;
    }

    public void setArquivos(List<Arquivo> arquivos) {
        this.arquivos = arquivos;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
}
