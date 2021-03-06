package com.example.quemtocahoje.Persistencia.Entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BandaEntity implements Serializable {

    private String banda_id;
    private String idCriador;
    private String nome;
    private String dataCriacao;
    private String generos;
    private List<String> integrantes;
    private List<ConviteEntity> Convite;
    private String bandaAtiva;
    private String tipoCadastro;

    public BandaEntity(String nome, String idCriador, String dataCriacao, String generos, List<String> integrantes, String bandaAtiva, List<ConviteEntity> Convite, String tipoCadastro) {
        this.nome = nome;
        this.idCriador = idCriador;
        this.dataCriacao = dataCriacao;
        this.generos = generos;
        this.integrantes = integrantes;
        this.bandaAtiva = bandaAtiva;
        this.Convite = Convite;
        this.tipoCadastro= tipoCadastro;
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

    public String getGeneros() {
        return generos;
    }

    public void setGeneros(String generos) {
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

    public List<ConviteEntity> getConvite() {
        return Convite;
    }

    public void setConvite(List<ConviteEntity> convites) {
        this.Convite = convites;
    }

    public String getTipoCadastro() {
        return tipoCadastro;
    }

    public void setTipoCadastro(String tipoCadastro) {
        this.tipoCadastro = tipoCadastro;
    }

    public List<String> converterConvites(String convites){
        if(convites.isEmpty()) return new ArrayList<String>();

        String[] selecao = convites.split(",");
        ArrayList<String> retorno = new ArrayList<>();
        for(String c : selecao){
            retorno.add(c.replace(",",""));
        }

        return retorno;
    }

    @Override
    public String toString() {
        return "BandaEntity{" +
                "banda_id='" + banda_id + '\'' +
                ", idCriador='" + idCriador + '\'' +
                ", nome='" + nome + '\'' +
                ", dataCriacao='" + dataCriacao + '\'' +
                ", generos='" + generos + '\'' +
                ", integrantes=" + integrantes +
                ", convites=" + Convite +
                ", bandaAtiva='" + bandaAtiva + '\'' +
                ", tipoCadastro='" + tipoCadastro + '\'' +
                '}';
    }
}
