package com.example.quemtocahoje.Persistencia.Entity;

import com.example.quemtocahoje.POJO.Convite;

import java.io.Serializable;
import java.util.List;

public class BandaEntity implements Serializable {

    private String banda_id;
    private String idCriador;
    private String nome;
    private String dataCriacao;
    private List<String> generos;
    private List<String> integrantes;
    private List<ConviteEntity> convites;
    private String bandaAtiva;

    public BandaEntity(String nome, String idCriador, String dataCriacao, List<String> generos, List<String> integrantes, String bandaAtiva, List<ConviteEntity> convites) {
        this.nome = nome;
        this.idCriador = idCriador;
        this.dataCriacao = dataCriacao;
        this.generos = generos;
        this.integrantes = integrantes;
        this.bandaAtiva = bandaAtiva;
        this.convites = convites;
    }

    public BandaEntity() {
    }

    public String getBanda_id() {
        return banda_id;
    }

    public void setBanda_id(String banda_id) {
        this.banda_id = banda_id;
    }

    public String getIdCriador() {
        return idCriador;
    }

    public void setIdCriador(String idCriador) {
        this.idCriador = idCriador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<String> getGeneros() {
        return generos;
    }

    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }

    public List<String> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<String> integrantes) {
        this.integrantes = integrantes;
    }

    public String isBandaAtiva() {
        return bandaAtiva;
    }

    public void setBandaAtiva(String bandaAtiva) {
        this.bandaAtiva = bandaAtiva;
    }

    public List<ConviteEntity> getConvites() {
        return convites;
    }

    public void setConvites(List<ConviteEntity> convites) {
        this.convites = convites;
    }

    @Override
    public String toString() {
        return "BandaEntity{" +
                "banda_id='" + banda_id + '\'' +
                ", idCriador='" + idCriador + '\'' +
                ", nome='" + nome + '\'' +
                ", dataCriacao='" + dataCriacao + '\'' +
                ", generos=" + generos +
                ", integrantes=" + integrantes +
                ", convites=" + convites +
                ", bandaAtiva='" + bandaAtiva + '\'' +
                '}';
    }
}
